package com.khanhpham.weatherapp.Activitis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.khanhpham.weatherapp.Functions.ChangeUI;
import com.khanhpham.weatherapp.R;

import java.util.Arrays;

public class SearchActivity extends AppCompatActivity{
    private String cityName;
    private String coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChangeUI.setSystemMode(this);
        setContentView(R.layout.activity_search);

        //Fragment
        if(!Places.isInitialized()){
            Places.initialize(getApplicationContext(),"AIzaSyAMYVmKyvp6cKn4acskurJOd3Y2y-GngDQ");
        }

        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        assert autocompleteSupportFragment != null;
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME));

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onError(@NonNull Status status) {
                assert status.getStatusMessage() != null;
                Log.e("error", status.getStatusMessage());
            }

            @Override
            public void onPlaceSelected(Place place) {
                if (place.getLatLng() != null) {
                    LatLng latLng = place.getLatLng();
                    double latitude = latLng.latitude;
                    double longitude = latLng.longitude;
                    coordinates = latitude + "," +longitude;
                }
                cityName = place.getName();
                Intent intent = new Intent();
                intent.putExtra("COORDINATE", coordinates);
                intent.putExtra("CITYNAME", cityName);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}