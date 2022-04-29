package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.redditcloneandroid.R;

public class ReportListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button nazad = (Button) findViewById(R.id.spisak_neprikladnog_sadrzaja_nazad_button);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReportListActivity.this, MainPageActivity.class);

                startActivity(intent);
            }
        });

    }
}
