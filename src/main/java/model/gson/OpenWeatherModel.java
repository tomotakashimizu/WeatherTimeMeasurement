package model.gson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenWeatherModel {

    @SerializedName("rain")
    @Expose
    public Rain rain;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("cod")
    @Expose
    public Integer cod;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("dt")
    @Expose
    public Integer dt;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
    @SerializedName("visibility")
    @Expose
    public Integer visibility;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("coord")
    @Expose
    public Coord coord;
    @SerializedName("timezone")
    @Expose
    public Integer timezone;

}
