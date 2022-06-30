package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;

public class MainActivityUpvoteDownvoteComment extends AppCompatActivity {

    int mintegerComment1 = 0;
    int mintegerComment2 = 0;
    int mintegerComment3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_upvote_downvote_comment);

        Button nazad = (Button) findViewById(R.id.nazad);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityUpvoteDownvoteComment.this, ProfilActivity.class);

                startActivity(intent);
            }
        });
    }

    public void increaseIntegerComment1(View view) {
        mintegerComment1 = mintegerComment1 + 1;
        displayPost1(mintegerComment1);
    }

    public void decreaseIntegerComment1(View view) {
        mintegerComment1 = mintegerComment1 - 1;
        displayPost1(mintegerComment1);
    }

    public void increaseIntegerComment2(View view) {
        mintegerComment2 = mintegerComment2 + 1;
        displayPost2(mintegerComment2);
    }

    public void decreaseIntegerComment2(View view) {
        mintegerComment2 = mintegerComment2 - 1;
        displayPost2(mintegerComment2);
    }

    public void increaseIntegerComment3(View view) {
        mintegerComment3 = mintegerComment3 + 1;
        displayPost3(mintegerComment3);
    }

    public void decreaseIntegerPost3(View view) {
        mintegerComment3 = mintegerComment3 - 1;
        displayPost3(mintegerComment3);
    }

    private void displayPost1(int number) {
        TextView displayIntegerComment1 = (TextView) findViewById(
                R.id.integer_number_comment1);
        displayIntegerComment1.setText("Komentar1: " + number);

    }

    private void displayPost2(int number) {
        TextView displayIntegerComment2 = (TextView) findViewById(
                R.id.integer_number_comment2);
        displayIntegerComment2.setText("Komentar2: " + number);
    }

    private void displayPost3(int number) {
        TextView displayIntegerComment3 = (TextView) findViewById(
                R.id.integer_number_comment3);
        displayIntegerComment3.setText("Komentar3: " + number);
    }
}
