package com.example.redditcloneandroid.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redditcloneandroid.R;

public class KorisnikHolder extends RecyclerView.ViewHolder {

    TextView username, password, email;

    public KorisnikHolder(@NonNull View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.userListItem_username);
        password = itemView.findViewById(R.id.userListItem_password);
        email = itemView.findViewById(R.id.userListItem_email);
    }
}
