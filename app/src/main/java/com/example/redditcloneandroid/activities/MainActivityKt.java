package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;

public class MainActivityKt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kt);

        EditText username =(EditText) findViewById(R.id.edit_text_username_kt);
        EditText password =(EditText) findViewById(R.id.edit_text_password_kt);

        Button loginButtonKorisnik = (Button) findViewById(R.id.login_button_kt);

        loginButtonKorisnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("novi123") && password.getText().toString().equals("novi123")){
                    //correct
                    Toast.makeText(MainActivityKt.this,"Uspesna prijava kao korisnik",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivityKt.this, PostListActivity.class);
                    startActivity(intent);


                }else
                    //incorrect
                    Toast.makeText(MainActivityKt.this,"Neuspesna prijava !!!",Toast.LENGTH_SHORT).show();
            }
        });

        /*Button button1 = (Button) findViewById(R.id.login_button_korisnik);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityModerator.this, MainPageActivity.class);

                startActivity(intent);
            }
        });*/

        Button button2 = (Button) findViewById(R.id.back_to_first_page_kt);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityKt.this, MainActivity.class);

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
