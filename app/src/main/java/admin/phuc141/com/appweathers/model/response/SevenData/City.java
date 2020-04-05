package admin.phuc141.com.appweathers.model.response.SevenData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    private Integer id;

    private String name;

    private String country;

    private Integer population;

    private Integer timezone;

    public Integer getId() {
    return id;
    }

    public void setId(Integer id) {
    this.id = id;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getCountry() {
    return country;
    }

    public void setCountry(String country) {
    this.country = country;
    }

    public Integer getPopulation() {
    return population;
    }

    public void setPopulation(Integer population) {
    this.population = population;
    }

    public Integer getTimezone() {
    return timezone;
    }

    public void setTimezone(Integer timezone) {
    this.timezone = timezone;
    }

}