package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.redditcloneandroid.R;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button sacuvaj = (Button) findViewById(R.id.promena_lozinke_sacuvaj);
        sacuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ChangePasswordActivity.this, ProfilActivity.class);

                startActivity(intent);
            }
        });

        Button odustani = (Button) findViewById(R.id.promena_lozinke_nazad);
        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ChangePasswordActivity.this, ProfilActivity.class);

                startActivity(intent);
            }
        });

    }
}
