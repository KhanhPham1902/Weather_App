package com.khanhpham.weatherapp.Functions;

import android.app.Activity;

import android.content.res.Configuration;

import androidx.cardview.widget.CardView;

import com.khanhpham.weatherapp.R;

public class ChangeUI {
    //Thay doi theo cai dat he thong
    public static void setSystemMode(Activity activity) {
        int currentNightMode = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_YES:
                activity.setTheme(R.style.AppTheme_Night);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
            default:
                activity.setTheme(R.style.AppTheme_Light);
                break;
        }
    }

    //Check night mode
    public static boolean checkNightMode(Activity activity){
        int currentMode = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return Configuration.UI_MODE_NIGHT_YES == currentMode;
    }
}
