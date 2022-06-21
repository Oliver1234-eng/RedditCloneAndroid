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

public class EditPostActivityModerator extends AppCompatActivity {

    Post post;
    EditText titleText;
    EditText textText;
    EditText idOfCommunityText;
    Button editPostButton;

    PostCRUDInterface postCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post_moderator);
        Intent detailIntent = getIntent();
        post = (Post) detailIntent.getSerializableExtra("objava");
        //Log.i("objava: ", post.toString());
        titleText = findViewById(R.id.titleText);
        textText = findViewById(R.id.textText);
        idOfCommunityText = findViewById(R.id.idOfCommunityText);
        idOfCommunityText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "4")});
        titleText.setText(post.getTitle());
        textText.setText(post.getText());
        idOfCommunityText.setText(String.valueOf(post.getCommunity()));
        editPostButton = findViewById(R.id.editPostButton);
        titleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editPostButton.setEnabled(buttonEnabled());
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
                editPostButton.setEnabled(buttonEnabled());
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
                editPostButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editPostButton.setEnabled(buttonEnabled());
        editPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostDto dto = new PostDto(titleText.getText().toString(), textText.getText().toString(),Integer.valueOf(idOfCommunityText.getText().toString()));
                edit(dto);
            }
        });
    }

    private void edit(PostDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        postCrudInterface = retrofit.create(PostCRUDInterface.class);
        int id = post.getId();
        Call<Post> call = postCrudInterface.edit(id, dto);
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
                Toast toast = Toast.makeText(getApplicationContext(), post.getTitle() + " izmenjen!!!", Toast.LENGTH_LONG);
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
