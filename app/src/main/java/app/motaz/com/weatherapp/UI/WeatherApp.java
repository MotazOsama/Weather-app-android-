package app.motaz.com.weatherapp.UI;

import android.app.Application;

import app.motaz.com.weatherapp.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by motaz on 3/7/17.
 */

public class WeatherApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
