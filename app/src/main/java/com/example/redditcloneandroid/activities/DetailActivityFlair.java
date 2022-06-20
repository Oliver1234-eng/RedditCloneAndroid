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
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.fragment.app.FragmentManager;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.fragments.DeleteFlairFragment;
import com.example.redditcloneandroid.interfaces.DeleteFlairInterface;
import com.example.redditcloneandroid.interfaces.FlairCRUDInterface;
import com.example.redditcloneandroid.model.Flair;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivityFlair extends AppCompatActivity implements DeleteFlairInterface {

    TextView idFlairText;
    TextView flairNameText;
    Button editFlairButton;
    Button deleteFlairButton;

    FlairCRUDInterface flairCrudInterface;
    Flair flair;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flair_activity_detail);

        idFlairText = findViewById(R.id.idFlairText);
        flairNameText = findViewById(R.id.flairNameText);
        int id = getIntent().getExtras().getInt("id");
        editFlairButton = findViewById(R.id.editFlairButton);
        editFlairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEdit();
            }
        });
        deleteFlairButton = findViewById(R.id.deleteFlairButton);
        deleteFlairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(flair.getId());
            }
        });
        getOne(id);
    }

    private void getOne(int id) {
        flairCrudInterface = getFlairCrudInterface();
        Call<Flair> call = flairCrudInterface.getOne(id);
        call.enqueue(new Callback<Flair>() {
            @Override
            public void onResponse(Call<Flair> call, Response<Flair> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                flair = response.body();
                idFlairText.setText(String.valueOf(flair.getId()));
                flairNameText.setText(flair.getFlairName());
            }

            @Override
            public void onFailure(Call<Flair> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditFlairActivity.class);
        intent.putExtra("tag", flair);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteFlairFragment deleteFlairFragment = new DeleteFlairFragment("Obrisi tag ", flair.getId(), this);
        deleteFlairFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(int id) {
        flairCrudInterface = getFlairCrudInterface();
        Call<Flair> call = flairCrudInterface.delete(id);
        call.enqueue(new Callback<Flair>() {
            @Override
            public void onResponse(Call<Flair> call, Response<Flair> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                flair = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), flair.getFlairName() + " obrisan!!!", Toast.LENGTH_LONG);
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

    private FlairCRUDInterface getFlairCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        flairCrudInterface = retrofit.create(FlairCRUDInterface.class);
        return flairCrudInterface;
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityFlair.class);
        startActivity(intent);
    }
}
