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

public class CreateReportPostsActivity extends AppCompatActivity {

    EditText reportReasonPostText;
    EditText whichPostText;
    Button createReportPostButton;

    ReportPostCRUDInterface reportPostCrudInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report_post);
        reportReasonPostText = findViewById(R.id.reportReasonPostText);
        whichPostText = findViewById(R.id.whichPostText);
        whichPostText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "9")});
        createReportPostButton = findViewById(R.id.createReportPostButton);
        reportReasonPostText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createReportPostButton.setEnabled(buttonEnabled());
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
                createReportPostButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        createReportPostButton.setEnabled(buttonEnabled());
        createReportPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportPostDto dto = new ReportPostDto(reportReasonPostText.getText().toString(), Integer.valueOf(whichPostText.getText().toString()));
                create(dto);
            }
        });

    }

    private void create(ReportPostDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reportPostCrudInterface = retrofit.create(ReportPostCRUDInterface.class);
        Call<ReportPost> call = reportPostCrudInterface.create(dto);
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
                Toast toast = Toast.makeText(getApplicationContext(), reportPost.getReportReasonPost() + " dodat!!!", Toast.LENGTH_LONG);
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
        Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
        startActivity(intent);
        Toast.makeText(CreateReportPostsActivity.this,"Uspesno prijavljen neprikladan sadrzaj",Toast.LENGTH_SHORT).show();
    }

    private boolean buttonEnabled() {
        return reportReasonPostText.getText().toString().trim().length() > 0 && whichPostText.getText().toString().trim().length() > 0;
    }
}
