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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button suspendovanjeZajedniceButton = (Button) findViewById(R.id.suspendovanje_zajednice_button);
        suspendovanjeZajedniceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivity.this, SuspendCommunityActivity.class);

                startActivity(intent);
            }
        });

        Button uklanjanjeModeratoraButton = (Button) findViewById(R.id.uklanjanje_moderatora_button);
        uklanjanjeModeratoraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivity.this, DeleteModeratorActivity.class);

                startActivity(intent);
            }
        });

        Button spisakNeprikladnogSadrzajaButton = (Button) findViewById(R.id.spisak_neprikladnog_sadrzaja_button);
        spisakNeprikladnogSadrzajaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivity.this, ReportListActivity.class);

                startActivity(intent);
            }
        });

        Button blokiranjeKorisnikaButton = (Button) findViewById(R.id.blokiranje_korisnika_button);
        blokiranjeKorisnikaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivity.this, MainPageActivityUser.class);

                startActivity(intent);
            }
        });

        Button promeniLozinku = (Button) findViewById(R.id.button_promeni_lozinku);
        promeniLozinku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfilActivity.this, ChangePasswordActivity.class);

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

                Intent intent = new Intent(ProfilActivity.this, CreateCommunityActivity.class);

                startActivity(intent);
            }
        });

    }
}
