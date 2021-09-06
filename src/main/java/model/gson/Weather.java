package model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("main")
    @Expose
    public String main;
    @SerializedName("icon")
    @Expose
    public String icon;

}
