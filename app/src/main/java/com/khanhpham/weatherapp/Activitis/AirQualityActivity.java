package com.khanhpham.weatherapp.Activitis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.khanhpham.weatherapp.Domains.AirQuality;
import com.khanhpham.weatherapp.Domains.WeatherApiData;
import com.khanhpham.weatherapp.Functions.ChangeUI;
import com.khanhpham.weatherapp.Functions.UppercaseFormat;
import com.khanhpham.weatherapp.R;
import com.khanhpham.weatherapp.Retrofit.WeatherApi;
import com.khanhpham.weatherapp.Retrofit.WeatherResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AirQualityActivity extends AppCompatActivity {
    private ArrayList<AirQuality> airQualityArrayList;
    private LinearLayout linearLayout, topLL;
    private ProgressBar progressBarPm2_5, progressBarNo2, progressBarO3, progressBarSo2, progressBarCO, progressBarPm10;
    private TextView txtPm2_5, txtNo2, txtO3, txtSo2, txtCO, txtPm10,
            txtAqiPm2_5, txtAqiNo2, txtAqiO3, txtAqiSo2, txtAqiCO, txtAqiPm10,
            txtAirStatusPM2_5, txtAirStatusNo2, txtAirStatusO3, txtAirStatusSo2, txtAirStatusCO, txtAirStatusPM10,
            txtLocateAir, txtCountryAir, txtAqi_description;
    private ImageView imgAqi;
    private String cityName;
    private String coordinates;
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String newCityname = data.getStringExtra("CITYNAME");
                    String newCoordinate = data.getStringExtra("COORDINATE");
                    cityName = newCityname;
                    coordinates = newCoordinate;
                    String city = UppercaseFormat.getUppercase(cityName);
                    txtLocateAir.setText(city);
                    getAirQuality(coordinates);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChangeUI.setSystemMode(this);

        setContentView(R.layout.activity_air_quality);

        linearLayout = findViewById(R.id.LinearLayout);
        topLL = findViewById(R.id.topLLAir);
        BottomNavigationView bottomView = findViewById(R.id.bottomView);
        imgAqi = findViewById(R.id.imgAqi);
        txtAqi_description = findViewById(R.id.txtAqi_description);
        progressBarCO = findViewById(R.id.progressBarCO);
        progressBarPm2_5 = findViewById(R.id.progressBarPm2_5);
        progressBarNo2 = findViewById(R.id.progressBarNo2);
        progressBarO3 = findViewById(R.id.progressBarO3);
        progressBarSo2 = findViewById(R.id.progressBarSo2);
        progressBarPm10 = findViewById(R.id.progressBarPm10);
        txtAqiPm2_5 = findViewById(R.id.txtAqiPm2_5);
        txtAqiCO = findViewById(R.id.txtAqiCO);
        txtAqiPm10 = findViewById(R.id.txtAqiPm10);
        txtAqiNo2 = findViewById(R.id.txtAqiNo2);
        txtAqiO3 = findViewById(R.id.txtAqiO3);
        txtAqiSo2 = findViewById(R.id.txtAqiSo2);
        txtPm2_5 = findViewById(R.id.txtPm2_5);
        txtPm10 = findViewById(R.id.txtPm10);
        txtCO = findViewById(R.id.txtCO);
        txtNo2 = findViewById(R.id.txtNo2);
        txtSo2 = findViewById(R.id.txtSo2);
        txtO3 = findViewById(R.id.txtO3);
        txtAirStatusPM2_5 = findViewById(R.id.txtAirStatusPM2_5);
        txtAirStatusNo2 = findViewById(R.id.txtAirStatusNo2);
        txtAirStatusO3 = findViewById(R.id.txtAirStatusO3);
        txtAirStatusSo2 = findViewById(R.id.txtAirStatusSo2);
        txtAirStatusCO = findViewById(R.id.txtAirStatusCO);
        txtAirStatusPM10 = findViewById(R.id.txtAirStatusPM10);
        txtCountryAir = findViewById(R.id.txtCountryAir);
        txtLocateAir = findViewById(R.id.txtLocateAir);
        ImageView imgSearch = findViewById(R.id.imgSearch);

        Intent getIntent = getIntent();
        if (getIntent != null) {
            cityName = getIntent.getStringExtra("CITYNAME");
            coordinates = getIntent.getStringExtra("COORDINATE");
            String city = UppercaseFormat.getUppercase(cityName);
            txtLocateAir.setText(city);
            getAirQuality(coordinates);
        }

        //imageview event
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("CITYNAME", cityName);
                intent.putExtra("COORDINATE", coordinates);
                activityResultLauncher.launch(intent);
            }
        });

        //Bottom Navigation View
        bottomView.setSelectedItemId(R.id.bottom_air_quality);
        bottomView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_air_quality:
                    return true;
                case R.id.bottom_daily:
                    Intent intent1 = new Intent(getApplicationContext(), FutureActivity.class);
                    intent1.putExtra("CITYNAME",cityName);
                    intent1.putExtra("COORDINATE",coordinates);
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                    startActivity(intent1);
                    finish();
                    return true;
                case R.id.bottom_current:
                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                    intent2.putExtra("CITYNAME",cityName);
                    intent2.putExtra("COORDINATE",coordinates);
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                    activityResultLauncher.launch(intent2);
                    finish();
                    return true;
                case R.id.bottom_hourly:
                    Intent intent3 = new Intent(getApplicationContext(), HourlyActivity.class);
                    intent3.putExtra("CITYNAME",cityName);
                    intent3.putExtra("COORDINATE",coordinates);
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                    startActivity(intent3);
                    finish();
                    return true;
            }
            return false;
        });
    }

    //Lay du lieu chat luong khong khi
    private void getAirQuality(String cityName) {
        WeatherApiData weatherApiData = new WeatherApiData();
        String API_KEY = weatherApiData.getAPI_KEY();
        String BASE_URL = weatherApiData.getBASE_URL();
        String LANG = weatherApiData.getLANG();
        int DAYS = weatherApiData.getDAYS();
        String AQI = weatherApiData.getAQI();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CustomProgressBar customProgressBar = new CustomProgressBar(this);
        customProgressBar.show();
        linearLayout.setVisibility(View.GONE);
        topLL.setVisibility(View.GONE);

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeatherInfo(API_KEY, cityName, DAYS, LANG, AQI);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                customProgressBar.dismiss();
                linearLayout.setVisibility(View.VISIBLE);
                topLL.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        txtCountryAir.setText(weatherResponse.getLocationApi().getCountry());
                        AirQuality airQuality = weatherResponse.getCurrent().getAirQuality();
                        Double pm2_5 = airQuality.getPm2_5Index();
                        Double no2 = airQuality.getNo2Index();
                        Double o3 = airQuality.getOzoneIndex();
                        Double so2 = airQuality.getSo2Index();
                        Double co = airQuality.getCoIndex();
                        Double pm10 = airQuality.getPm10Index();
                        txtPm2_5.setText(String.format("%s μg/m3", pm2_5));
                        txtNo2.setText(String.format("%s μg/m3", no2));
                        txtO3.setText(String.format("%s μg/m3", o3));
                        txtSo2.setText(String.format("%s μg/m3", so2));
                        txtPm10.setText(String.format("%s μg/m3", pm10));
                        txtCO.setText(String.format("%s μg/m3", co));

                        //Chuyen don vi tu μg/m3 sang mg/m3
                        double mgCO = co/1000;

                        int aqiPm2_5 = (int) Math.round(pm2_5);
                        int aqiPm10 = (int) Math.round(pm10);
                        int aqiSo2 = (int) Math.round(so2);
                        int aqiCo = (int) Math.round(mgCO);
                        int aqiO3 = (int) Math.round(o3);
                        int aqiNo2 = (int) Math.round(no2);

                        int[] arr = {aqiCo,aqiNo2,aqiO3,aqiPm2_5,aqiPm10,aqiSo2};
                        int aqi = findMaxValue(arr); //chi so AQI bang gia tri aqi cua thong so lon nhat
                        changeImgAqi(imgAqi,txtAqi_description,aqi);

                        txtAqiPm2_5.setText(String.format("%s", aqiPm2_5));
                        txtAqiNo2.setText(String.format("%s", aqiNo2));
                        txtAqiSo2.setText(String.format("%s", aqiSo2));
                        txtAqiO3.setText(String.format("%s", aqiO3));
                        txtAqiCO.setText(String.format("%s", aqiCo));
                        txtAqiPm10.setText(String.format("%s", aqiPm10));

                        changeColorProgressPm2_5(progressBarPm2_5, txtAirStatusPM2_5, aqiPm2_5);
                        changeColorProgressPm10(progressBarPm10, txtAirStatusPM10, aqiPm10);
                        changeColorProgressNo2(progressBarNo2, txtAirStatusNo2, aqiNo2);
                        changeColorProgressSo2(progressBarSo2, txtAirStatusSo2, aqiSo2);
                        changeColorProgressO3(progressBarO3, txtAirStatusO3, aqiO3);
                        changeColorProgressCO(progressBarCO, txtAirStatusCO, aqiCo);
                    }
                } else {
                    Toast.makeText(AirQualityActivity.this, "Không tìm thấy địa điểm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                customProgressBar.dismiss();
                linearLayout.setVisibility(View.VISIBLE);
                topLL.setVisibility(View.VISIBLE);
                Toast.makeText(AirQualityActivity.this, "Lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Thay doi mau sac progressbar cua PM2_5
    private void changeColorProgressPm2_5(ProgressBar progressBar, TextView txt, int aqi) {
        if (0 < aqi && aqi <= 12) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.good));
            txt.setText(R.string.good);
        } else if (12.1 <= aqi && aqi <= 35.4) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.moderate));
            txt.setText(R.string.moderate);
        } else if (35.5 <= aqi && aqi <= 55.4) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.poor));
            txt.setText(R.string.poor);
        } else if (55.5 <= aqi && aqi <= 150.4) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.unhealthy));
            txt.setText(R.string.unhealthy);
        } else if (150.5 <= aqi && aqi <= 250.4) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.very_unhealthy));
            txt.setText(R.string.very_unhealthy);
        } else if (aqi >= 250.5) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.hazardous));
            txt.setText(R.string.hazardous);
        }
    }

    //Thay doi mau sac progressbar cua PM10
    private void changeColorProgressPm10(ProgressBar progressBar, TextView txt, int aqi) {
        if (0 < aqi && aqi <= 54) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.good));
            txt.setText(R.string.good);
        } else if (55 <= aqi && aqi <= 154) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.moderate));
            txt.setText(R.string.moderate);
        } else if (155 <= aqi && aqi <= 254) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.poor));
            txt.setText(R.string.poor);
        } else if (255 <= aqi && aqi <= 354) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.unhealthy));
            txt.setText(R.string.unhealthy);
        } else if (355 <= aqi && aqi <= 424) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.very_unhealthy));
            txt.setText(R.string.very_unhealthy);
        } else if (aqi > 424) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.hazardous));
            txt.setText(R.string.hazardous);
        }
    }

    //Thay doi mau sac progressbar cua NO2
    private void changeColorProgressNo2(ProgressBar progressBar, TextView txt, int aqi) {
        if (0 < aqi && aqi <= 40) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.good));
            txt.setText(R.string.good);
        } else if (41 <= aqi && aqi <= 100) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.moderate));
            txt.setText(R.string.moderate);
        } else if (101 <= aqi && aqi <= 200) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.poor));
            txt.setText(R.string.poor);
        } else if (201 <= aqi && aqi <= 400) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.unhealthy));
            txt.setText(R.string.unhealthy);
        } else if (aqi>=401) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.very_unhealthy));
            txt.setText(R.string.very_unhealthy);
        }
    }

    //Thay doi mau sac progressbar cua SO2
    private void changeColorProgressSo2(ProgressBar progressBar, TextView txt, int aqi) {
        if (0 < aqi && aqi <= 20) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.good));
            txt.setText(R.string.good);
        } else if (21 <= aqi && aqi <= 50) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.moderate));
            txt.setText(R.string.moderate);
        } else if (51 <= aqi && aqi <= 100) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.poor));
            txt.setText(R.string.poor);
        } else if (101 <= aqi && aqi <= 200) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.unhealthy));
            txt.setText(R.string.unhealthy);
        } else if (aqi>=201) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.very_unhealthy));
            txt.setText(R.string.very_unhealthy);
        }
    }

    //Thay doi mau sac progressbar cua O3
    private void changeColorProgressO3(ProgressBar progressBar, TextView txt, int aqi) {
        if (0 < aqi && aqi <= 60) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.good));
            txt.setText(R.string.good);
        } else if (61 <= aqi && aqi <= 120) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.moderate));
            txt.setText(R.string.moderate);
        } else if (121 <= aqi && aqi <= 180) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.poor));
            txt.setText(R.string.poor);
        } else if (181 <= aqi && aqi <= 240) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.unhealthy));
            txt.setText(R.string.unhealthy);
        } else if (aqi>=241) {
            progressBar.setProgress(aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.very_unhealthy));
            txt.setText(R.string.very_unhealthy);
        }
    }

    //Thay doi mau sac progressbar cua CO
    private void changeColorProgressCO(ProgressBar progressBar, TextView txt, double aqi) {
        if (0 < aqi && aqi <= 1) {
            progressBar.setProgress((int) aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.good));
            txt.setText(R.string.good);
        } else if (1.1 <= aqi && aqi <= 2) {
            progressBar.setProgress((int) aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.moderate));
            txt.setText(R.string.moderate);
        } else if (2.1 <= aqi && aqi <= 10) {
            progressBar.setProgress((int) aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.unhealthy));
            txt.setText(R.string.unhealthy);
        } else if (aqi>10) {
            progressBar.setProgress((int) aqi);
            progressBar.setProgressTintList(ContextCompat.getColorStateList(AirQualityActivity.this, R.color.very_unhealthy));
            txt.setText(R.string.very_unhealthy);
        }
    }

    //Thay doi hinh anh va mo ta AQI
    private void changeImgAqi(ImageView img,TextView txt,int aqi){
        if(0<aqi && aqi<=50){
            img.setImageResource(R.drawable.aqi_good);
            txt.setText(R.string.des_good);
        } else if (51<=aqi && aqi<=100) {
            img.setImageResource(R.drawable.aqi_moderate);
            txt.setText(R.string.des_moderate);
        }else if (101<=aqi && aqi<=150) {
            img.setImageResource(R.drawable.aqi_poor);
            txt.setText(R.string.des_poor);
        }else if (151<=aqi && aqi<=200) {
            img.setImageResource(R.drawable.aqi_unhealthy);
            txt.setText(R.string.des_unhealthy);
        }else if (201<=aqi && aqi<=300) {
            img.setImageResource(R.drawable.aqi_very_unhealthy);
            txt.setText(R.string.des_very_unhealthy);
        }else if (aqi>=301) {
            img.setImageResource(R.drawable.aqi_hazardous);
            txt.setText(R.string.des_hazadous);
        }
    }

    //Tim aqi max
    private int findMaxValue(int[] numbers) {
        int maxValue = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maxValue) {
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }
}