package com.example.redditcloneandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.activities.UserDetailActivity;
import com.example.redditcloneandroid.adapters.UserAdapter;
import com.example.redditcloneandroid.model.User;
import com.example.redditcloneandroid.tools.Mokap;

public class UserFragment extends ListFragment {

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.map_user_layout, vg, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        User user = Mokap.getUsers().get(position);

        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra("ime", user.getIme());
        intent.putExtra("prezime", user.getPrezime());
        intent.putExtra("email", user.getEmail());
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();

        UserAdapter adapter = new UserAdapter(getActivity());
        setListAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.activity_itemdetail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_refresh){
            Toast.makeText(getActivity(), "Refresh App", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.action_new){
            Toast.makeText(getActivity(), "Create Text", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
