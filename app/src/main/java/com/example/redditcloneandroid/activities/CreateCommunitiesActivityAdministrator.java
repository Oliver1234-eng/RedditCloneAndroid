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
import com.example.redditcloneandroid.dto.CommunityDto;
import com.example.redditcloneandroid.interfaces.CommunityCRUDInterface;
import com.example.redditcloneandroid.model.Community;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateCommunitiesActivityAdministrator extends AppCompatActivity {

    EditText nameText;
    EditText descriptionText;
    EditText rulesText;
    Button createCommunityButton;

    CommunityCRUDInterface communityCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community_administrator);
        nameText = findViewById(R.id.nameText);
        descriptionText = findViewById(R.id.descriptionText);
        rulesText = findViewById(R.id.rulesText);
        createCommunityButton = findViewById(R.id.createCommunityButton);
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createCommunityButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        descriptionText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createCommunityButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        rulesText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createCommunityButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        createCommunityButton.setEnabled(buttonEnabled());
        createCommunityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommunityDto dto = new CommunityDto(nameText.getText().toString(), descriptionText.getText().toString(), rulesText.getText().toString());
                create(dto);
            }
        });

    }

    private void create(CommunityDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        communityCrudInterface = retrofit.create(CommunityCRUDInterface.class);
        Call<Community> call = communityCrudInterface.create(dto);
        call.enqueue(new Callback<Community>() {
            @Override
            public void onResponse(Call<Community> call, Response<Community> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                Community community = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), community.getName() + " dodat!!!", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<Community> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityCommunitiesAdministrator.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return nameText.getText().toString().trim().length() > 0 && descriptionText.getText().toString().trim().length() > 0 && rulesText.getText().toString().trim().length() > 0;
    }
}
