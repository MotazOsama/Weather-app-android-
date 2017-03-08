package app.motaz.com.weatherlib.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by motaz on 3/7/17.
 */

public class ClimateAverage {
    @SerializedName("month")
    @Expose
    private List<Month> month = null;

    public List<Month> getMonth() {
        return month;
    }

    public void setMonth(List<Month> month) {
        this.month = month;
    }
}
