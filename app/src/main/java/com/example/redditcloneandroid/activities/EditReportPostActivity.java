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
import com.example.redditcloneandroid.dto.ReportPostDto;
import com.example.redditcloneandroid.interfaces.ReportPostCRUDInterface;
import com.example.redditcloneandroid.model.ReportPost;
import com.example.redditcloneandroid.utils.Constants;
import com.example.redditcloneandroid.utils.InputFilterMinMax;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditReportPostActivity extends AppCompatActivity {

    ReportPost reportPost;
    EditText reportReasonPostText;
    EditText whichPostText;
    Button editReportPostButton;

    ReportPostCRUDInterface reportPostCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_report_post);
        Intent detailIntent = getIntent();
        reportPost = (ReportPost) detailIntent.getSerializableExtra("prijavljenaObjava");
        //Log.i("prijavljena objava: ", reportPost.toString());
        reportReasonPostText = findViewById(R.id.reportReasonPostText);
        whichPostText = findViewById(R.id.whichPostText);
        whichPostText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "4")});
        reportReasonPostText.setText(reportPost.getReportReasonPost());
        whichPostText.setText(String.valueOf(reportPost.getWhichPost()));
        editReportPostButton = findViewById(R.id.editReportPostButton);
        reportReasonPostText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editReportPostButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        whichPostText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editReportPostButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editReportPostButton.setEnabled(buttonEnabled());
        editReportPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportPostDto dto = new ReportPostDto(reportReasonPostText.getText().toString(), Integer.valueOf(whichPostText.getText().toString()));
                edit(dto);
            }
        });
    }

    private void edit(ReportPostDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reportPostCrudInterface = retrofit.create(ReportPostCRUDInterface.class);
        int id = reportPost.getId();
        Call<ReportPost> call = reportPostCrudInterface.edit(id, dto);
        call.enqueue(new Callback<ReportPost>() {
            @Override
            public void onResponse(Call<ReportPost> call, Response<ReportPost> response) {
                if(!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                ReportPost reportPost = response.body();
                Toast toast = Toast.makeText(getApplicationContext(), reportPost.getReportReasonPost() + " izmenjen!!!", Toast.LENGTH_LONG);
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

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivityReportPost.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return reportReasonPostText.getText().toString().trim().length() > 0 && whichPostText.getText().toString().trim().length() > 0;
    }
}
