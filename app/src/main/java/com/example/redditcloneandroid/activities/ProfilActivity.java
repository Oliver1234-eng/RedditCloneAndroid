package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button promeniLozinku = (Button) findViewById(R.id.button_promeni_lozinku);
        promeniLozinku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivity.this, MainActivityUsers.class);

                startActivity(intent);
            }
        });

        Button kreiranjeObjave = (Button) findViewById(R.id.kreiranje_objave_button);
        kreiranjeObjave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivity.this, CreatePostActivity.class);

                startActivity(intent);
            }
        });

        Button kreiranjeZajednice = (Button) findViewById(R.id.kreiranje_zajednice_button);
        kreiranjeZajednice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivity.this, MainActivityCommunities.class);

                startActivity(intent);
            }
        });

        Button nazadNaPocetnuStranu = (Button) findViewById(R.id.nazad_na_home_page_user);
        nazadNaPocetnuStranu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivity.this, MainActivityPost.class);

                startActivity(intent);
            }
        });

    }
}
