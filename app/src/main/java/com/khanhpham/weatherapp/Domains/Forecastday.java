package com.khanhpham.weatherapp.Domains;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Forecastday {
    @SerializedName("hour")
    private ArrayList<Hour> hours;
    @SerializedName("date")
    private String date;
    @SerializedName("day")
    private Day days;
    @SerializedName("astro")
    private Astro astro;

    public Forecastday(ArrayList<Hour> hours, String date, Day days, Astro astro) {
        this.hours = hours;
        this.date = date;
        this.days = days;
        this.astro = astro;
    }

    public ArrayList<Hour> getHours() {
        return hours;
    }

    public String getDate() {
        return date;
    }

    public Day getDays() {
        return days;
    }

    public Astro getAstro() {
        return astro;
    }
}
