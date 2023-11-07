package com.khanhpham.weatherapp.Domains;

import com.google.gson.annotations.SerializedName;

public class Day {
    private String date;
    @SerializedName("condition")
    private WeatherCondition weatherCondition;
    @SerializedName("maxtemp_c")
    private Double tempMax;
    @SerializedName("mintemp_c")
    private Double tempMin;
    @SerializedName("avgtemp_c")
    private Double avgTemp;
    @SerializedName("uv")
    private Double uv;
    @SerializedName("maxwind_kph")
    private Double maxWind;
    @SerializedName("avghumidity")
    private Double avgHumidity;
    @SerializedName("avgvis_km")
    private Double avgVision;
    @SerializedName("totalprecip_mm")
    private Double totalPre;
    @SerializedName("daily_chance_of_rain")
    private Double chanceRain;
    @SerializedName("totalsnow_cm")
    private Double totalSnow;
    @SerializedName("daily_chance_of_snow")
    private Double chanceSnow;
    @SerializedName("air_quality")
    private AirQuality airQuality;

    public Day(String date, WeatherCondition weatherCondition, Double tempMax, Double tempMin, Double avgTemp, Double uv,
               Double maxWind, Double avgHumidity, Double avgVision, Double totalPre, Double chanceRain, Double totalSnow,
               Double chanceSnow, AirQuality airQuality) {
        this.date = date;
        this.weatherCondition = weatherCondition;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.avgTemp = avgTemp;
        this.uv = uv;
        this.maxWind = maxWind;
        this.avgHumidity = avgHumidity;
        this.avgVision = avgVision;
        this.totalPre = totalPre;
        this.chanceRain = chanceRain;
        this.totalSnow = totalSnow;
        this.chanceSnow = chanceSnow;
        this.airQuality = airQuality;
    }

    public String getDate() {
        return date;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public Double getAvgTemp() {
        return avgTemp;
    }

    public Double getUv() {
        return uv;
    }

    public Double getMaxWind() {
        return maxWind;
    }

    public Double getAvgHumidity() {
        return avgHumidity;
    }

    public Double getAvgVision() {
        return avgVision;
    }

    public Double getTotalPre() {
        return totalPre;
    }

    public Double getChanceRain() {
        return chanceRain;
    }

    public Double getTotalSnow() {
        return totalSnow;
    }

    public Double getChanceSnow() {
        return chanceSnow;
    }

    public AirQuality getAirQuality() {
        return airQuality;
    }
}
