package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;

import java.util.Calendar;

public class UpdateListOfRulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_list_of_rules_activity);

        Spinner dropdown = findViewById(R.id.spinner2);
        String[] items = new String[]{"zajednica1", "zajednica2", "zajednica3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Button kreirajPravila = (Button) findViewById(R.id.create_rules_button);
        kreirajPravila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UpdateListOfRulesActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });

        Button odustani = (Button) findViewById(R.id.create_rules_button_cancel);
        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UpdateListOfRulesActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
