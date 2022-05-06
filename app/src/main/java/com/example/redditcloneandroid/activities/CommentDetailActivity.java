package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.redditcloneandroid.R;

public class CommentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_comment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvTekst = findViewById(R.id.tvTekst);
        TextView tvDatum = findViewById(R.id.tvDatum);

        tvTekst.setText(getIntent().getStringExtra("tekst"));
        tvDatum.setText(getIntent().getStringExtra("datum"));

        Button prijaviKomentar = (Button) findViewById(R.id.prijavi_neprikladan_komentar_button);
        prijaviKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CommentDetailActivity.this, ReportPostActivity.class);

                startActivity(intent);
            }
        });
    }
}
