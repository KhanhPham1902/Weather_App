package com.khanhpham.weatherapp.Domains;

import com.google.gson.annotations.SerializedName;

public class Astro {
    @SerializedName("sunrise")
    private String sunRise;
    @SerializedName("sunset")
    private String sunSet;
    @SerializedName("moonrise")
    private String moonRise;
    @SerializedName("moonset")
    private String moonSet;
    @SerializedName("moon_phase")
    private String moonPhase;

    public Astro(String sunRise, String sunSet, String moonRise, String moonSet, String moonPhase) {
        this.sunRise = sunRise;
        this.sunSet = sunSet;
        this.moonRise = moonRise;
        this.moonSet = moonSet;
        this.moonPhase = moonPhase;
    }

    public String getSunRise() {
        return sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public String getMoonRise() {
        return moonRise;
    }

    public String getMoonSet() {
        return moonSet;
    }

    public String getMoonPhase() {
        return moonPhase;
    }
}
