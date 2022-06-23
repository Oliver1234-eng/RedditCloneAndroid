package com.example.redditcloneandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.SortingComments;

import java.util.List;

public class SortingCommentsAdapter extends ArrayAdapter<SortingComments> {

    public SortingCommentsAdapter(Context context, int resource, List<SortingComments> commentList)
    {
        super(context, resource, commentList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        SortingComments sortingComments = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sorting_comments_cell, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.commentReply);

        tv.setText(sortingComments.getId() + " - " + sortingComments.getReply());

        return convertView;
    }
}
