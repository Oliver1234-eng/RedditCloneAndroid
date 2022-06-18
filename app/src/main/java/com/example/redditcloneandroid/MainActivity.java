package com.example.redditcloneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.redditcloneandroid.activities.MainActivityAdministrator;
import com.example.redditcloneandroid.activities.MainActivityKt;
import com.example.redditcloneandroid.activities.MainActivityModerator;
import com.example.redditcloneandroid.activities.MainPageActivity;
import com.example.redditcloneandroid.activities.RegistrationActivity;
import com.example.redditcloneandroid.activities.UserForm;
import com.example.redditcloneandroid.activities.UserListActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button5 = (Button) findViewById(R.id.prijava_kt_dugme);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityKt.class);

                startActivity(intent);
            }
        });

        Button button1 = (Button) findViewById(R.id.prijava_kao_administrator_dugme);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityAdministrator.class);

                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.prijava_kao_korisnik_dugme);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityModerator.class);

                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.window_registration_login_button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, UserForm.class);

                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.window_guest_login_button);
        button4.setOnClickListener(new View.OnClickListener() {
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