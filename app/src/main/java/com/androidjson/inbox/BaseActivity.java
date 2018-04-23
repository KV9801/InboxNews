package com.androidjson.inbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import com.androidjson.inbox.util.Utils;


public class BaseActivity extends AppCompatActivity
{
    private final static int THEME_DARK = 1;
    private final static int THEME_LIGHT = 2;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateTheme();
    }

    public void updateTheme() {
        if (Utils.getTheme(getApplicationContext()) <= THEME_DARK) {
            setTheme(R.style.AppThemeDark);
            //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
             //   getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        } else if (Utils.getTheme(getApplicationContext()) == THEME_LIGHT) {
            setTheme(R.style.AppThemeLight);
            //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            //    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
    }
}

