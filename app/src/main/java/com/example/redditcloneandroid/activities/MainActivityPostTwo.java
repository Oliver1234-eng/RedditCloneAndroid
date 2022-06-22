package com.example.redditcloneandroid.activities;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.adapters.PostsTwoAdapter;
import com.example.redditcloneandroid.interfaces.PostTwoCRUDInterface;
import com.example.redditcloneandroid.model.PostTwo;
import com.example.redditcloneandroid.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPostTwo extends AppCompatActivity {

    List<PostTwo> posts;
    PostTwoCRUDInterface postCrudInterface;

    ListView listViewPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_posts_two);
        listViewPosts = findViewById(R.id.listViewPosts);
        getAll();

    }

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postCrudInterface = retrofit.create(PostTwoCRUDInterface.class);
        Call<List<PostTwo>> call = postCrudInterface.getAll();
        call.enqueue(new Callback<List<PostTwo>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<PostTwo>> call, Response<List<PostTwo>> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                posts = response.body();
                PostsTwoAdapter postsAdapter = new PostsTwoAdapter(posts, getApplicationContext());
                listViewPosts.setAdapter(postsAdapter);
                posts.forEach(p -> Log.i("Objave: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<PostTwo>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}
