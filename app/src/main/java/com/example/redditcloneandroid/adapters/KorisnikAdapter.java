package com.example.redditcloneandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.Korisnik;

import java.util.List;

public class KorisnikAdapter extends RecyclerView.Adapter<KorisnikHolder> {

    private List<Korisnik> userList;

    public KorisnikAdapter(List<Korisnik> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public KorisnikHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user_item, parent, false);
        return new KorisnikHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KorisnikHolder holder, int position) {
        Korisnik korisnik = userList.get(position);
        holder.username.setText(korisnik.getUsername());
        holder.password.setText(korisnik.getPassword());
        holder.email.setText(korisnik.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}
