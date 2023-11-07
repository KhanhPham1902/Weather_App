package com.khanhpham.weatherapp.Domains;

public class WeatherApiData {
    private String API_KEY = "7ec7bb74d3984e2ea0e135859232009";
    private String BASE_URL = "https://api.weatherapi.com/v1/";
    private String LANG = "vi";
    private int DAYS = 14;
    private String AQI = "yes";

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public String getLANG() {
        return LANG;
    }

    public void setLANG(String LANG) {
        this.LANG = LANG;
    }

    public int getDAYS() {
        return DAYS;
    }

    public void setDAYS(int DAYS) {
        this.DAYS = DAYS;
    }

    public String getAQI() {
        return AQI;
    }

    public void setAQI(String AQI) {
        this.AQI = AQI;
    }
}
