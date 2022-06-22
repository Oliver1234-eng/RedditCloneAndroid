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

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.interfaces.CommentGuestCRUDInterface;
import com.example.redditcloneandroid.model.CommentGuest;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivityCommentGuest extends AppCompatActivity {

    TextView idCommentText;
    TextView replyText;
    TextView idOfPostText;
    Button editCommentButton;
    Button deleteCommentButton;

    CommentGuestCRUDInterface commentCrudInterface;
    CommentGuest comment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_activity_detail_guest);

        idCommentText = findViewById(R.id.idCommentText);
        replyText = findViewById(R.id.replyText);
        idOfPostText = findViewById(R.id.idOfPostText);
        int id = getIntent().getExtras().getInt("id");
        getOne(id);

        Button nazad = (Button) findViewById(R.id.comments_guest);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivityCommentGuest.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }

    private void getOne(int id) {
        commentCrudInterface = getCommentCrudInterface();
        Call<CommentGuest> call = commentCrudInterface.getOne(id);
        call.enqueue(new Callback<CommentGuest>() {
            @Override
            public void onResponse(Call<CommentGuest> call, Response<CommentGuest> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                comment = response.body();
                idCommentText.setText(String.valueOf(comment.getId()));
                replyText.setText(comment.getReply());
                idOfPostText.setText(String.valueOf(comment.getPost()));
            }

            @Override
            public void onFailure(Call<CommentGuest> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private CommentGuestCRUDInterface getCommentCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        commentCrudInterface = retrofit.create(CommentGuestCRUDInterface.class);
        return commentCrudInterface;
    }

}
