package app.motaz.com.weatherlib.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

import app.motaz.com.weatherlib.models.ForecastResponse;

/**
 * Created by motaz on 3/7/17.
 */

public interface CityForecastDetailsViewInterface extends MvpView {
    void showErrorInLoadingForecast();

    void showForecastResponse(ForecastResponse forecastResponse);
}
