package app.motaz.com.weatherapp.UI.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.List;

import app.motaz.com.weatherapp.R;
import app.motaz.com.weatherapp.UI.adapters.WeatherAdapter;
import app.motaz.com.weatherlib.models.ForecastResponse;
import app.motaz.com.weatherlib.models.Weather;
import app.motaz.com.weatherlib.presenters.CityForecastDetailsFragmentPresenter;
import app.motaz.com.weatherlib.utils.DateUtils;
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
    @BindView(R.id.iv_weather_icon)
    ImageView ivWeatherIcon;
    private String city;

    public static CityForecastDetailsFragment newInstance(String city) {
        CityForecastDetailsFragment fragment = new CityForecastDetailsFragment();
        fragment.setCity(city);
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
        getPresenter().loadForecastDetails(city);
    }

    @Override
    public void onResume() {
        super.onResume();
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
        tvForecastDate.setText(DateUtils.
                getFormatedDate(forecastResponse.getData().getWeather().get(0).getDate()) +
                " " +
                forecastResponse.getData().
                        getCurrentCondition().get(0).getObservationTime());
        tvForecastTemp.
                setText(forecastResponse.getData().
                        getCurrentCondition().get(0).getTempC() + "\u2103");

        Glide.with(getActivity()).load(forecastResponse.getData().getCurrentCondition()
                .get(0).getWeatherIconUrl().get(0).getValue()).into(ivWeatherIcon);

        showNextDaysWeather(forecastResponse.getData().getWeather());
    }

    private void showNextDaysWeather(List<Weather> weatherList) {
        WeatherAdapter adapter = new WeatherAdapter(getActivity(), 0, weatherList);
        listviewDaysForecastList.setAdapter(adapter);
    }

    public void setCity(String city) {
        this.city = city;
    }
}
