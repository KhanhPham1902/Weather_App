package com.khanhpham.weatherapp.Retrofit;

import com.google.gson.annotations.SerializedName;
import com.khanhpham.weatherapp.Domains.CurrentWeather;
import com.khanhpham.weatherapp.Domains.Forecast;
import com.khanhpham.weatherapp.Domains.LocationApi;

public class WeatherResponse {
    @SerializedName("location")
    private LocationApi location;

    @SerializedName("current")
    private CurrentWeather current;

    @SerializedName("forecast")
    private Forecast forecast;

    public WeatherResponse(LocationApi location, CurrentWeather current, Forecast forecast) {
        this.location = location;
        this.current = current;
        this.forecast = forecast;
    }

    public LocationApi getLocationApi() {
        return location;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public Forecast getForecast() {
        return forecast;
    }
}

