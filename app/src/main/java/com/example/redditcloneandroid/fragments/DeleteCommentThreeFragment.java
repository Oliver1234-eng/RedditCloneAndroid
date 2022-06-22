package com.example.redditcloneandroid.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.redditcloneandroid.interfaces.DeleteCommentThreeInterface;

public class DeleteCommentThreeFragment extends DialogFragment {

    private String message;
    private int id;
    private DeleteCommentThreeInterface deleteCommentThreeInterface;

    public DeleteCommentThreeFragment(String message, int id, DeleteCommentThreeInterface deleteCommentThreeInterface) {
        this.message = message;
        this.id = id;
        this.deleteCommentThreeInterface = deleteCommentThreeInterface;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message + id + "?")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteCommentThreeInterface.delete(id);
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
