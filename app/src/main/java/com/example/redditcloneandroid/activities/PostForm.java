package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.Objava;
import com.example.redditcloneandroid.retrofit.ObjavaApi;
import com.example.redditcloneandroid.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_post_retrofit);

        initializeComponents();
    }

    private void initializeComponents() {
        EditText inputEditTextTitle = findViewById(R.id.form_textFieldTitle);
        EditText inputEditTextCommunity = findViewById(R.id.form_textFieldCommunity);
        EditText inputEditTextText = findViewById(R.id.form_textFieldText);
        Button buttonSavePost = findViewById(R.id.form_buttonSavePost);

        RetrofitService retrofitService = new RetrofitService();
        ObjavaApi objavaApi = retrofitService.getRetrofit().create(ObjavaApi.class);

        buttonSavePost.setOnClickListener(view -> {
            String title = String.valueOf(inputEditTextTitle.getText());
            String community = String.valueOf(inputEditTextCommunity.getText());
            String text = String.valueOf(inputEditTextText.getText());

            Objava objava = new Objava();
            objava.setTitle(title);
            objava.setCommunity(community);
            objava.setText(text);

            objavaApi.save(objava)
                    .enqueue(new Callback<Objava>() {
                        @Override
                        public void onResponse(Call<Objava> call, Response<Objava> response) {
                            Toast.makeText(PostForm.this, "Uspesno dodata objava", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PostForm.this, PostListActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Objava> call, Throwable t) {
                            Toast.makeText(PostForm.this, "Neuspesno dodavanje objave!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(PostForm.class.getName()).log(Level.SEVERE, "Desila se greska", t);
                        }
                    });
        });
    }
}
