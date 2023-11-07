package com.khanhpham.weatherapp.Activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khanhpham.weatherapp.Functions.ChangeUI;
import com.khanhpham.weatherapp.Functions.NetworkCheck;
import com.khanhpham.weatherapp.R;

public class InternetCheckActivity extends AppCompatActivity{

    private TextView txtInternetCheck, txtLoading;
    private Button btnTryAgain;
    private ImageView imgBegin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChangeUI.setSystemMode(this);
        setContentView(R.layout.activity_internet_check);

        txtInternetCheck = findViewById(R.id.txtInternetCheck);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        txtLoading = findViewById(R.id.txtLoading);
        imgBegin = findViewById(R.id.imgBegin);

        CustomProgressBar customProgressBar = new CustomProgressBar(InternetCheckActivity.this);

        Handler handler = new Handler();
        int delay = 2000;

        if(NetworkCheck.isNetworkAvailable(this)){
            imgBegin.setVisibility(ImageView.VISIBLE);
            txtLoading.setVisibility(TextView.VISIBLE);
            txtInternetCheck.setVisibility(TextView.GONE);
            btnTryAgain.setVisibility(Button.GONE);
            //Delay 2s truoc khi chuyen sang cua so moi
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(InternetCheckActivity.this,MainActivity.class));
                    finish();
                }
            },delay);
        }else{
            imgBegin.setVisibility(ImageView.GONE);
            txtLoading.setVisibility(TextView.GONE);
            txtInternetCheck.setVisibility(TextView.VISIBLE);
            btnTryAgain.setVisibility(Button.VISIBLE);
            txtInternetCheck.setText("Có vẻ như bạn đang ngoại tuyến\nKiểm tra kết nối và thử lại");
            btnTryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(NetworkCheck.isNetworkAvailable(InternetCheckActivity.this)){
                        startActivity(new Intent(InternetCheckActivity.this,MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(InternetCheckActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}