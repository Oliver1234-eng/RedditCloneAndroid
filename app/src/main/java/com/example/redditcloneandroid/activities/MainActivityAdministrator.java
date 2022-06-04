package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;
import com.google.android.material.button.MaterialButton;

public class MainActivityAdministrator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_administrator);

        EditText username =(EditText) findViewById(R.id.edit_text_username_administrator);
        EditText password =(EditText) findViewById(R.id.edit_text_password_administrator);

        Button loginButtonAdministrator = (Button) findViewById(R.id.login_button_administrator);

        loginButtonAdministrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(MainActivityAdministrator.this,"Uspesna prijava kao administrator",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivityAdministrator.this, MainPageActivity.class);
                    startActivity(intent);


                }else
                    //incorrect
                    Toast.makeText(MainActivityAdministrator.this,"Neuspesna prijava !!!",Toast.LENGTH_SHORT).show();
            }
        });

        /*Button button1 = (Button) findViewById(R.id.login_button_administrator);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityAdministrator.this, MainPageActivity.class);

                startActivity(intent);
            }
        });*/

        Button button2 = (Button) findViewById(R.id.back_to_first_page_administrator);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityAdministrator.this, MainActivity.class);

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
