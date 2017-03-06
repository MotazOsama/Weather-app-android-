package app.motaz.com.weatherlib.api;

import com.google.android.agera.Result;
import com.google.android.agera.Supplier;

import app.motaz.com.weatherlib.models.ForecastResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by motaz on 3/7/17.
 */

public interface ForecastAPI {
    @GET("weather.ashx")
    Supplier<Result<ForecastResponse>> loadForecastForSpeceficCity(@Query("key") String key,
                                                                   @Query("q") String cityName,
                                                                   @Query("format") String format,
                                                                   @Query("num_of_days") int numOfDays);

}
