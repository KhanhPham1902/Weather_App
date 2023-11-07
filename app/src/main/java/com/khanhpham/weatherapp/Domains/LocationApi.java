package com.khanhpham.weatherapp.Domains;

import com.google.gson.annotations.SerializedName;

public class LocationApi {
    @SerializedName("country")
    private String country;

    @SerializedName("localtime")
    private String localtime;

    public LocationApi(String country, String localtime) {
        this.country = country;
        this.localtime = localtime;
    }

    public String getCountry() {
        return country;
    }

    public String getLocaltime() {
        return localtime;
    }
}
