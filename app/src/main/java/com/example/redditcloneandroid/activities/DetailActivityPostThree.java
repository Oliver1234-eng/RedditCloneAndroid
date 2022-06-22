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

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.interfaces.PostThreeCRUDInterface;
import com.example.redditcloneandroid.model.PostThree;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivityPostThree extends AppCompatActivity {

    TextView idPostText;
    TextView titleText;
    TextView textText;
    TextView idOfCommunityText;

    PostThreeCRUDInterface postCrudInterface;
    PostThree post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_three_activity_detail);

        idPostText = findViewById(R.id.idPostText);
        titleText = findViewById(R.id.titleText);
        textText = findViewById(R.id.textText);
        idOfCommunityText = findViewById(R.id.idOfCommunityText);
        int id = getIntent().getExtras().getInt("id");

        getOne(id);

        Button button1 = (Button) findViewById(R.id.back);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivityPostThree.this, ProfilActivity.class);

                startActivity(intent);
            }
        });

    }

    private void getOne(int id) {
        postCrudInterface = getPostCrudInterface();
        Call<PostThree> call = postCrudInterface.getOne(id);
        call.enqueue(new Callback<PostThree>() {
            @Override
            public void onResponse(Call<PostThree> call, Response<PostThree> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                post = response.body();
                idPostText.setText(String.valueOf(post.getId()));
                titleText.setText(post.getTitle());
                textText.setText(post.getText());
                idOfCommunityText.setText(String.valueOf(post.getCommunity()));
            }

            @Override
            public void onFailure(Call<PostThree> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private PostThreeCRUDInterface getPostCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        postCrudInterface = retrofit.create(PostThreeCRUDInterface.class);
        return postCrudInterface;
    }
}
