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
import com.example.redditcloneandroid.activities.PostDetailActivity;
import com.example.redditcloneandroid.adapters.PostAdapter;
import com.example.redditcloneandroid.model.Post;
import com.example.redditcloneandroid.tools.Mokap;

public class PostFragment extends ListFragment {

    public static PostFragment newInstance() {
        return new PostFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.map_post_layout, vg, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Post post = Mokap.getPosts().get(position);

        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
        intent.putExtra("community", post.getCommunity());
        intent.putExtra("title", post.getTitle());
        intent.putExtra("text", post.getText());
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();

        PostAdapter adapter = new PostAdapter(getActivity());
        setListAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // ovo korostimo ako je nasa arhitekrura takva da imamo jednu aktivnost
        // i vise fragmentaa gde svaki od njih ima svoj menu unutar toolbar-a
        menu.clear();
        inflater.inflate(R.menu.activity_itemdetail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
