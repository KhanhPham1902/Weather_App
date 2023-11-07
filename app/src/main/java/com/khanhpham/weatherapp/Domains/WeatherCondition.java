package com.khanhpham.weatherapp.Domains;

import com.google.gson.annotations.SerializedName;

public class WeatherCondition {
    @SerializedName("text")
    private String text;

    @SerializedName("code")
    private String code;

    public WeatherCondition(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public String getCode() {
        return code;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
