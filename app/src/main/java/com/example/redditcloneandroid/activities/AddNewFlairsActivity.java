package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;

import java.util.Calendar;

public class AddNewFlairsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_flairs_activity);

        Button dodajTag = (Button) findViewById(R.id.create_tag_button);
        dodajTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddNewFlairsActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });



        Button odustani = (Button) findViewById(R.id.create_tag_button_cancel);
        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddNewFlairsActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
