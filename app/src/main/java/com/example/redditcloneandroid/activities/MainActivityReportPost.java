package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.adapters.ReportPostsAdapter;
import com.example.redditcloneandroid.interfaces.ReportPostCRUDInterface;
import com.example.redditcloneandroid.model.ReportPost;
import com.example.redditcloneandroid.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityReportPost extends AppCompatActivity {

    List<ReportPost> reportPosts;
    ReportPostCRUDInterface reportPostCrudInterface;

    ListView listViewReportPosts;
    FloatingActionButton createReportPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_report_posts);
        listViewReportPosts = findViewById(R.id.listViewReportPosts);
        createReportPostButton = findViewById(R.id.createReportPostButton);
        createReportPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCreate();
            }
        });
        getAll();

    }

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        reportPostCrudInterface = retrofit.create(ReportPostCRUDInterface.class);
        Call<List<ReportPost>> call = reportPostCrudInterface.getAll();
        call.enqueue(new Callback<List<ReportPost>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<ReportPost>> call, Response<List<ReportPost>> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                reportPosts = response.body();
                ReportPostsAdapter reportPostsAdapter = new ReportPostsAdapter(reportPosts, getApplicationContext());
                listViewReportPosts.setAdapter(reportPostsAdapter);
                reportPosts.forEach(p -> Log.i("Prijavljene objave: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<ReportPost>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callCreate() {
        Intent intent = new Intent(getApplicationContext(), CreateReportPostsActivity.class);
        startActivity(intent);
    }
}
