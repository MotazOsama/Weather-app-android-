package app.motaz.com.weatherlib.interactors;

import android.support.annotation.NonNull;

import com.google.android.agera.Function;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import app.motaz.com.weatherlib.api.ForecastAPI;
import app.motaz.com.weatherlib.configs.Config;
import app.motaz.com.weatherlib.models.ForecastResponse;
import me.drakeet.retrofit2.adapter.agera.AgeraCallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by motaz on 3/7/17.
 */

public class ForecastInteractor {
    private static ForecastInteractor mForecastInteractor;

    private ForecastInteractor() {

    }

    public static ForecastInteractor newInstance() {
        if (mForecastInteractor == null) mForecastInteractor = new ForecastInteractor();
        return mForecastInteractor;
    }

    public Repository<Result<ForecastResponse>> loadForeCastForSpeceficCity(String cityName, int numOfDays) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                readTimeout(60, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Config.API_BASE_URL).
                client(okHttpClient).
                addCallAdapterFactory(AgeraCallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build();
        ForecastAPI api = retrofit.create(ForecastAPI.class);
        Supplier<Result<ForecastResponse>> supplier = api.loadForecastForSpeceficCity(Config.API_KEY, cityName, "json", numOfDays);

        Repository<Result<ForecastResponse>> repository = Repositories.repositoryWithInitialValue(Result.<ForecastResponse>absent())
                .observe()
                .onUpdatesPerLoop()
                .goTo(Executors.newSingleThreadExecutor())
                .attemptGetFrom(supplier)
                .orEnd(new Function<Throwable, Result<ForecastResponse>>() {
                    @NonNull
                    @Override
                    public Result<ForecastResponse> apply(@NonNull Throwable input) {
                        return null;
                    }
                }).thenTransform(new Function<ForecastResponse, Result<ForecastResponse>>() {
                    @NonNull
                    @Override
                    public Result<ForecastResponse> apply(@NonNull ForecastResponse forecastResponse) {
                        return Result.success(forecastResponse);
                    }
                }).compile();
        return repository;

    }
}
