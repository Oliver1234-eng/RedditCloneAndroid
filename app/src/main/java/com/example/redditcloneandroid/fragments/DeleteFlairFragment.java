package com.example.redditcloneandroid.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.redditcloneandroid.interfaces.DeleteFlairInterface;

public class DeleteFlairFragment extends DialogFragment {

    private String message;
    private int id;
    private DeleteFlairInterface deleteFlairInterface;

    public DeleteFlairFragment(String message, int id, DeleteFlairInterface deleteFlairInterface) {
        this.message = message;
        this.id = id;
        this.deleteFlairInterface = deleteFlairInterface;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message + id + "?")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteFlairInterface.delete(id);
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
