package app.motaz.com.weatherlib.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.ArrayList;

/**
 * Created by motaz on 3/8/17.
 */

public interface CitiesListFragmentViewInterface extends MvpView {
    void showAllCities(ArrayList<String> allCities);
}
