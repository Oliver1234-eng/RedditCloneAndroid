package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.fragments.DeleteUserFragment;
import com.example.redditcloneandroid.interfaces.DeleteUserInterface;
import com.example.redditcloneandroid.interfaces.UserCRUDInterface;
import com.example.redditcloneandroid.model.User;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivityUser extends AppCompatActivity implements DeleteUserInterface {

    TextView idText;
    TextView usernameText;
    TextView passwordText;
    TextView emailText;
    Button editUserButton;
    Button deleteUserButton;

    UserCRUDInterface userCrudInterface;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_detail);

        idText = findViewById(R.id.idText);
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        emailText = findViewById(R.id.emailText);
        int id = getIntent().getExtras().getInt("id");
        editUserButton = findViewById(R.id.editUserButton);
        editUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEdit();
            }
        });
        deleteUserButton = findViewById(R.id.deleteUserButton);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(user.getId());
            }
        });
        getOne(id);
    }

    private void getOne(int id) {
        userCrudInterface = getUserCrudInterface();
        Call<User> call = userCrudInterface.getOne(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                user = response.body();
                idText.setText(String.valueOf(user.getId()));
                usernameText.setText(user.getUsername());
                passwordText.setText(user.getPassword());
                emailText.setText(user.getEmail());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditUserActivity.class);
        intent.putExtra("korisnik", user);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteUserFragment deleteUserFragment = new DeleteUserFragment("Obrisi korisnika ", user.getId(), this);
        deleteUserFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(int id) {
        userCrudInterface = getUserCrudInterface();
        Call<User> call = userCrudInterface.delete(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                user = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), user.getUsername() + " obrisan!!!", Toast.LENGTH_LONG);
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

    private UserCRUDInterface getUserCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userCrudInterface = retrofit.create(UserCRUDInterface.class);
        return userCrudInterface;
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityUsers.class);
        startActivity(intent);
    }
}
