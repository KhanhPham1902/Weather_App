package com.khanhpham.weatherapp.Activitis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.khanhpham.weatherapp.Adapters.HourAdapters;
import com.khanhpham.weatherapp.Domains.Forecastday;
import com.khanhpham.weatherapp.Domains.Hour;
import com.khanhpham.weatherapp.Domains.WeatherApiData;
import com.khanhpham.weatherapp.Domains.WeatherCondition;
import com.khanhpham.weatherapp.Functions.ChangeUI;
import com.khanhpham.weatherapp.Functions.NetworkCheck;
import com.khanhpham.weatherapp.Functions.OnItemClickListener;
import com.khanhpham.weatherapp.Functions.UppercaseFormat;
import com.khanhpham.weatherapp.R;
import com.khanhpham.weatherapp.Retrofit.WeatherApi;
import com.khanhpham.weatherapp.Retrofit.WeatherResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HourlyActivity extends AppCompatActivity implements OnItemClickListener {

    private LinearLayout hourlyLL,topLLHour;
    private ArrayList<Hour> hourArrayList;
    private HourAdapters hourAdapters;
    private TextView txtTime,txtLocateHourly,txtCountryHourly;
    private RecyclerView recyclerView;
    private String cityName;
    private String coordinates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChangeUI.setSystemMode(this);
        setContentView(R.layout.activity_hourly);

        hourlyLL = findViewById(R.id.HourlyLL);
        topLLHour = findViewById(R.id.topLLHour);
        txtTime = findViewById(R.id.txtTimeHourly);
        txtLocateHourly = findViewById(R.id.txtLocateHourly);
        txtCountryHourly = findViewById(R.id.txtCountryHourly);
        recyclerView = findViewById(R.id.viewHourly);
        BottomNavigationView bottomView = findViewById(R.id.bottomView);

        //RecycleView
        hourArrayList = new ArrayList<>();
        hourAdapters = new HourAdapters(this,hourArrayList,this);
        recyclerView.setAdapter(hourAdapters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        Intent intent = getIntent();
        if(intent!=null){
            cityName = intent.getStringExtra("CITYNAME");
            coordinates = intent.getStringExtra("COORDINATE");
            String city = UppercaseFormat.getUppercase(cityName);
            txtLocateHourly.setText(city);
            getWeather(coordinates);
        }

        //Bottom Navigation View
        bottomView.setSelectedItemId(R.id.bottom_hourly);
        bottomView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_hourly:
                    return true;
                case R.id.bottom_daily:
                    Intent intent1 = new Intent(getApplicationContext(),FutureActivity.class);
                    intent1.putExtra("CITYNAME",cityName);
                    intent1.putExtra("COORDINATE",coordinates);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    startActivity(intent1);
                    finish();
                    return true;
                case R.id.bottom_current:
                    Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                    intent2.putExtra("CITYNAME",cityName);
                    intent2.putExtra("COORDINATE",coordinates);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    setResult(Activity.RESULT_OK,intent2);
                    finish();
                    return true;
                case R.id.bottom_air_quality:
                    Intent intent3 = new Intent(getApplicationContext(),AirQualityActivity.class);
                    intent3.putExtra("CITYNAME",cityName);
                    intent3.putExtra("COORDINATE",coordinates);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    startActivity(intent3);
                    finish();
                    return true;
            }
            return false;
        });
    }

    //Lay thong tin thoi tiet
    private void getWeather(String cityName) {
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
        hourlyLL.setVisibility(View.GONE);
        topLLHour.setVisibility(View.GONE);

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeatherInfo(API_KEY,cityName,DAYS,LANG,AQI);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                hourArrayList.clear();
                customProgressBar.dismiss();
                hourlyLL.setVisibility(View.VISIBLE);
                topLLHour.setVisibility(View.VISIBLE);
                if(response.isSuccessful()){
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        txtCountryHourly.setText(weatherResponse.getLocationApi().getCountry());
                        Date currentDate = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
                        String dayName = simpleDateFormat.format(currentDate);
                        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        SimpleDateFormat output = new SimpleDateFormat("dd/MM");
                        try {
                            Date t = input.parse(weatherResponse.getLocationApi().getLocaltime());
                            assert t != null;
                            String day = output.format(t);
                            txtTime.setText(dayName+", "+day);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Forecastday forecastday = weatherResponse.getForecast().getForecastday().get(0);
                        ArrayList<Hour> hourArray = forecastday.getHours();
                        for (int i=0; i<hourArray.size(); i++) {
                            Hour hour = hourArray.get(i);
                            String time = hour.getTime();
                            Double temp = hour.getTemp();
                            String img = hour.getWeatherCondition().getCode();
                            String status = hour.getWeatherCondition().getText();
                            WeatherCondition weatherCondition = new WeatherCondition(status, img);
                            weatherCondition.setText(status);
                            weatherCondition.setCode(img);
                            Double windSpeedHour = hour.getWindSpeed();
                            Double humidityHour = hour.getHumidity();
                            Integer is_Day = hour.getIsDay();
                            Double windDeHours = hour.getWindDegree();
                            String  windDirHours = hour.getWindDirection();
                            Double presHours = hour.getPressure();
                            Double preHours = hour.getPrecipitation();
                            Double cloudCoverHours = hour.getCloudCover();
                            Double chanceRainHours = hour.getChanceOfRain();
                            Double visionHours = hour.getVision();
                            Double windGustHours = hour.getWindGust();
                            Double flOfHours = hour.getFeelsLike();
                            Double uvHours = hour.getUv();
                            hourArrayList.add(new Hour(time, temp, weatherCondition,windSpeedHour, humidityHour,is_Day,
                                    windDeHours,windDirHours,presHours,preHours,cloudCoverHours,chanceRainHours,visionHours,windGustHours,flOfHours,uvHours));
                        }
                        hourAdapters.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(HourlyActivity.this, "Vui lòng nhập tên địa điểm hợp lệ!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                customProgressBar.dismiss();
                hourlyLL.setVisibility(View.VISIBLE);
                topLLHour.setVisibility(View.VISIBLE);
                Toast.makeText(HourlyActivity.this, "Lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(HourlyActivity.this,HoursActivity.class);
        intent.putExtra("times",hourArrayList.get(position).getTime());
        intent.putExtra("status",hourArrayList.get(position).getWeatherCondition().getText());
        intent.putExtra("temp",hourArrayList.get(position).getTemp());
        intent.putExtra("feelslike",hourArrayList.get(position).getFeelsLike());
        intent.putExtra("icon",hourArrayList.get(position).getWeatherCondition().getCode());
        intent.putExtra("windmax",hourArrayList.get(position).getWindSpeed());
        intent.putExtra("humidity",hourArrayList.get(position).getHumidity());
        intent.putExtra("uv",hourArrayList.get(position).getUv());
        intent.putExtra("winddegree",hourArrayList.get(position).getWindDegree());
        intent.putExtra("winddir",hourArrayList.get(position).getWindDirection());
        intent.putExtra("pressure",hourArrayList.get(position).getPressure());
        intent.putExtra("precipitation",hourArrayList.get(position).getPrecipitation());
        intent.putExtra("cloudcover",hourArrayList.get(position).getCloudCover());
        intent.putExtra("chancerain",hourArrayList.get(position).getChanceOfRain());
        intent.putExtra("vision",hourArrayList.get(position).getVision());
        intent.putExtra("windgust",hourArrayList.get(position).getWindGust());
        intent.putExtra("isDay",hourArrayList.get(position).getIsDay());
        startActivity(intent);
    }
}