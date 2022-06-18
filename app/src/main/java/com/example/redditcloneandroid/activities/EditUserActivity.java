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
import com.example.redditcloneandroid.dto.UserDto;
import com.example.redditcloneandroid.interfaces.UserCRUDInterface;
import com.example.redditcloneandroid.model.User;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditUserActivity extends AppCompatActivity {

    User user;
    EditText usernameText;
    EditText passwordText;
    EditText emailText;
    Button editUserButton;

    UserCRUDInterface userCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Intent detailIntent = getIntent();
        user = (User) detailIntent.getSerializableExtra("korisnik");
        //Log.i("korisnik: ", user.toString());
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        emailText = findViewById(R.id.emailText);
        usernameText.setText(user.getUsername());
        passwordText.setText(user.getPassword());
        emailText.setText(user.getEmail());
        editUserButton = findViewById(R.id.editUserButton);
        usernameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editUserButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editUserButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editUserButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editUserButton.setEnabled(buttonEnabled());
        editUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDto dto = new UserDto(usernameText.getText().toString(), passwordText.getText().toString(), emailText.getText().toString());
                edit(dto);
            }
        });
    }

    private void edit(UserDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userCrudInterface = retrofit.create(UserCRUDInterface.class);
        int id = user.getId();
        Call<User> call = userCrudInterface.edit(id, dto);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                User user = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), user.getUsername() + " izmenjen!!!", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityUsers.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return usernameText.getText().toString().trim().length() > 0 && passwordText.getText().toString().trim().length() > 0 && emailText.getText().toString().trim().length() > 0;
    }
}
