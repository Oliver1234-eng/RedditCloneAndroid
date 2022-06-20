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
import com.example.redditcloneandroid.activities.DetailActivityFlair;
import com.example.redditcloneandroid.model.Flair;

import java.util.List;

public class FlairsAdapter extends BaseAdapter {

    List<Flair> flairs;

    Context context;
    TextView flairNameText;
    Button viewFlairButton;

    public FlairsAdapter(List<Flair> flairs, Context context) {
        this.flairs = flairs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return flairs.size();
    }

    @Override
    public Object getItem(int i) {
        return flairs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return flairs.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_of_flairs, viewGroup, false);
        }

        flairNameText = view.findViewById(R.id.flairNameText);
        flairNameText.setText(flairs.get(position).getFlairName());
        viewFlairButton = view.findViewById(R.id.viewFlairButton);
        viewFlairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDetail(flairs.get(position).getId());
            }
        });

        return view;
    }

    private void callDetail(int id) {
        Intent intent = new Intent(context, DetailActivityFlair.class);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
