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
import com.example.redditcloneandroid.dto.PostDto;
import com.example.redditcloneandroid.interfaces.PostCRUDInterface;
import com.example.redditcloneandroid.model.Post;
import com.example.redditcloneandroid.utils.Constants;
import com.example.redditcloneandroid.utils.InputFilterMinMax;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatePostsActivityModerator extends AppCompatActivity {

    EditText titleText;
    EditText textText;
    EditText idOfCommunityText;
    Button createPostButton;

    PostCRUDInterface postCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_moderator);
        titleText = findViewById(R.id.titleText);
        textText = findViewById(R.id.textText);
        idOfCommunityText = findViewById(R.id.idOfCommunityText);
        idOfCommunityText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "5")});
        createPostButton = findViewById(R.id.createPostButton);
        titleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createPostButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createPostButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        idOfCommunityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createPostButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        createPostButton.setEnabled(buttonEnabled());
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostDto dto = new PostDto(titleText.getText().toString(), textText.getText().toString(),Integer.valueOf(idOfCommunityText.getText().toString()));
                create(dto);
            }
        });

    }

    private void create(PostDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        postCrudInterface = retrofit.create(PostCRUDInterface.class);
        Call<Post> call = postCrudInterface.create(dto);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                Post post = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), post.getTitle() + " dodat!!!", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityPostModerator.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return titleText.getText().toString().trim().length() > 0 && textText.getText().toString().trim().length() > 0 && idOfCommunityText.getText().toString().trim().length() > 0;
    }
}
