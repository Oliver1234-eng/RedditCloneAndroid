package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;

public class ProfilActivityAdministrator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profil_administrator);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button suspendovanjeZajedniceButton = (Button) findViewById(R.id.suspenduj_zajednice);
        suspendovanjeZajedniceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityAdministrator.this, MainActivityCommunitiesAdministrator.class);

                startActivity(intent);
            }
        });

        Button uklanjanjeModeratoraButton = (Button) findViewById(R.id.ukloni_moderatore);
        uklanjanjeModeratoraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityAdministrator.this, MainActivityUsersAdministrator.class);

                startActivity(intent);
            }
        });

        Button nazadNaPocetnuStranu = (Button) findViewById(R.id.nazad_na_home_page_administrator);
        nazadNaPocetnuStranu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityAdministrator.this, MainActivityPostAdministrator.class);

                startActivity(intent);
            }
        });

        Button kreiranjeZajednice = (Button) findViewById(R.id.kreiranje_zajednice_button_administrator);
        kreiranjeZajednice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityAdministrator.this, MainActivityCommunitiesAdministrator.class);

                startActivity(intent);
            }
        });

    }
}
