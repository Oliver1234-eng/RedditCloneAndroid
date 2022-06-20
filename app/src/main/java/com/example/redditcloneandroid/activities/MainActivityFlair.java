package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.adapters.FlairsAdapter;
import com.example.redditcloneandroid.interfaces.FlairCRUDInterface;
import com.example.redditcloneandroid.model.Flair;
import com.example.redditcloneandroid.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityFlair extends AppCompatActivity {

    List<Flair> flairs;
    FlairCRUDInterface flairCrudInterface;

    ListView listViewFlairs;
    FloatingActionButton createFlairButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_flairs);
        listViewFlairs = findViewById(R.id.listViewFlairs);
        createFlairButton = findViewById(R.id.createFlairButton);
        createFlairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCreate();
            }
        });
        getAll();

    }

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        flairCrudInterface = retrofit.create(FlairCRUDInterface.class);
        Call<List<Flair>> call = flairCrudInterface.getAll();
        call.enqueue(new Callback<List<Flair>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Flair>> call, Response<List<Flair>> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                flairs = response.body();
                FlairsAdapter flairsAdapter = new FlairsAdapter(flairs, getApplicationContext());
                listViewFlairs.setAdapter(flairsAdapter);
                flairs.forEach(p -> Log.i("Tagovi: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Flair>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callCreate() {
        Intent intent = new Intent(getApplicationContext(), CreateFlairActivity.class);
        startActivity(intent);
    }
}
