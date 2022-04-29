package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.redditcloneandroid.R;

public class SuspendCommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suspend_community);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button nazad = (Button) findViewById(R.id.suspendovanje_zajednice_nazad);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SuspendCommunityActivity.this, MainPageActivity.class);

                startActivity(intent);
            }
        });

    }
}
