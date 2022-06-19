package com.example.redditcloneandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.activities.DetailActivityPost;
import com.example.redditcloneandroid.model.Post;

import java.util.List;

public class PostsAdapter extends BaseAdapter {

    List<Post> posts;

    Context context;
    TextView titleText;
    Button viewPostButton;

    public PostsAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return posts.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_of_posts, viewGroup, false);
        }

        titleText = view.findViewById(R.id.titleText);
        titleText.setText(posts.get(position).getTitle());
        viewPostButton = view.findViewById(R.id.viewPostButton);
        viewPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDetail(posts.get(position).getId());
            }
        });

        return view;
    }

    private void callDetail(int id) {
        Intent intent = new Intent(context, DetailActivityPost.class);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
