package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.SortingPosts;

public class DetailActivitySortingPosts extends AppCompatActivity {

    SortingPosts selectedShape;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sorting_posts);
        getSelectedShape();
        setValues();

    }

    private void getSelectedShape()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedShape = getParsedShape(parsedStringID);
    }

    private SortingPosts getParsedShape(String parseId) {

        for(SortingPosts sortingPost : MainActivitySortingPosts.postList) {
            if (sortingPost.getId().equals(parseId)) {
                return sortingPost;
            }
        }
        return null;
    }

    private void setValues()
    {
        TextView tv = (TextView) findViewById(R.id.postTitle);

        tv.setText(selectedShape.getTitle());
    }
}
