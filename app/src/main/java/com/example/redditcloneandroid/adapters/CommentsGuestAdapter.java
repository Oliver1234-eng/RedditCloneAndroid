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
import com.example.redditcloneandroid.activities.DetailActivityCommentGuest;
import com.example.redditcloneandroid.model.CommentGuest;

import java.util.List;

public class CommentsGuestAdapter extends BaseAdapter {

    List<CommentGuest> comments;

    Context context;
    TextView replyText;
    Button viewCommentButton;

    public CommentsGuestAdapter(List<CommentGuest> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return comments.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_of_comments_guest, viewGroup, false);
        }

        replyText = view.findViewById(R.id.replyText);
        replyText.setText(comments.get(position).getReply());
        viewCommentButton = view.findViewById(R.id.viewCommentButton);
        viewCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDetail(comments.get(position).getId());
            }
        });

        return view;
    }

    private void callDetail(int id) {
        Intent intent = new Intent(context, DetailActivityCommentGuest.class);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
