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
import com.example.redditcloneandroid.fragments.DeleteCommentTwoFragment;
import com.example.redditcloneandroid.interfaces.CommentTwoCRUDInterface;
import com.example.redditcloneandroid.interfaces.DeleteCommentTwoInterface;
import com.example.redditcloneandroid.model.CommentTwo;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivityCommentTwo extends AppCompatActivity implements DeleteCommentTwoInterface {

    TextView idCommentText;
    TextView replyText;
    TextView idOfPostText;
    Button editCommentButton;
    Button deleteCommentButton;

    CommentTwoCRUDInterface commentCrudInterface;
    CommentTwo comment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_two_activity_detail);

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

        Button nazad = (Button) findViewById(R.id.comments_two);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivityCommentTwo.this, ProfilActivity.class);

                startActivity(intent);
            }
        });
    }

    private void getOne(int id) {
        commentCrudInterface = getCommentCrudInterface();
        Call<CommentTwo> call = commentCrudInterface.getOne(id);
        call.enqueue(new Callback<CommentTwo>() {
            @Override
            public void onResponse(Call<CommentTwo> call, Response<CommentTwo> response) {
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
            public void onFailure(Call<CommentTwo> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditCommentTwoActivity.class);
        intent.putExtra("komentar", comment);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteCommentTwoFragment deleteCommentFragment = new DeleteCommentTwoFragment("Obrisi komentar ", comment.getId(), this);
        deleteCommentFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(int id) {
        commentCrudInterface = getCommentCrudInterface();
        Call<CommentTwo> call = commentCrudInterface.delete(id);
        call.enqueue(new Callback<CommentTwo>() {
            @Override
            public void onResponse(Call<CommentTwo> call, Response<CommentTwo> response) {
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
            public void onFailure(Call<CommentTwo> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private CommentTwoCRUDInterface getCommentCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        commentCrudInterface = retrofit.create(CommentTwoCRUDInterface.class);
        return commentCrudInterface;
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityCommentTwo.class);
        startActivity(intent);
    }
}
