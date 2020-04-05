package admin.phuc141.com.appweathers.model.response.SevenData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temp {

    private Double day;

    private Double min;

    private Double max;

    private Integer night;

    private Double eve;

    private Double morn;

    public Double getDay() {
    return day;
    }

    public void setDay(Double day) {
    this.day = day;
    }

    public Double getMin() {
    return min;
    }

    public void setMin(Double min) {
    this.min = min;
    }

    public Double getMax() {
    return max;
    }

    public void setMax(Double max) {
    this.max = max;
    }

    public Integer getNight() {
    return night;
    }

    public void setNight(Integer night) {
    this.night = night;
    }

    public Double getEve() {
    return eve;
    }

    public void setEve(Double eve) {
    this.eve = eve;
    }

    public Double getMorn() {
    return morn;
    }

    public void setMorn(Double morn) {
    this.morn = morn;
    }

}