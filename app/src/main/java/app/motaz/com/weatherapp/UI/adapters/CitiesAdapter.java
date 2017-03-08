package app.motaz.com.weatherapp.UI.adapters;

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

import java.util.List;

import app.motaz.com.weatherapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by motaz on 3/8/17.
 */

public class CitiesAdapter extends ArrayAdapter<String> {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_delete_icon)
    ImageView ivDeleteIcon;
    private OnDeleteCityListener onDeleteCityListener;

    public CitiesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = LayoutInflater.from(getContext()).inflate(R.layout.row_view_city, parent, false);
        ButterKnife.bind(this, rowView);
        tvTitle.setText(getItem(position));
        ivDeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteCityListener.deleteCity(position);
            }
        });
        return rowView;
    }

    public void setOnDeleteCityListener(OnDeleteCityListener onDeleteCityListener) {
        this.onDeleteCityListener = onDeleteCityListener;
    }

    public interface OnDeleteCityListener {
        void deleteCity(int position);
    }
}
