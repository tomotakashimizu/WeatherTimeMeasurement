package model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    public Float speed;
    @SerializedName("deg")
    @Expose
    public Integer deg;
    @SerializedName("gust")
    @Expose
    public Float gust;

}
