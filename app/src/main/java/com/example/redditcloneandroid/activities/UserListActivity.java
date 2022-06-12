package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.adapters.KorisnikAdapter;
import com.example.redditcloneandroid.model.Korisnik;
import com.example.redditcloneandroid.retrofit.KorisnikApi;
import com.example.redditcloneandroid.retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.userList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button dodavanjePosta = (Button) findViewById(R.id.dugme_za_dodavanje_korisnika);
        dodavanjePosta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserListActivity.this, PostForm.class);

                startActivity(intent);
            }
        });
    }

    private void loadUsers() {
        RetrofitService retrofitService = new RetrofitService();
        KorisnikApi korisnikApi = retrofitService.getRetrofit().create(KorisnikApi.class);
        korisnikApi.getAllUsers()
                .enqueue(new Callback<List<Korisnik>>() {
                    @Override
                    public void onResponse(Call<List<Korisnik>> call, Response<List<Korisnik>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Korisnik>> call, Throwable t) {
                        Toast.makeText(UserListActivity.this, "Greska prilikom ucitavanja korisnika", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Korisnik> userList) {
        KorisnikAdapter korisnikAdapter = new KorisnikAdapter(userList);
        recyclerView.setAdapter(korisnikAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUsers();
    }
}
