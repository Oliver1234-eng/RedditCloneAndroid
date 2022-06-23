package com.example.redditcloneandroid.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;

public class MainActivityUpvoteDownvote extends AppCompatActivity {

    int mintegerPost1 = 0;
    int mintegerPost2 = 0;
    int mintegerPost3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_upvote_downvote);
    }

    public void increaseIntegerPost1(View view) {
        mintegerPost1 = mintegerPost1 + 1;
        displayPost1(mintegerPost1);
    }

    public void decreaseIntegerPost1(View view) {
        mintegerPost1 = mintegerPost1 - 1;
        displayPost1(mintegerPost1);
    }

    public void increaseIntegerPost2(View view) {
        mintegerPost2 = mintegerPost2 + 1;
        displayPost2(mintegerPost2);
    }

    public void decreaseIntegerPost2(View view) {
        mintegerPost2 = mintegerPost2 - 1;
        displayPost2(mintegerPost2);
    }

    public void increaseIntegerPost3(View view) {
        mintegerPost3 = mintegerPost3 + 1;
        displayPost3(mintegerPost3);
    }

    public void decreaseIntegerPost3(View view) {
        mintegerPost3 = mintegerPost3 - 1;
        displayPost3(mintegerPost3);
    }

    private void displayPost1(int number) {
        TextView displayIntegerPost1 = (TextView) findViewById(
                R.id.integer_number_post1);
        displayIntegerPost1.setText("Objava1: " + number);

    }

    private void displayPost2(int number) {
        TextView displayIntegerPost2 = (TextView) findViewById(
                R.id.integer_number_post2);
        displayIntegerPost2.setText("Objava2: " + number);
    }

    private void displayPost3(int number) {
        TextView displayIntegerPost3 = (TextView) findViewById(
                R.id.integer_number_post3);
        displayIntegerPost3.setText("Objava3: " + number);
    }
}
