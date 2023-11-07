package com.khanhpham.weatherapp.Activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khanhpham.weatherapp.Functions.ChangeUI;
import com.khanhpham.weatherapp.Functions.WeatherImageManager;
import com.khanhpham.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DaysActivity extends AppCompatActivity{

    private String imgCode,times,status,sunrise,sunset,moonrise,moonset,moonPhase;
    private Double avgTemp,maxTemp,minTemp,avgHumidity,avgVision,
                    uv,totalPre,chanceRain,totalSnow,chanceSnow,maxWind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChangeUI.setSystemMode(this);

        setContentView(R.layout.activity_days);

        ImageView imgStatusOfDays = findViewById(R.id.imgStatusOfDays);
        TextView txtTimeDays = findViewById(R.id.txtTimeDays);
        TextView txtTempDays = findViewById(R.id.txtTempDays);
        TextView txtStatusOfDays = findViewById(R.id.txtStatusOfDays);
        TextView txtSunrise = findViewById(R.id.txtSunrise);
        TextView txtSunset = findViewById(R.id.txtSunset);
        TextView txtMoonrise = findViewById(R.id.txtMoonrise);
        TextView txtMoonset = findViewById(R.id.txtMoonset);
        TextView txtMoonPhase = findViewById(R.id.txtMoonPhase);
        TextView txtMaxTempDays = findViewById(R.id.txtMaxTempDays);
        TextView txtMinTempDays = findViewById(R.id.txtMinTempDays);
        TextView txtAvgHumidity = findViewById(R.id.txtAvgHumidity);
        TextView txtAvgVision = findViewById(R.id.txtAvgVision);
        TextView txtUvOfDays = findViewById(R.id.txtUvOfDays);
        TextView txtMaxWind = findViewById(R.id.txtWindMaxDays);
        TextView txtTotalPre = findViewById(R.id.txtTotalPre);
        TextView txtChanceOfRainDays = findViewById(R.id.txtChanceOfRainDays);
        TextView txtTotalSnow = findViewById(R.id.txtTotalSnow);
        TextView txtChanceOfSnow = findViewById(R.id.txtChanceOfSnow);
        ImageButton btnExit = findViewById(R.id.btnExit);

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
            txtTimeDays.setText(output.format(t));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Days
        txtTempDays.setText(String.format("%s°C", avgTemp));
        txtStatusOfDays.setText(status);
        txtMaxTempDays.setText(String.format("%s°C", maxTemp));
        txtMinTempDays.setText(String.format("%s°C", minTemp));
        txtAvgHumidity.setText(String.format("%s%%", avgHumidity));
        txtAvgVision.setText(String.format("%skm", avgVision));
        txtUvOfDays.setText(String.format("%s", uv));
        txtMaxWind.setText(String.format("%skm/h", maxWind));
        txtTotalPre.setText(String.format("%smm", totalPre));
        txtTotalSnow.setText(String.format("%scm", totalSnow));
        txtChanceOfRainDays.setText(String.format("%s%%", chanceRain));
        txtChanceOfSnow.setText(String.format("%s%%", chanceSnow));
        //Astronomy
        txtSunrise.setText(sunrise);
        txtSunset.setText(sunset);
        txtMoonrise.setText(moonrise);
        txtMoonset.setText(moonset);
        txtMoonPhase.setText(moonPhase);

        WeatherImageManager weatherImageManager = new WeatherImageManager();
        int imgDayResource = weatherImageManager.getDayImageResource(imgCode);
        Picasso.get().load(imgDayResource).into(imgStatusOfDays);

        //Thoat
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}