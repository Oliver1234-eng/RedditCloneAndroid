package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.dto.FlairDto;
import com.example.redditcloneandroid.interfaces.FlairCRUDInterface;
import com.example.redditcloneandroid.model.Flair;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditFlairActivity extends AppCompatActivity {

    Flair flair;
    EditText flairNameText;
    Button editFlairButton;

    FlairCRUDInterface flairCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flair);
        Intent detailIntent = getIntent();
        flair = (Flair) detailIntent.getSerializableExtra("tag");
        //Log.i("tag: ", flair.toString());
        flairNameText = findViewById(R.id.flairNameText);
        flairNameText.setText(flair.getFlairName());
        editFlairButton = findViewById(R.id.editFlairButton);
        flairNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editFlairButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editFlairButton.setEnabled(buttonEnabled());
        editFlairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlairDto dto = new FlairDto(flairNameText.getText().toString());
                edit(dto);
            }
        });
    }

    private void edit(FlairDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        flairCrudInterface = retrofit.create(FlairCRUDInterface.class);
        int id = flair.getId();
        Call<Flair> call = flairCrudInterface.edit(id, dto);
        call.enqueue(new Callback<Flair>() {
            @Override
            public void onResponse(Call<Flair> call, Response<Flair> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                Flair flair = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), flair.getFlairName() + " izmenjen!!!", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<Flair> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityFlair.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return flairNameText.getText().toString().trim().length() > 0;
    }
}
