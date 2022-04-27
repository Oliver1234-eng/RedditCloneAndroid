package com.example.redditcloneandroid.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.Post;
import com.example.redditcloneandroid.tools.Mokap;

public class PostAdapter extends BaseAdapter {

    private Activity activity;

    public PostAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Mokap.getPosts().size();
    }

    @Override
    public Object getItem(int position) {
        return Mokap.getPosts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        Post post = Mokap.getPosts().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.post_list, null);

        TextView community = (TextView)vi.findViewById(R.id.community_post);
        TextView title = (TextView)vi.findViewById(R.id.title_post);
        TextView text = (TextView)vi.findViewById(R.id.text_post);
        ImageView image = (ImageView)vi.findViewById(R.id.item_icon_post);

        community.setText(post.getCommunity());
        title.setText(post.getTitle());
        text.setText(post.getText());

        if (post.getAvatar() != -1){
            image.setImageResource(post.getAvatar());
        }

        return  vi;
    }
}
