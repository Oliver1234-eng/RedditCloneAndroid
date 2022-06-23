package com.example.redditcloneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.redditcloneandroid.activities.CreateUserActivity;
import com.example.redditcloneandroid.activities.MainActivityAdministrator;
import com.example.redditcloneandroid.activities.MainActivityCommentGuest;
import com.example.redditcloneandroid.activities.MainActivityGuest;
import com.example.redditcloneandroid.activities.MainActivityKt;
import com.example.redditcloneandroid.activities.MainActivityModerator;
import com.example.redditcloneandroid.activities.MainActivityReportComment;
import com.example.redditcloneandroid.activities.MainActivityReportPost;

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
                //Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.window_registration_login_button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);

                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.window_guest_login_button);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityGuest.class);

                startActivity(intent);
            }
        });

        Button button13 = (Button) findViewById(R.id.window_guest2_login_button);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityCommentGuest.class);

                startActivity(intent);
            }
        });

        /*Button button11 = (Button) findViewById(R.id.crud_report_comments);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityReportComment.class);

                startActivity(intent);
            }
        });

        Button button12 = (Button) findViewById(R.id.crud_report_posts);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityReportPost.class);

                startActivity(intent);
            }
        });*/

        /*Button button6 = (Button) findViewById(R.id.crud_users);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityUsers.class);

                startActivity(intent);
            }
        });

        Button button7 = (Button) findViewById(R.id.crud_communities);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityCommunities.class);

                startActivity(intent);
            }
        });

        Button button8 = (Button) findViewById(R.id.crud_posts);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityPost.class);

                startActivity(intent);
            }
        });

        Button button9 = (Button) findViewById(R.id.crud_comments);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityComment.class);

                startActivity(intent);
            }
        });

        Button button10 = (Button) findViewById(R.id.crud_flairs);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivityFlair.class);

                startActivity(intent);
            }
        });*/

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