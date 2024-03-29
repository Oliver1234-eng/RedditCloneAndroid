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
import com.example.redditcloneandroid.fragments.DeleteCommentFragment;
import com.example.redditcloneandroid.interfaces.CommentCRUDInterface;
import com.example.redditcloneandroid.interfaces.DeleteCommentInterface;
import com.example.redditcloneandroid.model.Comment;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivityComment extends AppCompatActivity implements DeleteCommentInterface {

    TextView idCommentText;
    TextView replyText;
    TextView idOfPostText;
    Button editCommentButton;
    Button deleteCommentButton;

    CommentCRUDInterface commentCrudInterface;
    Comment comment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_activity_detail);

        idCommentText = findViewById(R.id.idCommentText);
        replyText = findViewById(R.id.replyText);
        idOfPostText = findViewById(R.id.idOfPostText);
        int id = getIntent().getExtras().getInt("id");
        editCommentButton = findViewById(R.id.editCommentButton);
        editCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEdit();
            }
        });
        deleteCommentButton = findViewById(R.id.deleteCommentButton);
        deleteCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(comment.getId());
            }
        });
        getOne(id);

        Button nazad = (Button) findViewById(R.id.comments_one);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivityComment.this, ProfilActivity.class);

                startActivity(intent);
            }
        });
    }

    private void getOne(int id) {
        commentCrudInterface = getCommentCrudInterface();
        Call<Comment> call = commentCrudInterface.getOne(id);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
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
            public void onFailure(Call<Comment> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditCommentActivity.class);
        intent.putExtra("komentar", comment);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteCommentFragment deleteCommentFragment = new DeleteCommentFragment("Obrisi komentar ", comment.getId(), this);
        deleteCommentFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(int id) {
        commentCrudInterface = getCommentCrudInterface();
        Call<Comment> call = commentCrudInterface.delete(id);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                comment = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), comment.getReply() + " obrisan!!!", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private CommentCRUDInterface getCommentCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        commentCrudInterface = retrofit.create(CommentCRUDInterface.class);
        return commentCrudInterface;
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityComment.class);
        startActivity(intent);
    }
}
