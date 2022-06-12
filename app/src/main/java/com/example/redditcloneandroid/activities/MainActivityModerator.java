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

public class MainActivityModerator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_moderator);

        EditText username =(EditText) findViewById(R.id.edit_text_username_korisnik);
        EditText password =(EditText) findViewById(R.id.edit_text_password_korisnik);

        Button loginButtonKorisnik = (Button) findViewById(R.id.login_button_korisnik);

        loginButtonKorisnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("korisnik") && password.getText().toString().equals("korisnik")){
                    //correct
                    Toast.makeText(MainActivityModerator.this,"Uspesna prijava kao korisnik",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivityModerator.this, PostListActivity.class);
                    startActivity(intent);


                }else
                    //incorrect
                    Toast.makeText(MainActivityModerator.this,"Neuspesna prijava !!!",Toast.LENGTH_SHORT).show();
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

        Button button2 = (Button) findViewById(R.id.back_to_first_page_korisnik);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityModerator.this, MainActivity.class);

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
