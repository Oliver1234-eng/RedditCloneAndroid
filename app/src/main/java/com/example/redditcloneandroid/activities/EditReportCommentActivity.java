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

public class EditReportCommentActivity extends AppCompatActivity {

    ReportComment reportComment;
    EditText reportReasonCommentText;
    EditText whichCommentText;
    Button editReportCommentButton;

    ReportCommentCRUDInterface reportCommentCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_report_comment);
        Intent detailIntent = getIntent();
        reportComment = (ReportComment) detailIntent.getSerializableExtra("prijavljeniKomentar");
        //Log.i("prijavljeni komentar: ", reportComment.toString());
        reportReasonCommentText = findViewById(R.id.reportReasonCommentText);
        whichCommentText = findViewById(R.id.whichCommentText);
        whichCommentText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "4")});
        reportReasonCommentText.setText(reportComment.getReportReasonComment());
        whichCommentText.setText(String.valueOf(reportComment.getWhichComment()));
        editReportCommentButton = findViewById(R.id.editReportCommentButton);
        reportReasonCommentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editReportCommentButton.setEnabled(buttonEnabled());
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
                editReportCommentButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editReportCommentButton.setEnabled(buttonEnabled());
        editReportCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportCommentDto dto = new ReportCommentDto(reportReasonCommentText.getText().toString(), Integer.valueOf(whichCommentText.getText().toString()));
                edit(dto);
            }
        });
    }

    private void edit(ReportCommentDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reportCommentCrudInterface = retrofit.create(ReportCommentCRUDInterface.class);
        int id = reportComment.getId();
        Call<ReportComment> call = reportCommentCrudInterface.edit(id, dto);
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
                Toast toast = Toast.makeText(getApplicationContext(), reportComment.getReportReasonComment() + " izmenjen!!!", Toast.LENGTH_LONG);
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
        Intent intent = new Intent(getApplicationContext(), MainActivityReportComment.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return reportReasonCommentText.getText().toString().trim().length() > 0 && whichCommentText.getText().toString().trim().length() > 0;
    }
}
