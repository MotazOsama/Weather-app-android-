package app.motaz.com.weatherapp.UI.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import app.motaz.com.weatherapp.R;
import app.motaz.com.weatherapp.UI.fragments.CitiesListFragment;
import app.motaz.com.weatherapp.UI.fragments.CityForecastDetailsFragment;
import app.motaz.com.weatherlib.interactors.CitiesInteractor;
import app.motaz.com.weatherlib.presenters.CitiesListFragmentPresenter;
import app.motaz.com.weatherlib.presenters.HomeActivityPresenter;
import app.motaz.com.weatherlib.views.HomeViewInterface;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by motaz on 3/5/17.
 */

public class HomeActivity extends BaseActivity<HomeViewInterface, HomeActivityPresenter>
        implements HomeViewInterface, CitiesListFragmentPresenter.OnCityChoosedListener {

    @BindView(R.id.icon_menu)
    ImageView iconMenu;
    public boolean cityDetailsIsShown;
    @BindView(R.id.swipe_to_refresh_layout)
    SwipeRefreshLayout swipeToRefreshLayout;

    String lastSelectedCity;

    @NonNull
    @Override
    public HomeActivityPresenter createPresenter() {
        return new HomeActivityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ArrayList<String> allCities = CitiesInteractor.newInstance().getAllCities(this);
        lastSelectedCity = allCities.get(0);
        showCityForecast(lastSelectedCity);
        swipeToRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showCityForecast(lastSelectedCity);
                swipeToRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void showCityForecast(String city) {
        cityDetailsIsShown = true;
        lastSelectedCity = city;
        replaceFragment(R.id.fl_fragment_container,
                CityForecastDetailsFragment.newInstance(lastSelectedCity),
                getSupportFragmentManager(), true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.icon_menu)
    public void onMenuIconClicked() {
        replaceFragment(R.id.fl_fragment_container,
                CitiesListFragment.newInstance(this), getSupportFragmentManager(), true);
        iconMenu.setVisibility(View.GONE);
        cityDetailsIsShown = false;
    }

    @Override
    public void onBackPressed() {
        if (!cityDetailsIsShown) {
            super.onBackPressed();
            iconMenu.setVisibility(View.VISIBLE);
            cityDetailsIsShown = true;
        } else {
            finish();
        }
    }

    @Override
    public void onCitySelected(String city) {
        lastSelectedCity = city;
        showCityForecast(lastSelectedCity);
        iconMenu.setVisibility(View.VISIBLE);
    }
}
