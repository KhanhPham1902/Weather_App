package com.khanhpham.weatherapp.Domains;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Forecast {
    @SerializedName("forecastday")
    private ArrayList<Forecastday> forecastday;

    public Forecast(ArrayList<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }

    public ArrayList<Forecastday> getForecastday() {
        return forecastday;
    }
}
