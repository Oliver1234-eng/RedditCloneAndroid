package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.Korisnik;
import com.example.redditcloneandroid.retrofit.KorisnikApi;
import com.example.redditcloneandroid.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_retrofit);

        initializeComponents();
    }

    private void initializeComponents() {
        EditText inputEditTextUsername = findViewById(R.id.form_textFieldUsername);
        EditText inputEditEmail = findViewById(R.id.form_textFieldEmail);
        EditText inputEditPassword = findViewById(R.id.form_textFieldLozinka);
        Button buttonSaveUser = findViewById(R.id.form_buttonSaveUser);

        RetrofitService retrofitService = new RetrofitService();
        KorisnikApi korisnikApi = retrofitService.getRetrofit().create(KorisnikApi.class);

        buttonSaveUser.setOnClickListener(view -> {
            String username = String.valueOf(inputEditTextUsername.getText());
            String email = String.valueOf(inputEditEmail.getText());
            String password = String.valueOf(inputEditPassword.getText());

            Korisnik korisnik = new Korisnik();
            korisnik.setUsername(username);
            korisnik.setEmail(email);
            korisnik.setPassword(password);

            korisnikApi.save(korisnik)
                    .enqueue(new Callback<Korisnik>() {
                        @Override
                        public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                            Toast.makeText(UserForm.this, "Uspesna registracija", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UserForm.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Korisnik> call, Throwable t) {
                            Toast.makeText(UserForm.this, "Neuspesna registracija!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, "Desila se greska", t);
                        }
                    });
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
