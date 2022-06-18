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

public class EditCommunityActivity extends AppCompatActivity {

    Community community;
    EditText nameText;
    EditText descriptionText;
    EditText rulesText;
    Button editCommunityButton;

    CommunityCRUDInterface communityCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_community);
        Intent detailIntent = getIntent();
        community = (Community) detailIntent.getSerializableExtra("zajednica");
        //Log.i("zajednica: ", community.toString());
        nameText = findViewById(R.id.nameText);
        descriptionText = findViewById(R.id.descriptionText);
        rulesText = findViewById(R.id.rulesText);
        nameText.setText(community.getName());
        descriptionText.setText(community.getDescription());
        rulesText.setText(community.getRules());
        editCommunityButton = findViewById(R.id.editCommunityButton);
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editCommunityButton.setEnabled(buttonEnabled());
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
                editCommunityButton.setEnabled(buttonEnabled());
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
                editCommunityButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editCommunityButton.setEnabled(buttonEnabled());
        editCommunityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommunityDto dto = new CommunityDto(nameText.getText().toString(), descriptionText.getText().toString(), rulesText.getText().toString());
                edit(dto);
            }
        });
    }

    private void edit(CommunityDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        communityCrudInterface = retrofit.create(CommunityCRUDInterface.class);
        int id = community.getId();
        Call<Community> call = communityCrudInterface.edit(id, dto);
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
                Toast toast = Toast.makeText(getApplicationContext(), community.getName() + " izmenjen!!!", Toast.LENGTH_LONG);
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
        Intent intent = new Intent(getApplicationContext(), MainActivityCommunities.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return nameText.getText().toString().trim().length() > 0 && descriptionText.getText().toString().trim().length() > 0 && rulesText.getText().toString().trim().length() > 0;
    }
}
