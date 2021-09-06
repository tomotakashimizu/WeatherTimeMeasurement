package model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    public Float temp;
    @SerializedName("feels_like")
    @Expose
    public Float feelsLike;
    @SerializedName("temp_min")
    @Expose
    public Float tempMin;
    @SerializedName("grnd_level")
    @Expose
    public Integer grndLevel;
    @SerializedName("pressure")
    @Expose
    public Integer pressure;
    @SerializedName("temp_max")
    @Expose
    public Float tempMax;
    @SerializedName("humidity")
    @Expose
    public Integer humidity;
    @SerializedName("sea_level")
    @Expose
    public Integer seaLevel;

}
