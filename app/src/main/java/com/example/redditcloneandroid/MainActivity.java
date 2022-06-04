package com.example.redditcloneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.redditcloneandroid.activities.MainPageActivity;
import com.example.redditcloneandroid.activities.RegistrationActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.window_registration_login_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);

                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.window_guest_login_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainPageActivity.class);

                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.submit_button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainPageActivity.class);

                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}