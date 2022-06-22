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
import com.example.redditcloneandroid.activities.DetailActivityCommunity;
import com.example.redditcloneandroid.activities.DetailActivityCommunityAdministrator;
import com.example.redditcloneandroid.model.Community;

import java.util.List;

public class CommunitiesAdapterAdministrator extends BaseAdapter {

    List<Community> communities;

    Context context;
    TextView nameText;
    Button viewButtonCommunities;

    public CommunitiesAdapterAdministrator(List<Community> communities, Context context) {
        this.communities = communities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return communities.size();
    }

    @Override
    public Object getItem(int i) {
        return communities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return communities.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_of_communities, viewGroup, false);
        }

        nameText = view.findViewById(R.id.nameText);
        nameText.setText(communities.get(position).getName());
        viewButtonCommunities = view.findViewById(R.id.viewButtonCommunities);
        viewButtonCommunities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDetail(communities.get(position).getId());
            }
        });

        return view;
    }

    private void callDetail(int id) {
        Intent intent = new Intent(context, DetailActivityCommunityAdministrator.class);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
