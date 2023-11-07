package com.khanhpham.weatherapp.Domains;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Hour implements Serializable {
    @SerializedName("time")
    private String time;

    @SerializedName("temp_c")
    private Double temp;

    @SerializedName("condition")
    private WeatherCondition weatherCondition;

    @SerializedName("wind_kph")
    private Double windSpeed;

    @SerializedName("humidity")
    private Double humidity;

    @SerializedName("is_day")
    private int isDay;

    @SerializedName("wind_degree")
    private Double windDegree;
    @SerializedName("wind_dir")
    private String windDirection;
    @SerializedName("pressure_mb")
    private Double pressure;
    @SerializedName("precip_mm")
    private Double precipitation;
    @SerializedName("cloud")
    private Double cloudCover;
    @SerializedName("chance_of_rain")
    private Double chanceOfRain;
    @SerializedName("vis_km")
    private Double vision;
    @SerializedName("gust_kph")
    private Double windGust;
    @SerializedName("feelslike_c")
    private Double feelsLike;
    @SerializedName("uv")
    private Double uv;

    public Hour(String time, Double temp, WeatherCondition weatherCondition, Double windSpeed, Double humidity, int isDay, Double windDegree, String windDirection, Double pressure, Double precipitation, Double cloudCover, Double chanceOfRain, Double vision, Double windGust, Double feelsLike, Double uv) {
        this.time = time;
        this.temp = temp;
        this.weatherCondition = weatherCondition;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.isDay = isDay;
        this.windDegree = windDegree;
        this.windDirection = windDirection;
        this.pressure = pressure;
        this.precipitation = precipitation;
        this.cloudCover = cloudCover;
        this.chanceOfRain = chanceOfRain;
        this.vision = vision;
        this.windGust = windGust;
        this.feelsLike = feelsLike;
        this.uv = uv;
    }

    public String getTime() {
        return time;
    }

    public Double getTemp() {
        return temp;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public int getIsDay() {
        return isDay;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getWindDegree() {
        return windDegree;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public Double getChanceOfRain() {
        return chanceOfRain;
    }

    public Double getVision() {
        return vision;
    }

    public Double getWindGust() {
        return windGust;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public Double getUv() {
        return uv;
    }
}
