package app.motaz.com.weatherlib.presenters;

import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import app.motaz.com.weatherlib.interactors.ForecastInteractor;
import app.motaz.com.weatherlib.models.ForecastResponse;
import app.motaz.com.weatherlib.views.CityForecastDetailsViewInterface;

/**
 * Created by motaz on 3/7/17.
 */

public class CityForecastDetailsFragmentPresenter
        extends MvpBasePresenter<CityForecastDetailsViewInterface> implements Updatable {
    private Repository<Result<ForecastResponse>> repository;

    public void loadForecastDetails() {
        repository =
                ForecastInteractor.newInstance().loadForeCastForSpeceficCity("london", 5);

        repository.addUpdatable(this);
    }

    public void removeUpdatple() {
        repository.removeUpdatable(this);
    }

    @Override
    public void update() {
        if (getView() == null) return;
        Result<ForecastResponse> result = repository.get();
        if (result.succeeded()) {
            getView().showForecastResponse(result.get());
        } else {
            getView().showErrorInLoadingForecast();
        }
    }
}
