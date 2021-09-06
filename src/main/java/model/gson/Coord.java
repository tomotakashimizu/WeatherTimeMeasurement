package model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName("lon")
    @Expose
    public Float lon;
    @SerializedName("lat")
    @Expose
    public Float lat;

}
