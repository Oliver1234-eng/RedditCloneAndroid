package com.example.redditcloneandroid.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.redditcloneandroid.interfaces.DeleteCommunityInterface;

public class DeleteCommunityFragment extends DialogFragment {

    private String message;
    private int id;
    private DeleteCommunityInterface deleteCommunityInterface;

    public DeleteCommunityFragment(String message, int id, DeleteCommunityInterface deleteCommunityInterface) {
        this.message = message;
        this.id = id;
        this.deleteCommunityInterface = deleteCommunityInterface;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message + id + "?")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteCommunityInterface.delete(id);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("Action: ", "cancel");
                    }
                });
        return builder.create();
    }
}
