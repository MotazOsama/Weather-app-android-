package app.motaz.com.weatherapp.UI.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.ArrayList;

import app.motaz.com.weatherapp.R;
import app.motaz.com.weatherapp.UI.adapters.CitiesAdapter;
import app.motaz.com.weatherlib.presenters.CitiesListFragmentPresenter;
import app.motaz.com.weatherlib.views.CitiesListFragmentViewInterface;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by motaz on 3/8/17.
 */

public class CitiesListFragment
        extends MvpFragment<CitiesListFragmentViewInterface, CitiesListFragmentPresenter>
        implements CitiesListFragmentViewInterface {
    @BindView(R.id.lv_cities)
    ListView lvCities;
    @BindView(R.id.iv_add_new_city)
    ImageView ivAddNewCity;
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.btn_add_city)
    Button btnAddCity;
    private CitiesListFragmentPresenter.OnCityChoosedListener onCityChoosedListener;

    @Override
    public CitiesListFragmentPresenter createPresenter() {
        return new CitiesListFragmentPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().loadAllCities(getContext());
        getPresenter().setOnCitySelectedListener(onCityChoosedListener);
        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etCity.getText().toString().equals(""))
                    getPresenter().addNewCity(etCity.getText().toString(), getContext());

                etCity.clearFocus();
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                etCity.setVisibility(View.GONE);
                btnAddCity.setVisibility(View.GONE);
            }
        });

    }

    public static CitiesListFragment newInstance
            (CitiesListFragmentPresenter.OnCityChoosedListener onCityChoosedListener) {
        CitiesListFragment fragment = new CitiesListFragment();
        fragment.onCityChoosedListener = onCityChoosedListener;
        return fragment;
    }

    @Override
    public void showAllCities(ArrayList<String> allCities) {
        CitiesAdapter citiesAdapter = new CitiesAdapter(getContext(), 0, allCities);
        lvCities.setAdapter(citiesAdapter);
        citiesAdapter.setOnDeleteCityListener(new CitiesAdapter.OnDeleteCityListener() {
            @Override
            public void deleteCity(int position) {
                getPresenter().deleteCity(position, getContext());
            }
        });
        lvCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getPresenter().userSelectCity(i);
            }
        });
    }


    @OnClick(R.id.iv_add_new_city)
    public void OnAddNewCityButtonClicked() {
        etCity.setVisibility(View.VISIBLE);
        btnAddCity.setVisibility(View.VISIBLE);
    }

}
