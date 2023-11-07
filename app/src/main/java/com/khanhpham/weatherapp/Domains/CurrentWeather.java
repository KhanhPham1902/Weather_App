package com.khanhpham.weatherapp.Domains;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {
    @SerializedName("temp_c")
    private double tempC;
    @SerializedName("is_day")
    private int isDay;
    @SerializedName("condition")
    private WeatherCondition condition;
    @SerializedName("wind_kph")
    private double windKph;
    @SerializedName("humidity")
    private double humidity;
    @SerializedName("uv")
    private double uv;
    @SerializedName("feelslike_c")
    private double feelslikeC;
    @SerializedName("precip_mm")
    private double precipitation;
    @SerializedName("wind_degree")
    private double windDegree;
    @SerializedName("wind_dir")
    private String windDirection;
    @SerializedName("gust_kph")
    private double windGust;
    @SerializedName("vis_km")
    private double vision;
    @SerializedName("cloud")
    private double cloud;
    @SerializedName("air_quality")
    private AirQuality airQuality;

    public CurrentWeather(double tempC, int isDay, WeatherCondition condition, double windKph, double humidity,
                          double uv, double feelslikeC, double precipitation, double windDegree, String windDirection,
                          double windGust, double vision, double cloud, AirQuality airQuality) {
        this.tempC = tempC;
        this.isDay = isDay;
        this.condition = condition;
        this.windKph = windKph;
        this.humidity = humidity;
        this.uv = uv;
        this.feelslikeC = feelslikeC;
        this.precipitation = precipitation;
        this.windDegree = windDegree;
        this.windDirection = windDirection;
        this.windGust = windGust;
        this.vision = vision;
        this.cloud = cloud;
        this.airQuality = airQuality;
    }

    public double getTempC() {
        return tempC;
    }

    public int getIsDay() {
        return isDay;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public double getWindKph() {
        return windKph;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getUv() {
        return uv;
    }

    public double getFeelslikeC() {
        return feelslikeC;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getWindDegree() {
        return windDegree;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public double getWindGust() {
        return windGust;
    }

    public double getVision() {
        return vision;
    }

    public double getCloud() {
        return cloud;
    }

    public AirQuality getAirQuality() {
        return airQuality;
    }
}
