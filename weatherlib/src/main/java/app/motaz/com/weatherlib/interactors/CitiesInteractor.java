package app.motaz.com.weatherlib.interactors;

import android.content.Context;

import java.util.ArrayList;

import app.motaz.com.weatherlib.R;
import app.motaz.com.weatherlib.configs.Config;
import me.kartikarora.potato.Potato;

/**
 * Created by motaz on 3/8/17.
 */

public class CitiesInteractor {
    private ArrayList<String> allCities;
    private static CitiesInteractor citiesInteractor;

    private CitiesInteractor() {
    }

    public static CitiesInteractor newInstance() {
        if (citiesInteractor == null) citiesInteractor = new CitiesInteractor();
        return citiesInteractor;
    }

    public ArrayList<String> getAllCities(Context context) {
        if (allCities != null) return allCities;
        String allCitiesStr = Potato.potate(context).Preferences().getSharedPreferenceString(Config.CITIES);
        allCities = new ArrayList<>();
        if (allCitiesStr == null ||
                allCitiesStr.equals("") ||
                allCitiesStr.equals("null")) {
            String defaultCities[] = context.getResources().getStringArray(R.array.cities);
            allCitiesStr = "";
            for (int i = 0; i < defaultCities.length; i++) {
                allCities.add(defaultCities[i]);
                allCitiesStr += defaultCities[i] + ",";
            }
            Potato.potate(context).Preferences().putSharedPreference(Config.CITIES, allCitiesStr);
        } else {
            String cities[] = allCitiesStr.split(",");
            for (int i = 0; i < cities.length; i++) {
                if (cities[i] != null && !cities[i].equals("")) {
                    allCities.add(cities[i]);
                }
            }
        }
        return allCities;
    }

    public ArrayList<String> deleteCity(int cityPosition, Context context) {
        allCities.remove(cityPosition);
        saveAllCities(context);
        return allCities;
    }

    private void saveAllCities(Context context) {
        String allCitiesStr = "";
        for (int i = 0; i < allCities.size(); i++) {
            allCitiesStr += allCities.get(i) + ",";
        }
        Potato.potate(context).Preferences().putSharedPreference(Config.CITIES, allCitiesStr);
    }

    public ArrayList<String> addNewCity(String newCity, Context context) {
        allCities.add(newCity);
        saveAllCities(context);
        return allCities;
    }

}
