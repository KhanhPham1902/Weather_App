package com.khanhpham.weatherapp.Functions;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CityName {
    public static String getCityName(Context context, double latitude, double longitude){
        String cityName = "Not found";
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        try{
            List<Address> addresses = gcd.getFromLocation(latitude,longitude,10);
            assert addresses != null;
            for (Address adr : addresses) {
                if (adr != null) { 
                    String city = adr.getLocality();
                    if (city != null && !city.equals("")) {
                        cityName = city;
                    } else {
                        Log.d("TAG", "CITY NOT FOUND");
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return cityName;
    }
}
