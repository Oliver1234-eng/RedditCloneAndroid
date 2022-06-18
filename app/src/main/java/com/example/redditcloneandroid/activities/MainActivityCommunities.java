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
import com.example.redditcloneandroid.adapters.CommunitiesAdapter;
import com.example.redditcloneandroid.interfaces.CommunityCRUDInterface;
import com.example.redditcloneandroid.model.Community;
import com.example.redditcloneandroid.model.User;
import com.example.redditcloneandroid.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityCommunities extends AppCompatActivity {

    List<Community> communities;
    CommunityCRUDInterface communityCrudInterface;

    ListView listViewCommunities;
    FloatingActionButton createCommunityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_communities);
        listViewCommunities = findViewById(R.id.listViewCommunities);
        createCommunityButton = findViewById(R.id.createCommunityButton);
        createCommunityButton.setOnClickListener(new View.OnClickListener() {
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
        communityCrudInterface = retrofit.create(CommunityCRUDInterface.class);
        Call<List<Community>> call = communityCrudInterface.getAll();
        call.enqueue(new Callback<List<Community>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Community>> call, Response<List<Community>> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                communities = response.body();
                CommunitiesAdapter communitiesAdapter = new CommunitiesAdapter(communities, getApplicationContext());
                listViewCommunities.setAdapter(communitiesAdapter);
                communities.forEach(p -> Log.i("Zajednice: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Community>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callCreate() {
        Intent intent = new Intent(getApplicationContext(), CreateCommunitiesActivity.class);
        startActivity(intent);
    }
}
