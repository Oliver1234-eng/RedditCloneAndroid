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
import com.example.redditcloneandroid.adapters.CommentsTwoAdapter;
import com.example.redditcloneandroid.interfaces.CommentTwoCRUDInterface;
import com.example.redditcloneandroid.model.CommentTwo;
import com.example.redditcloneandroid.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityCommentTwo extends AppCompatActivity {

    List<CommentTwo> comments;
    CommentTwoCRUDInterface commentCrudInterface;

    ListView listViewComments;
    FloatingActionButton createCommentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_comments_two);
        listViewComments = findViewById(R.id.listViewComments);
        createCommentButton = findViewById(R.id.createCommentButton);
        createCommentButton.setOnClickListener(new View.OnClickListener() {
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
        commentCrudInterface = retrofit.create(CommentTwoCRUDInterface.class);
        Call<List<CommentTwo>> call = commentCrudInterface.getAll();
        call.enqueue(new Callback<List<CommentTwo>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<CommentTwo>> call, Response<List<CommentTwo>> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                comments = response.body();
                CommentsTwoAdapter commentsAdapter = new CommentsTwoAdapter(comments, getApplicationContext());
                listViewComments.setAdapter(commentsAdapter);
                comments.forEach(p -> Log.i("Komentari: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<CommentTwo>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callCreate() {
        Intent intent = new Intent(getApplicationContext(), CreateCommentTwoActivity.class);
        startActivity(intent);
    }
}
