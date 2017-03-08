package app.motaz.com.weatherapp.UI;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.motaz.com.weatherapp.R;
import app.motaz.com.weatherlib.models.Weather;
import app.motaz.com.weatherlib.utils.DateUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by motaz on 3/8/17.
 */

public class WeatherAdapter extends ArrayAdapter<Weather> {
    @BindView(R.id.tv_weather_item_date)
    TextView tvWeatherItemDate;
    @BindView(R.id.iv_weather_icon)
    ImageView ivWeatherIcon;
    @BindView(R.id.tv_weather_temp)
    TextView tvWeatherTemp;

    public WeatherAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Weather> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = LayoutInflater.from(getContext()).inflate(R.layout.row_view_weather, parent, false);
        ButterKnife.bind(this, rowView);
        Weather weather = getItem(position);
        tvWeatherItemDate.setText(DateUtils.getFormatedDate(weather.getDate()));
        tvWeatherTemp.setText(weather.getMaxtempC() + "\u2103" + " - " + weather.getMintempC() + "\u2103");
        Glide.with(getContext()).
                load(weather.getHourly().get(0).getWeatherIconUrl().
                        get(0).getValue()).into(ivWeatherIcon);

        return rowView;
    }
}
