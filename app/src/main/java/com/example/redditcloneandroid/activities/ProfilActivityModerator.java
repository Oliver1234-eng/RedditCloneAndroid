package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.redditcloneandroid.R;

public class ProfilActivityModerator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profil_moderator);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button spisakNeprikladnihObjava = (Button) findViewById(R.id.pregledaj_prijavaljene_objave);
        spisakNeprikladnihObjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityModerator.this, MainActivityReportPostModerator.class);

                startActivity(intent);
            }
        });

        Button spisakNeprikladnihKomentara = (Button) findViewById(R.id.pregledaj_prijavljene_komentare);
        spisakNeprikladnihKomentara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityModerator.this, MainActivityReportCommentModerator.class);

                startActivity(intent);
            }
        });

        Button blokiranjeKorisnikaButton = (Button) findViewById(R.id.blokiraj_korisnika);
        blokiranjeKorisnikaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityModerator.this, MainActivityUsersModerator.class);

                startActivity(intent);
            }
        });

        Button dodavanjeNovogTaga = (Button) findViewById(R.id.rad_sa_tagovima);
        dodavanjeNovogTaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityModerator.this, MainActivityFlair.class);

                startActivity(intent);
            }
        });

        Button azuriranjeListePravila = (Button) findViewById(R.id.azuriraj_listu_pravila);
        azuriranjeListePravila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityModerator.this, MainActivityCommunitiesModerator.class);

                startActivity(intent);
            }
        });

        Button nazadNaPocetnuStranu = (Button) findViewById(R.id.nazad_na_home_page_moderator);
        nazadNaPocetnuStranu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityModerator.this, MainActivityPostModerator.class);

                startActivity(intent);
            }
        });

        Button kreiranjeZajednice = (Button) findViewById(R.id.kreiranje_zajednice_button_moderator);
        kreiranjeZajednice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivityModerator.this, MainActivityCommunitiesModerator.class);

                startActivity(intent);
            }
        });

    }
}
