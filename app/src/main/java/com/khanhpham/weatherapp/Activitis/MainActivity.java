package com.khanhpham.weatherapp.Activitis;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.khanhpham.weatherapp.Domains.Day;
import com.khanhpham.weatherapp.Domains.WeatherApiData;
import com.khanhpham.weatherapp.Fragments.AboutFragment;
import com.khanhpham.weatherapp.Fragments.ExitFragment;
import com.khanhpham.weatherapp.Fragments.HomeFragment;
import com.khanhpham.weatherapp.Fragments.SettingsFragment;
import com.khanhpham.weatherapp.Fragments.ShareFragment;
import com.khanhpham.weatherapp.Functions.ChangeUI;
import com.khanhpham.weatherapp.Functions.CityName;
import com.khanhpham.weatherapp.Functions.UppercaseFormat;
import com.khanhpham.weatherapp.Functions.WeatherImageManager;
import com.khanhpham.weatherapp.R;
import com.khanhpham.weatherapp.Retrofit.WeatherApi;
import com.khanhpham.weatherapp.Retrofit.WeatherResponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private LinearLayout LinearLayoutBg;
    private TextView txtDate, txtWeather, txtTemp, txtUV, txtWind, txtHumidity, txtCountry, txtTempFeelsLike, txtTotalPreCurrent, txtChanceRainCurrent, txtWindDeCurrent, txtWindDirCurrent, txtTempMaxCurrent, txtTempMinCurrent, txtWindGustCurrent, txtVisionCurrent, txtCloudCurrent;
    public TextView txtLocate,txtCelsius,txtFeelsLike;
    private ImageView imgToday;
    private FrameLayout frameLayout;
    private FusedLocationProviderClient fusedLocationClient;
    private String cityName;
    private String coordinates;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 123;
    private boolean isLocationSet = false;
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String newCityname = data.getStringExtra("CITYNAME");
                    String newCoordinate = data.getStringExtra("COORDINATE");
                    cityName = newCityname;
                    coordinates = newCoordinate;
                    String city = UppercaseFormat.getUppercase(newCityname);
                    txtLocate.setText(city);
                    getWeatherInfo(coordinates);
                    isLocationSet = true;
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Thay doi giao dien sang/toi theo cai dat he thong
        ChangeUI.setSystemMode(this);

        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        txtDate = findViewById(R.id.txtDate);
        txtHumidity = findViewById(R.id.txtHumidity);
        txtCountry = findViewById(R.id.txtCountry);
        txtLocate = findViewById(R.id.txtLocate);
        txtUV = findViewById(R.id.txtUV);
        txtTemp = findViewById(R.id.txtTemp);
        txtWeather = findViewById(R.id.txtWeather);
        txtCelsius = findViewById(R.id.txtCelsius);
        txtFeelsLike = findViewById(R.id.txtFeelsLike);
        txtWind = findViewById(R.id.txtWind);
        txtTempFeelsLike = findViewById(R.id.txtTempFeelsLike);
        txtTotalPreCurrent = findViewById(R.id.txtTotalPreCurrent);
        txtChanceRainCurrent = findViewById(R.id.txtChanceRainCurrent);
        txtWindDeCurrent = findViewById(R.id.txtWindDeCurrent);
        txtWindDirCurrent = findViewById(R.id.txtWindDirCurrent);
        txtWindGustCurrent = findViewById(R.id.txtWindGustCurrent);
        txtTempMaxCurrent = findViewById(R.id.txtTempMaxCurrent);
        txtTempMinCurrent = findViewById(R.id.txtTempMinCurrent);
        txtVisionCurrent = findViewById(R.id.txtVisionCurrent);
        txtCloudCurrent = findViewById(R.id.txtCloudCurrent);
        imgToday = findViewById(R.id.imgToday);
        frameLayout = findViewById(R.id.fragment_container);
        BottomNavigationView bottomView = findViewById(R.id.bottomView);
        LinearLayoutBg = findViewById(R.id.LinearLayoutBg);
        ImageView imgSearch = findViewById(R.id.imgSearch);

        //Location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

        //Toolbar
        setSupportActionBar(toolbar);
        if (ChangeUI.checkNightMode(this)) {
            toolbar.setNavigationIcon(R.drawable.menu_white);
        }

        //Navigation drawer menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_view);
        }

        //Bottom Navigation View
        bottomView.setSelectedItemId(R.id.bottom_current);
        bottomView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_current:
                    return true;
                case R.id.bottom_hourly:
                    Intent intent1 = new Intent(getApplicationContext(), HourlyActivity.class);
                    intent1.putExtra("CITYNAME", cityName);
                    intent1.putExtra("COORDINATE", coordinates);
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                    activityResultLauncher.launch(intent1);
                    return true;
                case R.id.bottom_daily:
                    Intent intent2 = new Intent(getApplicationContext(), FutureActivity.class);
                    intent2.putExtra("CITYNAME", cityName);
                    intent2.putExtra("COORDINATE", coordinates);
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                    activityResultLauncher.launch(intent2);
                    return true;
                case R.id.bottom_air_quality:
                    Intent intent3 = new Intent(getApplicationContext(), AirQualityActivity.class);
                    intent3.putExtra("CITYNAME", cityName);
                    intent3.putExtra("COORDINATE", coordinates);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    activityResultLauncher.launch(intent3);
                    return true;
            }
            return false;
        });

        //ImageView
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), SearchActivity.class);
                intent4.putExtra("CITYNAME", cityName);
                intent4.putExtra("COORDINATE", coordinates);
                activityResultLauncher.launch(intent4);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Đã cho phép", Toast.LENGTH_SHORT).show();
                getLocation();
            } else {
                Toast.makeText(this, "Vui lòng cung cấp các quyền", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    //Thoat menu khi nhan nut quay lai
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Lay thong tin vi tri va thoi tiet dia diem hien tai
    private void getLocation() {
        if (!isLocationSet) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            fusedLocationClient.getLastLocation().addOnCompleteListener(this, new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        Location location = task.getResult();
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        cityName = CityName.getCityName(MainActivity.this, latitude, longitude);
                        txtLocate.setText(cityName);
                        String locate = latitude + "," + longitude;
                        coordinates = locate;
                        getWeatherInfo(locate);
                    } else {
                        Toast.makeText(MainActivity.this, "Không lấy được thông tin vị trí", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //Lay thong tin thoi tiet
    private void getWeatherInfo(String cityName) {
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
        frameLayout.setVisibility(View.GONE);

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeatherInfo(API_KEY, cityName, DAYS, LANG, AQI);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, retrofit2.Response<WeatherResponse> response) {
                customProgressBar.dismiss();
                frameLayout.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        SimpleDateFormat output = new SimpleDateFormat("HH:mm | dd-MM-yyyy");
                        try {
                            Date t = input.parse(weatherResponse.getLocationApi().getLocaltime());
                            assert t != null;
                            txtDate.setText(output.format(t));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        txtCountry.setText(weatherResponse.getLocationApi().getCountry());
                        txtTemp.setText(String.format("%s°C", weatherResponse.getCurrent().getTempC()));
                        txtWeather.setText(weatherResponse.getCurrent().getCondition().getText());
                        txtTempFeelsLike.setText(String.format("%s", weatherResponse.getCurrent().getFeelslikeC()));
                        txtWind.setText(String.format("%s", weatherResponse.getCurrent().getWindKph()));
                        txtHumidity.setText(String.format("%s", weatherResponse.getCurrent().getHumidity()));
                        txtUV.setText(String.format("%s", weatherResponse.getCurrent().getUv()));
                        txtTotalPreCurrent.setText(String.format("%s", weatherResponse.getCurrent().getPrecipitation()));
                        txtWindDeCurrent.setText(String.format("%s°", weatherResponse.getCurrent().getWindDegree()));
                        txtWindDirCurrent.setText(weatherResponse.getCurrent().getWindDirection());
                        txtWindGustCurrent.setText(String.format("%s", weatherResponse.getCurrent().getWindGust()));
                        txtVisionCurrent.setText(String.format("%s", weatherResponse.getCurrent().getVision()));
                        txtCloudCurrent.setText(String.format("%s", weatherResponse.getCurrent().getCloud()));

                        Day days = weatherResponse.getForecast().getForecastday().get(0).getDays();
                        txtChanceRainCurrent.setText(String.format("%s", days.getChanceRain()));
                        txtTempMaxCurrent.setText(String.format("%s", days.getTempMax()));
                        txtTempMinCurrent.setText(String.format("%s", days.getTempMin()));

                        int isDay = weatherResponse.getCurrent().getIsDay();
                        WeatherImageManager weatherImageManager = new WeatherImageManager();
                        if (isDay == 1) {
                            int imgDayResource = weatherImageManager.getDayImageResource(weatherResponse.getCurrent().getCondition().getCode());
                            Picasso.get().load(imgDayResource).into(imgToday);
                            //Thay doi background
                            int imgDayBg = weatherImageManager.getDayBgResource(weatherResponse.getCurrent().getCondition().getCode());
                            Picasso.get()
                                    .load(imgDayBg)
                                    .into(new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                                            LinearLayoutBg.setBackground(drawable);
                                            int pixelColor = bitmap.getPixel(32, 32);
                                            double luminance = calculateLuminance(pixelColor);
                                            if (luminance > 0.5) {
                                                txtDate.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                                txtWeather.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                                txtTemp.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                                txtFeelsLike.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                                txtTempFeelsLike.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                                txtCelsius.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                            } else {
                                                txtDate.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                                txtWeather.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                                txtTemp.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                                txtFeelsLike.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                                txtTempFeelsLike.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                                txtCelsius.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                            }
                                        }

                                        @Override
                                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                                            LinearLayoutBg.setBackgroundResource(R.drawable.sunny_bg);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    });
                        } else {
                            int imgNightResource = weatherImageManager.getNightImageResource(weatherResponse.getCurrent().getCondition().getCode());
                            Picasso.get().load(imgNightResource).into(imgToday);
                            //Thay doi background
                            int imgNightBg = weatherImageManager.getNightBgResource(weatherResponse.getCurrent().getCondition().getCode());
                            Picasso.get()
                                    .load(imgNightBg)
                                    .into(new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                                            LinearLayoutBg.setBackground(drawable);
                                            int pixelColor = bitmap.getPixel(32, 32);
                                            double luminance = calculateLuminance(pixelColor);
                                            if (luminance > 0.5) {
                                                txtDate.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                                txtWeather.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                                txtTemp.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                                txtFeelsLike.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                                txtTempFeelsLike.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                                txtCelsius.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey));
                                            } else {
                                                txtDate.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                                txtWeather.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                                txtTemp.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                                txtFeelsLike.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                                txtTempFeelsLike.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                                txtCelsius.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                                            }
                                        }

                                        @Override
                                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                                            LinearLayoutBg.setBackgroundResource(R.drawable.clear_bg);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    });
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Không tìm thấy địa điểm", Toast.LENGTH_SHORT).show();
                    txtLocate.setText("");
                    txtCountry.setText("");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                customProgressBar.dismiss();
                frameLayout.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
                txtCountry.setText("");
                txtLocate.setText("");
            }
        });
    }

    //Kiem tra mau nen la sang hay toi
    private double calculateLuminance(int color) {
        int red = android.graphics.Color.red(color);
        int green = android.graphics.Color.green(color);
        int blue = android.graphics.Color.blue(color);
        //luminance = 0.299*R + 0.587*G + 0.114*B
        return (0.299 * red + 0.587 * green + 0.114 * blue) / 255;
    }

    //Su kien nhan chon item navigation view
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
            case R.id.nav_share:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                break;
            case R.id.nav_exit:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ExitFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}