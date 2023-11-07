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
import com.khanhpham.weatherapp.Adapters.DayAdapters;
import com.khanhpham.weatherapp.Domains.Astro;
import com.khanhpham.weatherapp.Domains.Day;
import com.khanhpham.weatherapp.Domains.Forecast;
import com.khanhpham.weatherapp.Domains.Forecastday;
import com.khanhpham.weatherapp.Domains.WeatherApiData;
import com.khanhpham.weatherapp.Domains.WeatherCondition;
import com.khanhpham.weatherapp.Functions.ChangeUI;
import com.khanhpham.weatherapp.Functions.OnItemClickListener;
import com.khanhpham.weatherapp.Functions.UppercaseFormat;
import com.khanhpham.weatherapp.Functions.WeatherImageManager;
import com.khanhpham.weatherapp.R;
import com.khanhpham.weatherapp.Retrofit.WeatherApi;
import com.khanhpham.weatherapp.Retrofit.WeatherResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FutureActivity extends AppCompatActivity implements OnItemClickListener{
    private ArrayList<Day> dayArrayList;
    private ArrayList<Day> dayArrayListTom;
    private ArrayList<Astro> astroArrayList;
    private ArrayList<Astro> astroArrayListTom;
    private DayAdapters dayAdapters;
    private RecyclerView recyclerView;
    private ImageView imgTomorrow;
    private TextView txtTempTomorrow, txtConditionTomorrow, txtUVTomorrow, txtWindTomorrow, txtHumidityTomorrow,txtLocateFuture,txtCountryFuture;
    private LinearLayout futureLL,topLLFuture;
    private String cityName;
    private String coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChangeUI.setSystemMode(this);
        setContentView(R.layout.activity_future);

        imgTomorrow = findViewById(R.id.imgConditionTomorrow);
        txtConditionTomorrow = findViewById(R.id.txtConditionTomorrow);
        txtHumidityTomorrow = findViewById(R.id.txtHumidityTomorrow);
        txtTempTomorrow = findViewById(R.id.txtTempTomorrow);
        txtUVTomorrow = findViewById(R.id.txtUVTomorrow);
        txtWindTomorrow = findViewById(R.id.txtWindTomorrow);
        txtCountryFuture = findViewById(R.id.txtCountryFuture);
        txtLocateFuture = findViewById(R.id.txtLocateFuture);
        recyclerView = findViewById(R.id.viewFuture);
        futureLL = findViewById(R.id.futureLL);
        topLLFuture = findViewById(R.id.topLLFuture);
        BottomNavigationView bottomView = findViewById(R.id.bottomView);

        astroArrayListTom = new ArrayList<>();
        dayArrayListTom = new ArrayList<>();
        astroArrayList = new ArrayList<>();
        dayArrayList = new ArrayList<>();
        dayAdapters = new DayAdapters(this, dayArrayList, this);
        recyclerView.setAdapter(dayAdapters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Intent intent = getIntent();
        if(intent!=null) {
            cityName = intent.getStringExtra("CITYNAME");
            coordinates = intent.getStringExtra("COORDINATE");
            String city = UppercaseFormat.getUppercase(cityName);
            txtLocateFuture.setText(city);
            getWeather(coordinates);
        }

        //Bottom Navigation View
        bottomView.setSelectedItemId(R.id.bottom_daily);
        bottomView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_daily:
                    return true;
                case R.id.bottom_hourly:
                    Intent intent1 = new Intent(getApplicationContext(),HourlyActivity.class);
                    intent1.putExtra("CITYNAME",cityName);
                    intent1.putExtra("COORDINATE",coordinates);
                    overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
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
        futureLL.setVisibility(View.GONE);
        topLLFuture.setVisibility(View.GONE);

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeatherInfo(API_KEY, cityName, DAYS, LANG,AQI);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, retrofit2.Response<WeatherResponse> response) {

                dayArrayList.clear();// xoa du lieu cu truoc khi cap nhat du lieu moi
                astroArrayList.clear();

                customProgressBar.dismiss();
                futureLL.setVisibility(View.VISIBLE);
                topLLFuture.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        txtCountryFuture.setText(weatherResponse.getLocationApi().getCountry());
                        //Thoi tiet cho ngay mai
                        Forecast forecast = weatherResponse.getForecast();
                        Forecastday forecastday = forecast.getForecastday().get(1);
                        txtTempTomorrow.setText(String.format("%s°C", forecastday.getDays().getAvgTemp()));
                        txtConditionTomorrow.setText(forecastday.getDays().getWeatherCondition().getText());
                        txtUVTomorrow.setText(String.format("%s", forecastday.getDays().getUv()));
                        txtWindTomorrow.setText(String.format("%sKm/h", forecastday.getDays().getMaxWind()));
                        txtHumidityTomorrow.setText(String.format("%s%%", forecastday.getDays().getAvgHumidity()));
                        WeatherImageManager weatherImageManager = new WeatherImageManager();
                        int imgDayResource = weatherImageManager.getDayImageResource(forecastday.getDays().getWeatherCondition().getCode());
                        Picasso.get().load(imgDayResource).into(imgTomorrow);

                        //Days
                        String dateTom = forecastday.getDate();
                        String conditionTom = forecastday.getDays().getWeatherCondition().getText();
                        String codeTom = forecastday.getDays().getWeatherCondition().getCode();
                        WeatherCondition weatherConditionTom = new WeatherCondition(conditionTom, codeTom);
                        weatherConditionTom.setText(conditionTom);
                        weatherConditionTom.setCode(codeTom);
                        Double maxTempTom = forecastday.getDays().getTempMax();
                        Double minTempTom = forecastday.getDays().getTempMin();
                        Double avgTempTom = forecastday.getDays().getAvgTemp();
                        Double uvTom = forecastday.getDays().getUv();
                        Double maxWindTom = forecastday.getDays().getMaxWind();
                        Double avgHumidityTom = forecastday.getDays().getAvgHumidity();
                        Double avgVisionTom = forecastday.getDays().getAvgVision();
                        Double totalPreTom = forecastday.getDays().getTotalPre();
                        Double chanceRainTom = forecastday.getDays().getChanceRain();
                        Double totalSnowTom = forecastday.getDays().getTotalSnow();
                        Double chanceSnowTom = forecastday.getDays().getChanceSnow();
                        //Astronomy
                        String sunRiseTom = forecastday.getAstro().getSunRise();
                        String sunSetTom = forecastday.getAstro().getSunSet();
                        String moonRiseTom = forecastday.getAstro().getMoonRise();
                        String moonSetTom = forecastday.getAstro().getMoonSet();
                        String moonPhaseTom = forecastday.getAstro().getMoonPhase();

                        dayArrayListTom.add(new Day(dateTom, weatherConditionTom, maxTempTom, minTempTom,avgTempTom,uvTom,maxWindTom,avgHumidityTom,avgVisionTom,totalPreTom,chanceRainTom,totalSnowTom,chanceSnowTom,null));
                        astroArrayListTom.add(new Astro(sunRiseTom,sunSetTom,moonRiseTom,moonSetTom,moonPhaseTom));

                        //Thoi tiet cho cac ngay tiep theo
                        ArrayList<Forecastday> forecastdayArrayList = forecast.getForecastday();
                        if (forecastdayArrayList != null && !forecastdayArrayList.isEmpty() && forecastdayArrayList.size() > 1) {
                            for (int i = 2; i < forecastdayArrayList.size(); i++) {
                                Forecastday forecastdays = forecastdayArrayList.get(i);
                                //Days
                                String date = forecastdays.getDate();
                                String condition = forecastdays.getDays().getWeatherCondition().getText();
                                String code = forecastdays.getDays().getWeatherCondition().getCode();
                                WeatherCondition weatherCondition = new WeatherCondition(condition, code);
                                weatherCondition.setText(condition);
                                weatherCondition.setCode(code);
                                Double maxTemp = forecastdays.getDays().getTempMax();
                                Double minTemp = forecastdays.getDays().getTempMin();
                                Double avgTemp = forecastdays.getDays().getAvgTemp();
                                Double uv = forecastdays.getDays().getUv();
                                Double maxWind = forecastdays.getDays().getMaxWind();
                                Double avgHumidity = forecastdays.getDays().getAvgHumidity();
                                Double avgVision = forecastdays.getDays().getAvgVision();
                                Double totalPre = forecastdays.getDays().getTotalPre();
                                Double chanceRain = forecastdays.getDays().getChanceRain();
                                Double totalSnow = forecastdays.getDays().getTotalSnow();
                                Double chanceSnow = forecastdays.getDays().getChanceSnow();
                                //Astronomy
                                String sunRise = forecastdays.getAstro().getSunRise();
                                String sunSet = forecastdays.getAstro().getSunSet();
                                String moonRise = forecastdays.getAstro().getMoonRise();
                                String moonSet = forecastdays.getAstro().getMoonSet();
                                String moonPhase = forecastdays.getAstro().getMoonPhase();

                                dayArrayList.add(new Day(date, weatherCondition, maxTemp, minTemp,avgTemp,uv,maxWind,avgHumidity,avgVision,totalPre,chanceRain,totalSnow,chanceSnow,null));
                                astroArrayList.add(new Astro(sunRise,sunSet,moonRise,moonSet,moonPhase));
                            }
                            dayAdapters.notifyDataSetChanged();
                        }
                    }
                } else {
                    Toast.makeText(FutureActivity.this, "Vui lòng nhập tên địa điểm hợp lệ!", Toast.LENGTH_SHORT).show();
                    txtConditionTomorrow.setText("");
                    txtTempTomorrow.setText("");
                    txtUVTomorrow.setText("");
                    txtWindTomorrow.setText("");
                    txtHumidityTomorrow.setText("");
                    imgTomorrow.setImageResource(R.drawable.nodata);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                customProgressBar.dismiss();
                futureLL.setVisibility(View.VISIBLE);
                topLLFuture.setVisibility(View.VISIBLE);
                Toast.makeText(FutureActivity.this, "Lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
                txtConditionTomorrow.setText("");
                txtTempTomorrow.setText("");
                txtUVTomorrow.setText("");
                txtWindTomorrow.setText("");
                txtHumidityTomorrow.setText("");
                imgTomorrow.setImageResource(R.drawable.nodata);
            }
        });
    }

    public void onClick(View view){
        Intent intent = new Intent(FutureActivity.this,TomorrowActivity.class);
        //Days
        intent.putExtra("img",dayArrayListTom.get(0).getWeatherCondition().getCode());
        intent.putExtra("time",dayArrayListTom.get(0).getDate());
        intent.putExtra("temp",dayArrayListTom.get(0).getAvgTemp());
        intent.putExtra("status",dayArrayListTom.get(0).getWeatherCondition().getText());
        intent.putExtra("tempmax",dayArrayListTom.get(0).getTempMax());
        intent.putExtra("tempmin",dayArrayListTom.get(0).getTempMin());
        intent.putExtra("avgtemp",dayArrayListTom.get(0).getAvgTemp());
        intent.putExtra("uv",dayArrayListTom.get(0).getUv());
        intent.putExtra("maxWind",dayArrayListTom.get(0).getMaxWind());
        intent.putExtra("avgHumidity",dayArrayListTom.get(0).getAvgHumidity());
        intent.putExtra("avgVision",dayArrayListTom.get(0).getAvgVision());
        intent.putExtra("totalPre",dayArrayListTom.get(0).getTotalPre());
        intent.putExtra("chanceRain",dayArrayListTom.get(0).getChanceRain());
        intent.putExtra("totalSnow",dayArrayListTom.get(0).getTotalSnow());
        intent.putExtra("chanceSnow",dayArrayListTom.get(0).getChanceSnow());
        //Astronomy
        intent.putExtra("sunrise",astroArrayListTom.get(0).getSunRise());
        intent.putExtra("sunset",astroArrayListTom.get(0).getSunSet());
        intent.putExtra("moonrise",astroArrayListTom.get(0).getMoonRise());
        intent.putExtra("moonset",astroArrayListTom.get(0).getMoonSet());
        intent.putExtra("moonphase",astroArrayListTom.get(0).getMoonPhase());
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(FutureActivity.this,DaysActivity.class);
        //Days
        intent.putExtra("img",dayArrayList.get(position).getWeatherCondition().getCode());
        intent.putExtra("time",dayArrayList.get(position).getDate());
        intent.putExtra("temp",dayArrayList.get(position).getAvgTemp());
        intent.putExtra("status",dayArrayList.get(position).getWeatherCondition().getText());
        intent.putExtra("tempmax",dayArrayList.get(position).getTempMax());
        intent.putExtra("tempmin",dayArrayList.get(position).getTempMin());
        intent.putExtra("avgtemp",dayArrayList.get(position).getAvgTemp());
        intent.putExtra("uv",dayArrayList.get(position).getUv());
        intent.putExtra("maxWind",dayArrayList.get(position).getMaxWind());
        intent.putExtra("avgHumidity",dayArrayList.get(position).getAvgHumidity());
        intent.putExtra("avgVision",dayArrayList.get(position).getAvgVision());
        intent.putExtra("totalPre",dayArrayList.get(position).getTotalPre());
        intent.putExtra("chanceRain",dayArrayList.get(position).getChanceRain());
        intent.putExtra("totalSnow",dayArrayList.get(position).getTotalSnow());
        intent.putExtra("chanceSnow",dayArrayList.get(position).getChanceSnow());
        //Astronomy
        intent.putExtra("sunrise",astroArrayList.get(position).getSunRise());
        intent.putExtra("sunset",astroArrayList.get(position).getSunSet());
        intent.putExtra("moonrise",astroArrayList.get(position).getMoonRise());
        intent.putExtra("moonset",astroArrayList.get(position).getMoonSet());
        intent.putExtra("moonphase",astroArrayList.get(position).getMoonPhase());

        startActivity(intent);
    }
}