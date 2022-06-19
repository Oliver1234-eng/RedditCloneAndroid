package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.dto.CommentDto;
import com.example.redditcloneandroid.interfaces.CommentCRUDInterface;
import com.example.redditcloneandroid.model.Comment;
import com.example.redditcloneandroid.utils.Constants;
import com.example.redditcloneandroid.utils.InputFilterMinMax;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditCommentActivity extends AppCompatActivity {

    Comment comment;
    EditText replyText;
    EditText idOfPostText;
    Button editCommentButton;

    CommentCRUDInterface commentCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_comment);
        Intent detailIntent = getIntent();
        comment = (Comment) detailIntent.getSerializableExtra("komentar");
        //Log.i("komentar: ", comment.toString());
        replyText = findViewById(R.id.replyText);
        idOfPostText = findViewById(R.id.idOfPostText);
        idOfPostText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "4")});
        replyText.setText(comment.getReply());
        idOfPostText.setText(String.valueOf(comment.getPost()));
        editCommentButton = findViewById(R.id.editCommentButton);
        replyText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editCommentButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        idOfPostText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editCommentButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editCommentButton.setEnabled(buttonEnabled());
        editCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentDto dto = new CommentDto(replyText.getText().toString(), Integer.valueOf(idOfPostText.getText().toString()));
                edit(dto);
            }
        });
    }

    private void edit(CommentDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        commentCrudInterface = retrofit.create(CommentCRUDInterface.class);
        int id = comment.getId();
        Call<Comment> call = commentCrudInterface.edit(id, dto);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                Comment comment = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), comment.getReply() + " izmenjen!!!", Toast.LENGTH_LONG);
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

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityComment.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return replyText.getText().toString().trim().length() > 0 && idOfPostText.getText().toString().trim().length() > 0;
    }
}
