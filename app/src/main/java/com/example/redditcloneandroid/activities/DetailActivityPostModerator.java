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
import com.example.redditcloneandroid.fragments.DeletePostFragment;
import com.example.redditcloneandroid.interfaces.DeletePostInterface;
import com.example.redditcloneandroid.interfaces.PostCRUDInterface;
import com.example.redditcloneandroid.model.Post;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivityPostModerator extends AppCompatActivity implements DeletePostInterface {

    TextView idPostText;
    TextView titleText;
    TextView textText;
    TextView idOfCommunityText;
    Button editPostButton;
    Button deletePostButton;

    PostCRUDInterface postCrudInterface;
    Post post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity_detail_moderator);

        idPostText = findViewById(R.id.idPostText);
        titleText = findViewById(R.id.titleText);
        textText = findViewById(R.id.textText);
        idOfCommunityText = findViewById(R.id.idOfCommunityText);
        int id = getIntent().getExtras().getInt("id");
        editPostButton = findViewById(R.id.editPostButton);
        editPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEdit();
            }
        });
        deletePostButton = findViewById(R.id.deletePostButton);
        deletePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(post.getId());
            }
        });
        getOne(id);

        Button button1 = (Button) findViewById(R.id.back_posts_moderator);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivityPostModerator.this, MainActivityPostModerator.class);

                startActivity(intent);
            }
        });

    }

    private void getOne(int id) {
        postCrudInterface = getPostCrudInterface();
        Call<Post> call = postCrudInterface.getOne(id);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                post = response.body();
                idPostText.setText(String.valueOf(post.getId()));
                titleText.setText(post.getTitle());
                textText.setText(post.getText());
                idOfCommunityText.setText(String.valueOf(post.getCommunity()));
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditPostActivityModerator.class);
        intent.putExtra("objava", post);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeletePostFragment deletePostFragment = new DeletePostFragment("Obrisi objavu ", post.getId(), this);
        deletePostFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(int id) {
        postCrudInterface = getPostCrudInterface();
        Call<Post> call = postCrudInterface.delete(id);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                post = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), post.getTitle() + " obrisan!!!", Toast.LENGTH_LONG);
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

    private PostCRUDInterface getPostCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        postCrudInterface = retrofit.create(PostCRUDInterface.class);
        return postCrudInterface;
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityPostModerator.class);
        startActivity(intent);
    }
}
