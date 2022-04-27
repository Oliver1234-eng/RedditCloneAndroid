package com.example.redditcloneandroid.activities;

import android.os.Bundle;
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
    }
}
