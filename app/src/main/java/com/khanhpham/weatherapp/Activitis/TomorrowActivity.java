package com.khanhpham.weatherapp.Activitis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.khanhpham.weatherapp.Functions.ChangeUI;
import com.khanhpham.weatherapp.Functions.WeatherImageManager;
import com.khanhpham.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TomorrowActivity extends AppCompatActivity{

    private String imgCode, times, status, sunrise, sunset, moonrise, moonset, moonPhase;
    private Double avgTemp, maxTemp, minTemp, avgHumidity, avgVision,
            uv, totalPre, chanceRain, totalSnow, chanceSnow, maxWind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChangeUI.setSystemMode(this);
        setContentView(R.layout.activity_tomorrow);

        ImageButton btnExit = findViewById(R.id.btnExits);
        ImageView img = findViewById(R.id.imgStatusTom);
        TextView txtTimeTom = findViewById(R.id.txtTimeTom);
        TextView txtTempOfTom = findViewById(R.id.txtTempOfTom);
        TextView txtStatusOfTom = findViewById(R.id.txtStatusOfTom);
        TextView txtSunriseTom = findViewById(R.id.txtSunriseTom);
        TextView txtSunsetTom = findViewById(R.id.txtSunsetTom);
        TextView txtMoonriseTom = findViewById(R.id.txtMoonriseTom);
        TextView txtMoonsetTom = findViewById(R.id.txtMoonsetTom);
        TextView txtMoonPhaseTom = findViewById(R.id.txtMoonPhaseTom);
        TextView txtMaxTempDaysTom =findViewById(R.id.txtMaxTempDaysTom);
        TextView txtMinTempDaysTom =findViewById(R.id.txtMinTempDaysTom);
        TextView txtWindMaxTom = findViewById(R.id.txtWindMaxTom);
        TextView txtAvgHumidityTom =findViewById(R.id.txtAvgHumidityTom);
        TextView txtAvgVisionTom =findViewById(R.id.txtAvgVisionTom);
        TextView txtUvOfDaysTom = findViewById(R.id.txtUvOfDaysTom);
        TextView txtTotalPreTom = findViewById(R.id.txtTotalPreTom);
        TextView txtChanceOfRainTom = findViewById(R.id.txtChanceOfRainTom);
        TextView txtTotalSnowTom = findViewById(R.id.txtTotalSnowTom);
        TextView txtChanceOfSnowTom = findViewById(R.id.txtChanceOfSnowTom);

        if(ChangeUI.checkNightMode(this)){
            btnExit.setImageResource(R.drawable.back_white);
        }

        Intent intent = getIntent();
        if(intent!=null) {
            //Days
            times = intent.getStringExtra("time");
            imgCode = intent.getStringExtra("img");
            avgTemp = intent.getDoubleExtra("temp", 0);
            status = intent.getStringExtra("status");
            maxTemp = intent.getDoubleExtra("tempmax", 0);
            minTemp = intent.getDoubleExtra("tempmin", 0);
            uv = intent.getDoubleExtra("uv", 0);
            maxWind = intent.getDoubleExtra("maxWind", 0);
            avgHumidity = intent.getDoubleExtra("avgHumidity", 0);
            avgVision = intent.getDoubleExtra("avgVision", 0);
            totalPre = intent.getDoubleExtra("totalPre", 0);
            chanceRain = intent.getDoubleExtra("chanceRain", 0);
            totalSnow = intent.getDoubleExtra("totalSnow", 0);
            chanceSnow = intent.getDoubleExtra("chanceSnow", 0);
            //Astronomy
            sunrise = intent.getStringExtra("sunrise");
            sunset = intent.getStringExtra("sunset");
            moonrise = intent.getStringExtra("moonrise");
            moonset = intent.getStringExtra("moonset");
            moonPhase = intent.getStringExtra("moonphase");
        }else{
            Toast.makeText(this, "Có lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
        }

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date t = input.parse(times);
            assert t != null;
            txtTimeTom.setText(output.format(t));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Days
        txtTempOfTom.setText(String.format("%s°C", avgTemp));
        txtStatusOfTom.setText(status);
        txtMaxTempDaysTom.setText(String.format("%s°C", maxTemp));
        txtMinTempDaysTom.setText(String.format("%s°C", minTemp));
        txtAvgHumidityTom.setText(String.format("%s%%", avgHumidity));
        txtAvgVisionTom.setText(String.format("%skm", avgVision));
        txtUvOfDaysTom.setText(String.format("%s", uv));
        txtWindMaxTom.setText(String.format("%skm/h", maxWind));
        txtTotalPreTom.setText(String.format("%smm", totalPre));
        txtTotalSnowTom.setText(String.format("%scm", totalSnow));
        txtChanceOfRainTom.setText(String.format("%s%%", chanceRain));
        txtChanceOfSnowTom.setText(String.format("%s%%", chanceSnow));
        //Astronomy
        txtSunriseTom.setText(sunrise);
        txtSunsetTom.setText(sunset);
        txtMoonriseTom.setText(moonrise);
        txtMoonsetTom.setText(moonset);
        txtMoonPhaseTom.setText(moonPhase);

        WeatherImageManager weatherImageManager = new WeatherImageManager();
        int imgDayResource = weatherImageManager.getDayImageResource(imgCode);
        Picasso.get().load(imgDayResource).into(img);

        //Thoat
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}