package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.adapters.ObjavaAdapter;
import com.example.redditcloneandroid.model.Objava;
import com.example.redditcloneandroid.retrofit.ObjavaApi;
import com.example.redditcloneandroid.retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        recyclerView = findViewById(R.id.postList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button dodavanjePosta = (Button) findViewById(R.id.dugme_za_dodavanje_objave);
        dodavanjePosta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PostListActivity.this, PostForm.class);

                startActivity(intent);
            }
        });

        Button dugmeZaOdjavaljivanje = (Button) findViewById(R.id.dugme_za_odjavljivanje);
        dugmeZaOdjavaljivanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PostListActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }

    private void loadPosts() {
        RetrofitService retrofitService = new RetrofitService();
        ObjavaApi objavaApi = retrofitService.getRetrofit().create(ObjavaApi.class);
        objavaApi.getAllPosts()
                .enqueue(new Callback<List<Objava>>() {
                    @Override
                    public void onResponse(Call<List<Objava>> call, Response<List<Objava>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Objava>> call, Throwable t) {
                        Toast.makeText(PostListActivity.this, "Greska prilikom ucitavanja objava", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Objava> postList) {
        ObjavaAdapter objavaAdapter = new ObjavaAdapter(postList);
        recyclerView.setAdapter(objavaAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPosts();
    }
}
