package app.motaz.com.weatherapp.UI.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.motaz.com.weatherapp.R;
import app.motaz.com.weatherapp.UI.fragments.CityForecastDetailsFragment;
import app.motaz.com.weatherlib.presenters.HomeActivityPresenter;
import app.motaz.com.weatherlib.views.HomeViewInterface;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by motaz on 3/5/17.
 */

public class HomeActivity extends BaseActivity<HomeViewInterface, HomeActivityPresenter>
        implements HomeViewInterface {

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
        CityForecastDetailsFragment cityForecastDetailsFragment =
                (CityForecastDetailsFragment) getSupportFragmentManager().
                        findFragmentByTag(CityForecastDetailsFragment.class.getCanonicalName());
        if (cityForecastDetailsFragment == null)
            replaceFragment(R.id.fl_fragment_container,
                    CityForecastDetailsFragment.newInstance(),
                    getSupportFragmentManager());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
