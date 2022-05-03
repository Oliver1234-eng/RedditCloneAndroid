package com.example.redditcloneandroid.activities;

import android.os.Bundle;
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
    }
}
