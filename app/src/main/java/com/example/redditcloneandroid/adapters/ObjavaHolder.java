package com.example.redditcloneandroid.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redditcloneandroid.R;

public class ObjavaHolder extends RecyclerView.ViewHolder {

    TextView title, text, community;

    public ObjavaHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.postListItem_title);
        text = itemView.findViewById(R.id.postListItem_text);
        community = itemView.findViewById(R.id.postListItem_community);
    }
}
