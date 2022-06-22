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
import com.example.redditcloneandroid.dto.CommentTwoDto;
import com.example.redditcloneandroid.interfaces.CommentTwoCRUDInterface;
import com.example.redditcloneandroid.model.CommentTwo;
import com.example.redditcloneandroid.utils.Constants;
import com.example.redditcloneandroid.utils.InputFilterMinMax;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateCommentTwoActivity extends AppCompatActivity {

    EditText replyText;
    EditText idOfPostText;
    Button createCommentButton;

    CommentTwoCRUDInterface commentCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_comment_two);
        replyText = findViewById(R.id.replyText);
        idOfPostText = findViewById(R.id.idOfPostText);
        idOfPostText.setFilters(new InputFilter[]{ new InputFilterMinMax("2", "2")});
        createCommentButton = findViewById(R.id.createCommentButton);
        replyText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createCommentButton.setEnabled(buttonEnabled());
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
                createCommentButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        createCommentButton.setEnabled(buttonEnabled());
        createCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentTwoDto dto = new CommentTwoDto(replyText.getText().toString(), Integer.valueOf(idOfPostText.getText().toString()));
                create(dto);
            }
        });

    }

    private void create(CommentTwoDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        commentCrudInterface = retrofit.create(CommentTwoCRUDInterface.class);
        Call<CommentTwo> call = commentCrudInterface.create(dto);
        call.enqueue(new Callback<CommentTwo>() {
            @Override
            public void onResponse(Call<CommentTwo> call, Response<CommentTwo> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                CommentTwo comment = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), comment.getReply() + " dodat!!!", Toast.LENGTH_LONG);
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

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityCommentTwo.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return replyText.getText().toString().trim().length() > 0 && idOfPostText.getText().toString().trim().length() > 0;
    }
}
