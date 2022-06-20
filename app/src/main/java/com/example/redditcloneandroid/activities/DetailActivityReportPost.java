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
import com.example.redditcloneandroid.fragments.DeleteReportPostFragment;
import com.example.redditcloneandroid.interfaces.DeleteReportPostInterface;
import com.example.redditcloneandroid.interfaces.ReportPostCRUDInterface;
import com.example.redditcloneandroid.model.ReportPost;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivityReportPost extends AppCompatActivity implements DeleteReportPostInterface {

    TextView idReportPostText;
    TextView reportReasonPostText;
    TextView whichPostText;
    Button editReportPostButton;
    Button deleteReportPostButton;

    ReportPostCRUDInterface reportPostCrudInterface;
    ReportPost reportPost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_post_activity_detail);

        idReportPostText = findViewById(R.id.idReportPostText);
        reportReasonPostText = findViewById(R.id.reportReasonPostText);
        whichPostText = findViewById(R.id.whichPostText);
        int id = getIntent().getExtras().getInt("id");
        editReportPostButton = findViewById(R.id.editReportPostButton);
        editReportPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEdit();
            }
        });
        deleteReportPostButton = findViewById(R.id.deleteReportPostButton);
        deleteReportPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(reportPost.getId());
            }
        });
        getOne(id);

    }

    private void getOne(int id) {
        reportPostCrudInterface = getReportPostCrudInterface();
        Call<ReportPost> call = reportPostCrudInterface.getOne(id);
        call.enqueue(new Callback<ReportPost>() {
            @Override
            public void onResponse(Call<ReportPost> call, Response<ReportPost> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                reportPost = response.body();
                idReportPostText.setText(String.valueOf(reportPost.getId()));
                reportReasonPostText.setText(reportPost.getReportReasonPost());
                whichPostText.setText(String.valueOf(reportPost.getWhichPost()));
            }

            @Override
            public void onFailure(Call<ReportPost> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditReportPostActivity.class);
        intent.putExtra("prijavljenaObjava", reportPost);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteReportPostFragment deleteReportPostFragment = new DeleteReportPostFragment("Obrisi objavu ", reportPost.getId(), this);
        deleteReportPostFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(int id) {
        reportPostCrudInterface = getReportPostCrudInterface();
        Call<ReportPost> call = reportPostCrudInterface.delete(id);
        call.enqueue(new Callback<ReportPost>() {
            @Override
            public void onResponse(Call<ReportPost> call, Response<ReportPost> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                reportPost = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), reportPost.getReportReasonPost() + " obrisan!!!", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<ReportPost> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private ReportPostCRUDInterface getReportPostCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reportPostCrudInterface = retrofit.create(ReportPostCRUDInterface.class);
        return reportPostCrudInterface;
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityReportPost.class);
        startActivity(intent);
    }
}
