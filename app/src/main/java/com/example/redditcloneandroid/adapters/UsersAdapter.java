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
import com.example.redditcloneandroid.activities.DetailActivityUser;
import com.example.redditcloneandroid.model.User;

import java.util.List;

public class UsersAdapter extends BaseAdapter {

    List<User> users;

    Context context;
    TextView usernameText;
    Button viewButtonUsers;

    public UsersAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return users.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_of_users, viewGroup, false);
        }

        usernameText = view.findViewById(R.id.usernameText);
        usernameText.setText(users.get(position).getUsername());
        viewButtonUsers = view.findViewById(R.id.viewButtonUsers);
        viewButtonUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDetail(users.get(position).getId());
            }
        });

        return view;
    }

    private void callDetail(int id) {
        Intent intent = new Intent(context, DetailActivityUser.class);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
