package com.khanhpham.weatherapp.Functions;

import com.khanhpham.weatherapp.R;

import java.util.HashMap;

public class WeatherImageManager {
    private final HashMap<String,Integer> weatherDayImageMap = new HashMap<>();
    private final HashMap<String,Integer> weatherNightImageMap = new HashMap<>();
    private final HashMap<String,Integer> weatherImageBgMap = new HashMap<>();
    private final HashMap<String,Integer> weatherImageBgMapNight = new HashMap<>();

    public WeatherImageManager() {
        initDay();
        initNight();
        weatherBgDay();
        weatherBgNight();
    }

    private void weatherBgDay(){
        weatherImageBgMap.put("1000", R.drawable.sunny_bg);
        weatherImageBgMap.put("1003", R.drawable.partly_cloudy_bg);
        weatherImageBgMap.put("1006", R.drawable.partly_cloudy_bg);
        weatherImageBgMap.put("1009", R.drawable.mist_bg);
        weatherImageBgMap.put("1030", R.drawable.mist_bg);
        weatherImageBgMap.put("1063", R.drawable.patchy_rain_possible_bg);
        weatherImageBgMap.put("1066", R.drawable.patchy_snow_possible_bg);
        weatherImageBgMap.put("1069", R.drawable.sleet_bg);
        weatherImageBgMap.put("1072", R.drawable.freezing_drizzle_bg);
        weatherImageBgMap.put("1087", R.drawable.thundery_outbreaks_bg);
        weatherImageBgMap.put("1114", R.drawable.blowing_snow_bg);
        weatherImageBgMap.put("1117", R.drawable.blizzard_bg);
        weatherImageBgMap.put("1135", R.drawable.mist_bg);
        weatherImageBgMap.put("1147", R.drawable.mist_bg);
        weatherImageBgMap.put("1150", R.drawable.light_drizzle_bg);
        weatherImageBgMap.put("1153", R.drawable.light_drizzle_bg);
        weatherImageBgMap.put("1168", R.drawable.freezing_drizzle_bg);
        weatherImageBgMap.put("1171", R.drawable.freezing_drizzle_bg);
        weatherImageBgMap.put("1180", R.drawable.light_rain_bg);
        weatherImageBgMap.put("1183", R.drawable.light_rain_bg);
        weatherImageBgMap.put("1186", R.drawable.moderate_rain_bg);
        weatherImageBgMap.put("1189", R.drawable.moderate_rain_bg);
        weatherImageBgMap.put("1192", R.drawable.heavy_rain_bg);
        weatherImageBgMap.put("1195", R.drawable.heavy_rain_bg);
        weatherImageBgMap.put("1198", R.drawable.freezing_drizzle_bg);
        weatherImageBgMap.put("1201", R.drawable.heavy_rain_bg);
        weatherImageBgMap.put("1204", R.drawable.sleet_bg);
        weatherImageBgMap.put("1207", R.drawable.sleet_bg);
        weatherImageBgMap.put("1210", R.drawable.patchy_snow_possible_bg);
        weatherImageBgMap.put("1213", R.drawable.patchy_snow_possible_bg);
        weatherImageBgMap.put("1216", R.drawable.patchy_snow_possible_bg);
        weatherImageBgMap.put("1219", R.drawable.blowing_snow_bg);
        weatherImageBgMap.put("1222", R.drawable.blowing_snow_bg);
        weatherImageBgMap.put("1225", R.drawable.blizzard_bg);
        weatherImageBgMap.put("1237", R.drawable.sleet_bg);
        weatherImageBgMap.put("1240", R.drawable.light_rain_bg);
        weatherImageBgMap.put("1243", R.drawable.moderate_rain_bg);
        weatherImageBgMap.put("1246", R.drawable.moderate_rain_bg);
        weatherImageBgMap.put("1249", R.drawable.sleet_bg);
        weatherImageBgMap.put("1252", R.drawable.sleet_bg);
        weatherImageBgMap.put("1255", R.drawable.blowing_snow_bg);
        weatherImageBgMap.put("1258", R.drawable.blowing_snow_bg);
        weatherImageBgMap.put("1261", R.drawable.sleet_bg);
        weatherImageBgMap.put("1264", R.drawable.sleet_bg);
        weatherImageBgMap.put("1273", R.drawable.thundery_outbreaks_bg);
        weatherImageBgMap.put("1276", R.drawable.thunder_rain_bg);
        weatherImageBgMap.put("1279", R.drawable.thundery_outbreaks_bg);
        weatherImageBgMap.put("1282", R.drawable.thunder_rain_bg);
    }

    private void weatherBgNight(){
        weatherImageBgMapNight.put("1000", R.drawable.clear_bg);
        weatherImageBgMapNight.put("1003", R.drawable.partly_cloudy_bg_n);
        weatherImageBgMapNight.put("1006", R.drawable.partly_cloudy_bg_n);
        weatherImageBgMapNight.put("1009", R.drawable.mist_bg);
        weatherImageBgMapNight.put("1030", R.drawable.mist_bg);
        weatherImageBgMapNight.put("1063", R.drawable.patchy_rain_possible_bg);
        weatherImageBgMapNight.put("1066", R.drawable.patchy_snow_possible_bg);
        weatherImageBgMapNight.put("1069", R.drawable.sleet_bg);
        weatherImageBgMapNight.put("1072", R.drawable.freezing_drizzle_bg);
        weatherImageBgMapNight.put("1087", R.drawable.thundery_outbreaks_bg);
        weatherImageBgMapNight.put("1114", R.drawable.blowing_snow_bg);
        weatherImageBgMapNight.put("1117", R.drawable.blizzard_bg);
        weatherImageBgMapNight.put("1135", R.drawable.mist_bg);
        weatherImageBgMapNight.put("1147", R.drawable.mist_bg);
        weatherImageBgMapNight.put("1150", R.drawable.light_drizzle_bg);
        weatherImageBgMapNight.put("1153", R.drawable.light_drizzle_bg);
        weatherImageBgMapNight.put("1168", R.drawable.freezing_drizzle_bg);
        weatherImageBgMapNight.put("1171", R.drawable.freezing_drizzle_bg);
        weatherImageBgMapNight.put("1180", R.drawable.light_rain_bg);
        weatherImageBgMapNight.put("1183", R.drawable.light_rain_bg);
        weatherImageBgMapNight.put("1186", R.drawable.moderate_rain_bg);
        weatherImageBgMapNight.put("1189", R.drawable.moderate_rain_bg);
        weatherImageBgMapNight.put("1192", R.drawable.heavy_rain_bg);
        weatherImageBgMapNight.put("1195", R.drawable.heavy_rain_bg);
        weatherImageBgMapNight.put("1198", R.drawable.freezing_drizzle_bg);
        weatherImageBgMapNight.put("1201", R.drawable.heavy_rain_bg);
        weatherImageBgMapNight.put("1204", R.drawable.sleet_bg);
        weatherImageBgMapNight.put("1207", R.drawable.sleet_bg);
        weatherImageBgMapNight.put("1210", R.drawable.patchy_snow_possible_bg);
        weatherImageBgMapNight.put("1213", R.drawable.patchy_snow_possible_bg);
        weatherImageBgMapNight.put("1216", R.drawable.patchy_snow_possible_bg);
        weatherImageBgMapNight.put("1219", R.drawable.blowing_snow_bg);
        weatherImageBgMapNight.put("1222", R.drawable.blowing_snow_bg);
        weatherImageBgMapNight.put("1225", R.drawable.blizzard_bg);
        weatherImageBgMapNight.put("1237", R.drawable.sleet_bg);
        weatherImageBgMapNight.put("1240", R.drawable.light_rain_bg);
        weatherImageBgMapNight.put("1243", R.drawable.moderate_rain_bg);
        weatherImageBgMapNight.put("1246", R.drawable.moderate_rain_bg);
        weatherImageBgMapNight.put("1249", R.drawable.sleet_bg);
        weatherImageBgMapNight.put("1252", R.drawable.sleet_bg);
        weatherImageBgMapNight.put("1255", R.drawable.blowing_snow_bg);
        weatherImageBgMapNight.put("1258", R.drawable.blowing_snow_bg);
        weatherImageBgMapNight.put("1261", R.drawable.sleet_bg);
        weatherImageBgMapNight.put("1264", R.drawable.sleet_bg);
        weatherImageBgMapNight.put("1273", R.drawable.thundery_outbreaks_bg);
        weatherImageBgMapNight.put("1276", R.drawable.thunder_rain_bg);
        weatherImageBgMapNight.put("1279", R.drawable.thundery_outbreaks_bg);
        weatherImageBgMapNight.put("1282", R.drawable.thunder_rain_bg);
    }

    private void initNight() {
        weatherDayImageMap.put("1000", R.drawable.sunny);
        weatherDayImageMap.put("1003", R.drawable.partly_cloudy);
        weatherDayImageMap.put("1006", R.drawable.cloudy);
        weatherDayImageMap.put("1009", R.drawable.overcast);
        weatherDayImageMap.put("1030", R.drawable.mist);
        weatherDayImageMap.put("1063", R.drawable.patchy_rain_possible);
        weatherDayImageMap.put("1066", R.drawable.patchy_snow_possible);
        weatherDayImageMap.put("1069", R.drawable.patchy_sleet_possible);
        weatherDayImageMap.put("1072", R.drawable.patchy_freezing_drizzle_possible);
        weatherDayImageMap.put("1087", R.drawable.thundery_outbreaks_possible);
        weatherDayImageMap.put("1114", R.drawable.blowing_snow);
        weatherDayImageMap.put("1117", R.drawable.blizzard);
        weatherDayImageMap.put("1135", R.drawable.fog);
        weatherDayImageMap.put("1147", R.drawable.freezing_fog);
        weatherDayImageMap.put("1150", R.drawable.patchy_light_drizzle);
        weatherDayImageMap.put("1153", R.drawable.light_drizzle);
        weatherDayImageMap.put("1168", R.drawable.freezing_drizzle);
        weatherDayImageMap.put("1171", R.drawable.heavy_freezing_drizzle);
        weatherDayImageMap.put("1180", R.drawable.patchy_light_rain);
        weatherDayImageMap.put("1183", R.drawable.light_rain);
        weatherDayImageMap.put("1186", R.drawable.moderate_rain_at_times);
        weatherDayImageMap.put("1189", R.drawable.moderate_rain);
        weatherDayImageMap.put("1192", R.drawable.heavy_rain_at_times);
        weatherDayImageMap.put("1195", R.drawable.heavy_rain);
        weatherDayImageMap.put("1198", R.drawable.light_freezing_rain);
        weatherDayImageMap.put("1201", R.drawable.moderate_or_heavy_freezing_rain);
        weatherDayImageMap.put("1204", R.drawable.light_sleet);
        weatherDayImageMap.put("1207", R.drawable.moderate_or_heavy_sleet);
        weatherDayImageMap.put("1210", R.drawable.patchy_light_snow);
        weatherDayImageMap.put("1213", R.drawable.light_snow);
        weatherDayImageMap.put("1216", R.drawable.patchy_moderate_snow);
        weatherDayImageMap.put("1219", R.drawable.moderate_snow);
        weatherDayImageMap.put("1222", R.drawable.patchy_heavy_snow);
        weatherDayImageMap.put("1225", R.drawable.heavy_snow);
        weatherDayImageMap.put("1237", R.drawable.ice_pellets);
        weatherDayImageMap.put("1240", R.drawable.light_rain_shower);
        weatherDayImageMap.put("1243", R.drawable.moderate_or_heavy_rain_shower);
        weatherDayImageMap.put("1246", R.drawable.torrential_rain_shower);
        weatherDayImageMap.put("1249", R.drawable.light_sleet_showers);
        weatherDayImageMap.put("1252", R.drawable.moderate_or_heavy_sleet_showers);
        weatherDayImageMap.put("1255", R.drawable.light_snow_showers);
        weatherDayImageMap.put("1258", R.drawable.moderate_or_heavy_snow_showers);
        weatherDayImageMap.put("1261", R.drawable.light_showers_of_ice_pellets);
        weatherDayImageMap.put("1264", R.drawable.moderate_or_heavy_showers_of_ice_pellets);
        weatherDayImageMap.put("1273", R.drawable.patchy_light_rain_with_thunder);
        weatherDayImageMap.put("1276", R.drawable.moderate_or_heavy_rain_with_thunder);
        weatherDayImageMap.put("1279", R.drawable.patchy_light_snow_with_thunder);
        weatherDayImageMap.put("1282", R.drawable.moderate_or_heavy_snow_with_thunder);
    }

    private void initDay() {
        weatherNightImageMap.put("1000", R.drawable.clear);
        weatherNightImageMap.put("1003", R.drawable.partly_cloudy_n);
        weatherNightImageMap.put("1006", R.drawable.cloudy);
        weatherNightImageMap.put("1009", R.drawable.overcast);
        weatherNightImageMap.put("1030", R.drawable.mist);
        weatherNightImageMap.put("1063", R.drawable.patchy_rain_possible_n);
        weatherNightImageMap.put("1066", R.drawable.patchy_snow_possible_n);
        weatherNightImageMap.put("1069", R.drawable.patchy_sleet_possible_n);
        weatherNightImageMap.put("1072", R.drawable.patchy_freezing_drizzle_possible_n);
        weatherNightImageMap.put("1087", R.drawable.thundery_outbreaks_possible_n);
        weatherNightImageMap.put("1114", R.drawable.blowing_snow);
        weatherNightImageMap.put("1117", R.drawable.blizzard);
        weatherNightImageMap.put("1135", R.drawable.fog);
        weatherNightImageMap.put("1147", R.drawable.freezing_frog_n);
        weatherNightImageMap.put("1150", R.drawable.patchy_light_drizzle);
        weatherNightImageMap.put("1153", R.drawable.light_drizzle);
        weatherNightImageMap.put("1168", R.drawable.freezing_drizzle);
        weatherNightImageMap.put("1171", R.drawable.heavy_freezing_drizzle);
        weatherNightImageMap.put("1180", R.drawable.patchy_light_rain);
        weatherNightImageMap.put("1183", R.drawable.light_rain);
        weatherNightImageMap.put("1186", R.drawable.moderate_rain_at_times_n);
        weatherNightImageMap.put("1189", R.drawable.moderate_rain);
        weatherNightImageMap.put("1192", R.drawable.heavy_rain_at_times_n);
        weatherNightImageMap.put("1195", R.drawable.heavy_rain);
        weatherNightImageMap.put("1198", R.drawable.light_freezing_rain);
        weatherNightImageMap.put("1201", R.drawable.moderate_or_heavy_freezing_rain);
        weatherNightImageMap.put("1204", R.drawable.light_sleet);
        weatherNightImageMap.put("1207", R.drawable.moderate_or_heavy_sleet);
        weatherNightImageMap.put("1210", R.drawable.patchy_light_snow_n);
        weatherNightImageMap.put("1213", R.drawable.light_snow);
        weatherNightImageMap.put("1216", R.drawable.patchy_moderate_snow_n);
        weatherNightImageMap.put("1219", R.drawable.moderate_snow);
        weatherNightImageMap.put("1222", R.drawable.patchy_heavy_snow_n);
        weatherNightImageMap.put("1225", R.drawable.heavy_snow);
        weatherNightImageMap.put("1237", R.drawable.ice_pellets);
        weatherNightImageMap.put("1240", R.drawable.light_rain_shower);
        weatherNightImageMap.put("1243", R.drawable.moderate_or_heavy_rain_shower_n);
        weatherNightImageMap.put("1246", R.drawable.torrential_rain_shower);
        weatherNightImageMap.put("1249", R.drawable.light_sleet_showers);
        weatherNightImageMap.put("1252", R.drawable.moderate_or_heavy_sleet_showers);
        weatherNightImageMap.put("1255", R.drawable.light_snow_showers);
        weatherNightImageMap.put("1258", R.drawable.moderate_or_heavy_snow_showers);
        weatherNightImageMap.put("1261", R.drawable.light_showers_of_ice_pellets);
        weatherNightImageMap.put("1264", R.drawable.moderate_or_heavy_showers_of_ice_pellets);
        weatherNightImageMap.put("1273", R.drawable.patchy_light_rain_with_thunder);
        weatherNightImageMap.put("1276", R.drawable.moderate_or_heavy_rain_with_thunder);
        weatherNightImageMap.put("1279", R.drawable.patchy_light_snow_with_thunder);
        weatherNightImageMap.put("1282", R.drawable.moderate_or_heavy_snow_with_thunder);
    }

    public int getDayImageResource(String code){
        if(weatherDayImageMap.containsKey(code)) {
            Integer imgResource = weatherDayImageMap.get(code);
            return imgResource != null ? imgResource : R.drawable.sunny;
        }else{
            return R.drawable.sunny;
        }
    }

    public int getNightImageResource(String code){
        if(weatherNightImageMap.containsKey(code)) {
            Integer imgResource = weatherNightImageMap.get(code);
            return imgResource != null ? imgResource : R.drawable.clear;
        }else{
            return R.drawable.clear;
        }
    }

    public int getDayBgResource(String code){
        if(weatherImageBgMap.containsKey(code)) {
            Integer imgResource = weatherImageBgMap.get(code);
            return imgResource != null ? imgResource : R.drawable.sunny_bg;
        }else{
            return R.drawable.sunny_bg;
        }
    }

    public int getNightBgResource(String code){
        if(weatherImageBgMapNight.containsKey(code)) {
            Integer imgResource = weatherImageBgMapNight.get(code);
            return imgResource != null ? imgResource : R.drawable.clear_bg;
        }else{
            return R.drawable.clear_bg;
        }
    }
}
