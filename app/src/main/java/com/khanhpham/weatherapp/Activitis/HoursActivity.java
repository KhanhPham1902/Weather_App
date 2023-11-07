package com.khanhpham.weatherapp.Activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.khanhpham.weatherapp.Functions.ChangeUI;
import com.khanhpham.weatherapp.Functions.WeatherImageManager;
import com.khanhpham.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HoursActivity extends AppCompatActivity{

    private ImageButton btnExit;
    private ImageView imgStatusOfHour;
    private TextView txtDetailHours, txtStatusOfHours, txtTempHours, txtTempFeelsLikeOfHours, txtWindMax,
            txtWindDirOfHours, txtWindDeOfHours, txtWindGustOfHours, txtHumidityHours, txtPressureOfHours,
            txtPrecipitationOfHours, txtChanceOfRain, txtUVOfHours, txtVisionOfHours, txtCloudCoverOfHours;
    private String detailHours, statusHours, iconCodeHours, windDirHours;
    private Double tempHours, flOfHours, windMaxHours, windDeHours, windGustHours, humidityHours,
            presHours, preHours, chanceRainHours, uvHours, visionHours, cloudCoverHours;
    private Integer isDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChangeUI.setSystemMode(this);
        setContentView(R.layout.activity_hours);

        btnExit = findViewById(R.id.btnExit);
        imgStatusOfHour = findViewById(R.id.imgStatusOfHour);
        txtDetailHours = findViewById(R.id.txtDetailHours);
        txtStatusOfHours = findViewById(R.id.txtStatusOfHours);
        txtTempHours = findViewById(R.id.txtTempHours);
        txtTempFeelsLikeOfHours = findViewById(R.id.txtTempFeelsLikeOfHours);
        txtWindMax = findViewById(R.id.txtWindMax);
        txtWindDirOfHours = findViewById(R.id.txtWindDirOfHours);
        txtWindDeOfHours = findViewById(R.id.txtWindDeOfHours);
        txtWindGustOfHours = findViewById(R.id.txtWindGustOfHours);
        txtHumidityHours = findViewById(R.id.txtHumidityHours);
        txtPressureOfHours = findViewById(R.id.txtPressureOfHours);
        txtPrecipitationOfHours = findViewById(R.id.txtPrecipitationOfHours);
        txtChanceOfRain = findViewById(R.id.txtChanceOfRain);
        txtUVOfHours = findViewById(R.id.txtUVOfHours);
        txtVisionOfHours = findViewById(R.id.txtVisionOfHours);
        txtCloudCoverOfHours = findViewById(R.id.txtCloudCoverOfHours);

        if(ChangeUI.checkNightMode(this)){
            btnExit.setImageResource(R.drawable.back_white);
        }

        Intent intent = getIntent();
        detailHours = intent.getStringExtra("times");
        statusHours = intent.getStringExtra("status");
        tempHours = intent.getDoubleExtra("temp", 0);
        flOfHours = intent.getDoubleExtra("feelslike", 0);
        iconCodeHours = intent.getStringExtra("icon");
        windMaxHours = intent.getDoubleExtra("windmax", 0);
        humidityHours = intent.getDoubleExtra("humidity", 0);
        uvHours = intent.getDoubleExtra("uv", 0);
        windDeHours = intent.getDoubleExtra("winddegree", 0);
        windDirHours = intent.getStringExtra("winddir");
        presHours = intent.getDoubleExtra("pressure", 0);
        preHours = intent.getDoubleExtra("precipitation", 0);
        cloudCoverHours = intent.getDoubleExtra("cloudcover", 0);
        chanceRainHours = intent.getDoubleExtra("chancerain", 0);
        visionHours = intent.getDoubleExtra("vision", 0);
        windGustHours = intent.getDoubleExtra("windgust", 0);
        isDay = intent.getIntExtra("isDay", 0);

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm | dd-MM-yyyy");
        try {
            Date t = input.parse(detailHours);
            txtDetailHours.setText(output.format(t));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtStatusOfHours.setText(statusHours);
        txtTempHours.setText(String.format("%s°C", tempHours));
        txtTempFeelsLikeOfHours.setText(String.format("Cảm giác như %s°C", flOfHours));
        txtWindMax.setText(String.format("%skm/h", windMaxHours));
        txtHumidityHours.setText(String.format("%s%%", humidityHours));
        txtUVOfHours.setText(String.format("%s", uvHours));
        txtWindDeOfHours.setText(String.format("%s°", windDeHours));
        txtWindDirOfHours.setText(String.format("%s/", windDirHours));
        txtPressureOfHours.setText(String.format("%shPa", presHours));
        txtPrecipitationOfHours.setText(String.format("%smm", preHours));
        txtCloudCoverOfHours.setText(String.format("%s%%", cloudCoverHours));
        txtChanceOfRain.setText(String.format("%s%%", chanceRainHours));
        txtVisionOfHours.setText(String.format("%skm", visionHours));
        txtWindGustOfHours.setText(String.format("%skm/h", windGustHours));

        WeatherImageManager weatherImageManager = new WeatherImageManager();
        if (isDay == 1) {
            int imgDayResource = weatherImageManager.getDayImageResource(iconCodeHours);
            Picasso.get().load(imgDayResource).into(imgStatusOfHour);
        } else {
            int imgNightResource = weatherImageManager.getNightImageResource(iconCodeHours);
            Picasso.get().load(imgNightResource).into(imgStatusOfHour);
        }

        //Thoat
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}