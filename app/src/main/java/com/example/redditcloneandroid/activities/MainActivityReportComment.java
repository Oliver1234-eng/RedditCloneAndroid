package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.MainActivity;
import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.adapters.ReportCommentsAdapter;
import com.example.redditcloneandroid.interfaces.ReportCommentCRUDInterface;
import com.example.redditcloneandroid.model.ReportComment;
import com.example.redditcloneandroid.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityReportComment extends AppCompatActivity {

    List<ReportComment> reportComments;
    ReportCommentCRUDInterface reportCommentCrudInterface;

    ListView listViewReportComments;
    FloatingActionButton createReportCommentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_report_comments);
        listViewReportComments = findViewById(R.id.listViewReportComments);
        createReportCommentButton = findViewById(R.id.createReportCommentButton);
        createReportCommentButton.setOnClickListener(new View.OnClickListener() {
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
        reportCommentCrudInterface = retrofit.create(ReportCommentCRUDInterface.class);
        Call<List<ReportComment>> call = reportCommentCrudInterface.getAll();
        call.enqueue(new Callback<List<ReportComment>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<ReportComment>> call, Response<List<ReportComment>> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                reportComments = response.body();
                ReportCommentsAdapter reportCommentsAdapter = new ReportCommentsAdapter(reportComments, getApplicationContext());
                listViewReportComments.setAdapter(reportCommentsAdapter);
                reportComments.forEach(p -> Log.i("Prijavljeni komentari: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<ReportComment>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callCreate() {
        Intent intent = new Intent(getApplicationContext(), CreateReportCommentsActivity.class);
        startActivity(intent);
    }
}
