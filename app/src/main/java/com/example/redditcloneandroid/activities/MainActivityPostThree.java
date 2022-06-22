package com.example.redditcloneandroid.activities;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.adapters.PostsThreeAdapter;
import com.example.redditcloneandroid.interfaces.PostThreeCRUDInterface;
import com.example.redditcloneandroid.model.PostThree;
import com.example.redditcloneandroid.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPostThree extends AppCompatActivity {

    List<PostThree> posts;
    PostThreeCRUDInterface postCrudInterface;

    ListView listViewPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_posts_three);
        listViewPosts = findViewById(R.id.listViewPosts);
        getAll();

    }

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postCrudInterface = retrofit.create(PostThreeCRUDInterface.class);
        Call<List<PostThree>> call = postCrudInterface.getAll();
        call.enqueue(new Callback<List<PostThree>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<PostThree>> call, Response<List<PostThree>> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                posts = response.body();
                PostsThreeAdapter postsAdapter = new PostsThreeAdapter(posts, getApplicationContext());
                listViewPosts.setAdapter(postsAdapter);
                posts.forEach(p -> Log.i("Objave: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<PostThree>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}
