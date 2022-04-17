package com.example.redditcloneandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        int SPLASH_TIME_OUT = 3000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
