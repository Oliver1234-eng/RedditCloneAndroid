package com.example.redditcloneandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.SortingPosts;

import java.util.List;

public class SortingPostsAdapter extends ArrayAdapter<SortingPosts> {

    public SortingPostsAdapter(Context context, int resource, List<SortingPosts> postList)
    {
        super(context, resource, postList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        SortingPosts sortingPosts = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sorting_posts_cell, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.postTitle);

        tv.setText(sortingPosts.getId() + " - " + sortingPosts.getTitle());

        return convertView;
    }
}
