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

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.fragments.DeleteCommunityFragment;
import com.example.redditcloneandroid.interfaces.CommunityCRUDInterface;
import com.example.redditcloneandroid.interfaces.DeleteCommunityInterface;
import com.example.redditcloneandroid.model.Community;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivityCommunityModerator extends AppCompatActivity implements DeleteCommunityInterface {

    TextView idCommunityText;
    TextView nameText;
    TextView descriptionText;
    TextView rulesText;
    Button editCommunityButton;
    Button deleteCommunityButton;

    CommunityCRUDInterface communityCrudInterface;
    Community community;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activity_detail_moderator);

        idCommunityText = findViewById(R.id.idCommunityText);
        nameText = findViewById(R.id.nameText);
        descriptionText = findViewById(R.id.descriptionText);
        rulesText = findViewById(R.id.rulesText);
        int id = getIntent().getExtras().getInt("id");
        editCommunityButton = findViewById(R.id.editCommunityButton);
        editCommunityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEdit();
            }
        });
        deleteCommunityButton = findViewById(R.id.deleteCommunityButton);
        deleteCommunityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(community.getId());
            }
        });
        getOne(id);

        Button nazadNaPocetnuStranu = (Button) findViewById(R.id.communities_moderator);
        nazadNaPocetnuStranu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivityCommunityModerator.this, ProfilActivityModerator.class);

                startActivity(intent);
            }
        });
    }

    private void getOne(int id) {
        communityCrudInterface = getCommunityCrudInterface();
        Call<Community> call = communityCrudInterface.getOne(id);
        call.enqueue(new Callback<Community>() {
            @Override
            public void onResponse(Call<Community> call, Response<Community> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                community = response.body();
                idCommunityText.setText(String.valueOf(community.getId()));
                nameText.setText(community.getName());
                descriptionText.setText(community.getDescription());
                rulesText.setText(community.getRules());
            }

            @Override
            public void onFailure(Call<Community> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditCommunityActivityModerator.class);
        intent.putExtra("zajednica", community);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteCommunityFragment deleteCommunityFragment = new DeleteCommunityFragment("Obrisi zajednicu ", community.getId(), this);
        deleteCommunityFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(int id) {
        communityCrudInterface = getCommunityCrudInterface();
        Call<Community> call = communityCrudInterface.delete(id);
        call.enqueue(new Callback<Community>() {
            @Override
            public void onResponse(Call<Community> call, Response<Community> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                community = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), community.getName() + " obrisan!!!", Toast.LENGTH_LONG);
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

    private CommunityCRUDInterface getCommunityCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        communityCrudInterface = retrofit.create(CommunityCRUDInterface.class);
        return communityCrudInterface;
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityCommunitiesModerator.class);
        startActivity(intent);
    }
}
