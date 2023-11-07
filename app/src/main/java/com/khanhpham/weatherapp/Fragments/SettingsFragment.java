package com.khanhpham.weatherapp.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.khanhpham.weatherapp.Functions.ChangeUI;
import com.khanhpham.weatherapp.R;

public class SettingsFragment extends Fragment {

    ImageButton buttonExit;
    RadioGroup rbGroupDisplay;
    RadioButton rbEnglish,rbVietnamese,rbKph,rbMph,rbCelsius,rbFahrenheit,rbLightMode,rbNightMode,rbSystemMode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        rbGroupDisplay = view.findViewById(R.id.rbGroupDisplay);
        buttonExit = view.findViewById(R.id.buttonExit);
        rbEnglish = view.findViewById(R.id.rbEnglish);
        rbVietnamese = view.findViewById(R.id.rbVietnamese);
        rbKph = view.findViewById(R.id.rbKph);
        rbMph = view.findViewById(R.id.rbMph);
        rbCelsius = view.findViewById(R.id.rbCelsius);
        rbFahrenheit = view.findViewById(R.id.rbFahrenheit);
        rbLightMode = view.findViewById(R.id.rbLightMode);
        rbNightMode = view.findViewById(R.id.rbNightMode);
        rbSystemMode = view.findViewById(R.id.rbSystemMode);

        Activity activity = getActivity();
        if(ChangeUI.checkNightMode(activity)){
            buttonExit.setImageResource(R.drawable.back_white);
        }

        //back to activity
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(SettingsFragment.this).commit();
            }
        });

        return view;
    }
}