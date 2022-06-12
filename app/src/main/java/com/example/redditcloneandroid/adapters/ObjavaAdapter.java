package com.example.redditcloneandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.Objava;

import java.util.List;

public class ObjavaAdapter extends RecyclerView.Adapter<ObjavaHolder> {

    private List<Objava> postList;

    public ObjavaAdapter(List<Objava> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public ObjavaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_post_item, parent, false);
        return new ObjavaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjavaHolder holder, int position) {
        Objava objava = postList.get(position);
        holder.title.setText(objava.getTitle());
        holder.text.setText(objava.getText());
        holder.community.setText(objava.getCommunity());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
