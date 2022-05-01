package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.redditcloneandroid.R;

public class ReportPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_report_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button prijaviSadrzaj = (Button) findViewById(R.id.prijavi_neprikladan_sadrzaj_button_post_report);
        prijaviSadrzaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReportPostActivity.this, MainPageActivity.class);

                startActivity(intent);
            }
        });

        Button odustani = (Button) findViewById(R.id.odustani_button_prijava_neprikladnog_sadrzaja);
        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReportPostActivity.this, MainPageActivity.class);

                startActivity(intent);
            }
        });

    }
}
