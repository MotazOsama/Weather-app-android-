package app.motaz.com.weatherapp.UI.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;

import app.motaz.com.weatherapp.R;
import app.motaz.com.weatherlib.presenters.HomeActivityPresenter;
import app.motaz.com.weatherlib.views.HomeViewInterface;
import butterknife.ButterKnife;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
