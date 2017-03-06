package app.motaz.com.weatherapp.UI.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import app.motaz.com.weatherapp.R;
import app.motaz.com.weatherlib.models.ForecastResponse;
import app.motaz.com.weatherlib.presenters.CityForecastDetailsFragmentPresenter;
import app.motaz.com.weatherlib.views.CityForecastDetailsViewInterface;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by motaz on 3/6/17.
 */

public class CityForecastDetailsFragment
        extends MvpFragment<CityForecastDetailsViewInterface, CityForecastDetailsFragmentPresenter>
        implements CityForecastDetailsViewInterface {

    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.tv_forecast_date)
    TextView tvForecastDate;
    @BindView(R.id.tv_forecast_temp)
    TextView tvForecastTemp;
    @BindView(R.id.listview_days_forecast_list)
    ListView listviewDaysForecastList;

    public static CityForecastDetailsFragment newInstance() {
        CityForecastDetailsFragment fragment = new CityForecastDetailsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_forecast_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().loadForecastDetails();
    }

    @Override
    public CityForecastDetailsFragmentPresenter createPresenter() {
        return new CityForecastDetailsFragmentPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().removeUpdatple();
    }

    @Override
    public void showErrorInLoadingForecast() {
        Toast.makeText(getActivity(),
                "Error in loading forecast for this city.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showForecastResponse(ForecastResponse forecastResponse) {
        tvCityName.setText(forecastResponse.getData().getRequest().get(0).getQuery());
        tvForecastDate.setText(forecastResponse.getData().getWeather().get(0).getDate() + " " +
                forecastResponse.getData().getCurrentCondition().get(0).getObservationTime());
        tvForecastTemp.setText(forecastResponse.getData().getCurrentCondition().get(0).getTempC() + "\u2103");
    }
}
