package com.khanhpham.weatherapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("forecast.json")
    Call<WeatherResponse> getWeatherInfo(
            @Query("key") String apiKey,
            @Query("q") String cityName,
            @Query("days") int days,
            @Query("lang") String language,
            @Query("aqi") String aqi
    );
}
