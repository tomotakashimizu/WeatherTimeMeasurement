package model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("sunrise")
    @Expose
    public Integer sunrise;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("type")
    @Expose
    public Integer type;
    @SerializedName("sunset")
    @Expose
    public Integer sunset;

}
