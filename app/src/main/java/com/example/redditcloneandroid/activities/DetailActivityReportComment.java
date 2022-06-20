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
import com.example.redditcloneandroid.fragments.DeleteReportCommentFragment;
import com.example.redditcloneandroid.interfaces.DeleteReportCommentInterface;
import com.example.redditcloneandroid.interfaces.ReportCommentCRUDInterface;
import com.example.redditcloneandroid.model.ReportComment;
import com.example.redditcloneandroid.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivityReportComment extends AppCompatActivity implements DeleteReportCommentInterface {

    TextView idReportCommentText;
    TextView reportReasonCommentText;
    TextView whichCommentText;
    Button editReportCommentButton;
    Button deleteReportCommentButton;

    ReportCommentCRUDInterface reportCommentCrudInterface;
    ReportComment reportComment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_comment_activity_detail);

        idReportCommentText = findViewById(R.id.idReportCommentText);
        reportReasonCommentText = findViewById(R.id.reportReasonCommentText);
        whichCommentText = findViewById(R.id.whichCommentText);
        int id = getIntent().getExtras().getInt("id");
        editReportCommentButton = findViewById(R.id.editReportCommentButton);
        editReportCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEdit();
            }
        });
        deleteReportCommentButton = findViewById(R.id.deleteReportCommentButton);
        deleteReportCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(reportComment.getId());
            }
        });
        getOne(id);

    }

    private void getOne(int id) {
        reportCommentCrudInterface = getReportCommentCrudInterface();
        Call<ReportComment> call = reportCommentCrudInterface.getOne(id);
        call.enqueue(new Callback<ReportComment>() {
            @Override
            public void onResponse(Call<ReportComment> call, Response<ReportComment> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                reportComment = response.body();
                idReportCommentText.setText(String.valueOf(reportComment.getId()));
                reportReasonCommentText.setText(reportComment.getReportReasonComment());
                whichCommentText.setText(String.valueOf(reportComment.getWhichComment()));
            }

            @Override
            public void onFailure(Call<ReportComment> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditReportCommentActivity.class);
        intent.putExtra("prijavljeniKomentar", reportComment);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteReportCommentFragment deleteReportCommentFragment = new DeleteReportCommentFragment("Obrisi komentar ", reportComment.getId(), this);
        deleteReportCommentFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(int id) {
        reportCommentCrudInterface = getReportCommentCrudInterface();
        Call<ReportComment> call = reportCommentCrudInterface.delete(id);
        call.enqueue(new Callback<ReportComment>() {
            @Override
            public void onResponse(Call<ReportComment> call, Response<ReportComment> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                reportComment = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), reportComment.getReportReasonComment() + " obrisan!!!", Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<ReportComment> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private ReportCommentCRUDInterface getReportCommentCrudInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reportCommentCrudInterface = retrofit.create(ReportCommentCRUDInterface.class);
        return reportCommentCrudInterface;
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityReportComment.class);
        startActivity(intent);
    }
}
