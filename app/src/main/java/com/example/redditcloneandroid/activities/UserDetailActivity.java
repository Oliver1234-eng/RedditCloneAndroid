package com.example.redditcloneandroid.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.redditcloneandroid.R;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvIme = findViewById(R.id.tvIme);
        TextView tvPrezime = findViewById(R.id.tvPrezime);
        TextView tvEmail = findViewById(R.id.tvEmail);

        tvIme.setText(getIntent().getStringExtra("ime"));
        tvPrezime.setText(getIntent().getStringExtra("prezime"));
        tvEmail.setText(getIntent().getStringExtra("email"));
    }
}
