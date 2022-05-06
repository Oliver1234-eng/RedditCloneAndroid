package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.redditcloneandroid.R;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvCommunity = findViewById(R.id.tvCommunity);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvText = findViewById(R.id.tvText);

        tvCommunity.setText(getIntent().getStringExtra("community"));
        tvTitle.setText(getIntent().getStringExtra("title"));
        tvText.setText(getIntent().getStringExtra("text"));

        Button prijaviSadrzaj = (Button) findViewById(R.id.prijavi_neprikladan_sadrzaj_button);
        prijaviSadrzaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PostDetailActivity.this, ReportPostActivity.class);

                startActivity(intent);
            }
        });

        Button pregledajKomentare = (Button) findViewById(R.id.pregledaj_komentare_button);
        pregledajKomentare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PostDetailActivity.this, MainPageActivityComment.class);

                startActivity(intent);
            }
        });

        Button posetiZajednicu = (Button) findViewById(R.id.poseti_zajednicu_button);
        posetiZajednicu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PostDetailActivity.this, MainPageActivity.class);

                startActivity(intent);
            }
        });
    }
}
