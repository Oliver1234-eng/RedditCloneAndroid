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
import com.example.redditcloneandroid.dto.ReportCommentDto;
import com.example.redditcloneandroid.interfaces.ReportCommentCRUDInterface;
import com.example.redditcloneandroid.model.ReportComment;
import com.example.redditcloneandroid.utils.Constants;
import com.example.redditcloneandroid.utils.InputFilterMinMax;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateReportCommentsActivity extends AppCompatActivity {

    EditText reportReasonCommentText;
    EditText whichCommentText;
    Button createReportCommentButton;

    ReportCommentCRUDInterface reportCommentCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report_comment);
        reportReasonCommentText = findViewById(R.id.reportReasonCommentText);
        whichCommentText = findViewById(R.id.whichCommentText);
        whichCommentText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "9")});
        createReportCommentButton = findViewById(R.id.createReportCommentButton);
        reportReasonCommentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createReportCommentButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        whichCommentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createReportCommentButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        createReportCommentButton.setEnabled(buttonEnabled());
        createReportCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportCommentDto dto = new ReportCommentDto(reportReasonCommentText.getText().toString(), Integer.valueOf(whichCommentText.getText().toString()));
                create(dto);
            }
        });

    }

    private void create(ReportCommentDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reportCommentCrudInterface = retrofit.create(ReportCommentCRUDInterface.class);
        Call<ReportComment> call = reportCommentCrudInterface.create(dto);
        call.enqueue(new Callback<ReportComment>() {
            @Override
            public void onResponse(Call<ReportComment> call, Response<ReportComment> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                ReportComment reportComment = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), reportComment.getReportReasonComment() + " dodat!!!", Toast.LENGTH_LONG);
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

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
        startActivity(intent);
        Toast.makeText(CreateReportCommentsActivity.this,"Uspesno prijavljen neprikladan sadrzaj",Toast.LENGTH_SHORT).show();
    }

    private boolean buttonEnabled() {
        return reportReasonCommentText.getText().toString().trim().length() > 0 && whichCommentText.getText().toString().trim().length() > 0;
    }
}
