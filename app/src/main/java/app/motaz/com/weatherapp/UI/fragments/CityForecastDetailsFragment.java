package app.motaz.com.weatherapp.UI.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import app.motaz.com.weatherapp.R;
import app.motaz.com.weatherlib.presenters.CityForecastDetailsFragmentPresenter;
import app.motaz.com.weatherlib.views.CityForecastDetailsViewInterface;
import butterknife.ButterKnife;

/**
 * Created by motaz on 3/6/17.
 */

public class CityForecastDetailsFragment
        extends MvpFragment<CityForecastDetailsViewInterface, CityForecastDetailsFragmentPresenter>
        implements CityForecastDetailsViewInterface {

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
    public CityForecastDetailsFragmentPresenter createPresenter() {
        return new CityForecastDetailsFragmentPresenter();
    }
}
