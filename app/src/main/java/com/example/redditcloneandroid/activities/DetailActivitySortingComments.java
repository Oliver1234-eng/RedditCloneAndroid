package com.example.redditcloneandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.SortingComments;

public class DetailActivitySortingComments extends AppCompatActivity {

    SortingComments selectedShape;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sorting_comments);
        getSelectedShape();
        setValues();

    }

    private void getSelectedShape()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedShape = getParsedShape(parsedStringID);
    }

    private SortingComments getParsedShape(String parseId) {

        for(SortingComments sortingComment : MainActivitySortingComments.commentList) {
            if (sortingComment.getId().equals(parseId)) {
                return sortingComment;
            }
        }
        return null;
    }

    private void setValues()
    {
        TextView tv = (TextView) findViewById(R.id.commentReply);

        tv.setText(selectedShape.getReply());
    }
}
