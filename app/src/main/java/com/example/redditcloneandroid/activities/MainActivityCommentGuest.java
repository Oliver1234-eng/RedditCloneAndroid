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
import com.example.redditcloneandroid.adapters.CommentsGuestAdapter;
import com.example.redditcloneandroid.interfaces.CommentGuestCRUDInterface;
import com.example.redditcloneandroid.model.CommentGuest;
import com.example.redditcloneandroid.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityCommentGuest extends AppCompatActivity {

    List<CommentGuest> comments;
    CommentGuestCRUDInterface commentCrudInterface;

    ListView listViewComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_comments_guest);
        listViewComments = findViewById(R.id.listViewComments);
        getAll();

    }

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commentCrudInterface = retrofit.create(CommentGuestCRUDInterface.class);
        Call<List<CommentGuest>> call = commentCrudInterface.getAll();
        call.enqueue(new Callback<List<CommentGuest>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<CommentGuest>> call, Response<List<CommentGuest>> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                comments = response.body();
                CommentsGuestAdapter commentsAdapter = new CommentsGuestAdapter(comments, getApplicationContext());
                listViewComments.setAdapter(commentsAdapter);
                comments.forEach(p -> Log.i("Komentari: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<CommentGuest>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

}
