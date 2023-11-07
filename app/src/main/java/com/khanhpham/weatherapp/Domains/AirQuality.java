package com.khanhpham.weatherapp.Domains;

import com.google.gson.annotations.SerializedName;

public class AirQuality {
    @SerializedName("co")
    private Double coIndex;
    @SerializedName("no2")
    private Double no2Index;
    @SerializedName("o3")
    private Double ozoneIndex;
    @SerializedName("so2")
    private Double so2Index;
    @SerializedName("pm2_5")
    private Double pm2_5Index;
    @SerializedName("pm10")
    private Double pm10Index;

    public AirQuality(Double coIndex, Double no2Index, Double ozoneIndex, Double so2Index, Double pm2_5Index, Double pm10Index) {
        this.coIndex = coIndex;
        this.no2Index = no2Index;
        this.ozoneIndex = ozoneIndex;
        this.so2Index = so2Index;
        this.pm2_5Index = pm2_5Index;
        this.pm10Index = pm10Index;
    }

    public Double getCoIndex() {
        return coIndex;
    }

    public Double getNo2Index() {
        return no2Index;
    }

    public Double getOzoneIndex() {
        return ozoneIndex;
    }

    public Double getSo2Index() {
        return so2Index;
    }

    public Double getPm2_5Index() {
        return pm2_5Index;
    }

    public Double getPm10Index() {
        return pm10Index;
    }
    public void setCoIndex(Double coIndex) {
        this.coIndex = coIndex;
    }

    public void setNo2Index(Double no2Index) {
        this.no2Index = no2Index;
    }

    public void setOzoneIndex(Double ozoneIndex) {
        this.ozoneIndex = ozoneIndex;
    }

    public void setSo2Index(Double so2Index) {
        this.so2Index = so2Index;
    }

    public void setPm2_5Index(Double pm2_5Index) {
        this.pm2_5Index = pm2_5Index;
    }

    public void setPm10Index(Double pm10Index) {
        this.pm10Index = pm10Index;
    }
}
