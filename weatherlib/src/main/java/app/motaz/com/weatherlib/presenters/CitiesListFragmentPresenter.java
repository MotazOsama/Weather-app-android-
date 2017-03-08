package app.motaz.com.weatherlib.presenters;

import android.content.Context;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;

import app.motaz.com.weatherlib.interactors.CitiesInteractor;
import app.motaz.com.weatherlib.views.CitiesListFragmentViewInterface;

/**
 * Created by motaz on 3/8/17.
 */

public class CitiesListFragmentPresenter extends MvpBasePresenter<CitiesListFragmentViewInterface> {

    private ArrayList<String> allCities;
    private OnCityChoosedListener onCitySelectedListener;

    public void loadAllCities(Context context) {
        allCities = CitiesInteractor.newInstance().getAllCities(context);
        getView().showAllCities(allCities);
    }

    public void userSelectCity(int cityPosition) {
        Log.d("selected city", "userSelectCity: " + allCities.get(cityPosition));
        onCitySelectedListener.onCitySelected(allCities.get(cityPosition));
    }

    public void deleteCity(int cityPosition, Context context) {
        Log.d("delete city", "userSelectCity: " + allCities.get(cityPosition));
        allCities = CitiesInteractor.newInstance().deleteCity(cityPosition, context);
        getView().showAllCities(allCities);
    }

    public void addNewCity(String newCity, Context context) {
        allCities = CitiesInteractor.newInstance().addNewCity(newCity, context);
        getView().showAllCities(allCities);
    }


    public void setOnCitySelectedListener(OnCityChoosedListener onCitySelectedListener) {
        this.onCitySelectedListener = onCitySelectedListener;
    }

    public interface OnCityChoosedListener {
        void onCitySelected(String city);
    }
}
