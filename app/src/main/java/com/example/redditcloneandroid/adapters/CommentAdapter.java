package com.example.redditcloneandroid.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.Comment;
import com.example.redditcloneandroid.model.Post;
import com.example.redditcloneandroid.tools.Mokap;

public class CommentAdapter extends BaseAdapter {

    private Activity activity;

    public CommentAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Mokap.getComments().size();
    }

    @Override
    public Object getItem(int position) {
        return Mokap.getComments().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        Comment comment = Mokap.getComments().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.comment_list, null);

        TextView tekst = (TextView)vi.findViewById(R.id.tekst);
        TextView datum = (TextView)vi.findViewById(R.id.datum);

        tekst.setText(comment.getTekst());
        datum.setText(comment.getDatum());

        return  vi;
    }
}
