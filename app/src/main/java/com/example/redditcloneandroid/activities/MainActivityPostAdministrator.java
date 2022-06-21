package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.adapters.PostsAdapter;
import com.example.redditcloneandroid.adapters.PostsAdministratorAdapter;
import com.example.redditcloneandroid.adapters.PostsModeratorAdapter;
import com.example.redditcloneandroid.interfaces.PostCRUDInterface;
import com.example.redditcloneandroid.model.Post;
import com.example.redditcloneandroid.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPostAdministrator extends AppCompatActivity {

    List<Post> posts;
    PostCRUDInterface postCrudInterface;

    ListView listViewPosts;
    FloatingActionButton createPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_posts_administrator);
        listViewPosts = findViewById(R.id.listViewPosts);
        createPostButton = findViewById(R.id.createPostButton);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCreate();
            }
        });
        getAll();

        Button button2 = (Button) findViewById(R.id.proba);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityPostAdministrator.this, MainActivity.class);

                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.proba2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityPostAdministrator.this, ProfilActivityAdministrator.class);

                startActivity(intent);
            }
        });
    }

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postCrudInterface = retrofit.create(PostCRUDInterface.class);
        Call<List<Post>> call = postCrudInterface.getAll();
        call.enqueue(new Callback<List<Post>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                posts = response.body();
                PostsAdministratorAdapter postsAdministratorAdapter = new PostsAdministratorAdapter(posts, getApplicationContext());
                listViewPosts.setAdapter(postsAdministratorAdapter);
                posts.forEach(p -> Log.i("Objave: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callCreate() {
        Intent intent = new Intent(getApplicationContext(), CreatePostsActivityAdministrator.class);
        startActivity(intent);
    }
}
