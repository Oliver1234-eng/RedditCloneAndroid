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
import com.example.redditcloneandroid.dto.CommentThreeDto;
import com.example.redditcloneandroid.interfaces.CommentThreeCRUDInterface;
import com.example.redditcloneandroid.model.CommentThree;
import com.example.redditcloneandroid.utils.Constants;
import com.example.redditcloneandroid.utils.InputFilterMinMax;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditCommentThreeActivity extends AppCompatActivity {

    CommentThree comment;
    EditText replyText;
    EditText idOfPostText;
    Button editCommentButton;

    CommentThreeCRUDInterface commentCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_comment_three);
        Intent detailIntent = getIntent();
        comment = (CommentThree) detailIntent.getSerializableExtra("komentar");
        //Log.i("komentar: ", commentThree.toString());
        replyText = findViewById(R.id.replyText);
        idOfPostText = findViewById(R.id.idOfPostText);
        idOfPostText.setFilters(new InputFilter[]{ new InputFilterMinMax("3", "3")});
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
                CommentThreeDto dto = new CommentThreeDto(replyText.getText().toString(), Integer.valueOf(idOfPostText.getText().toString()));
                edit(dto);
            }
        });
    }

    private void edit(CommentThreeDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        commentCrudInterface = retrofit.create(CommentThreeCRUDInterface.class);
        int id = comment.getId();
        Call<CommentThree> call = commentCrudInterface.edit(id, dto);
        call.enqueue(new Callback<CommentThree>() {
            @Override
            public void onResponse(Call<CommentThree> call, Response<CommentThree> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                CommentThree comment = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), comment.getReply() + " izmenjen!!!", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<CommentThree> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityCommentThree.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return replyText.getText().toString().trim().length() > 0 && idOfPostText.getText().toString().trim().length() > 0;
    }
}
