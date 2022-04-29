package com.example.redditcloneandroid.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.User;
import com.example.redditcloneandroid.tools.Mokap;

public class UserAdapter extends BaseAdapter {

    private Activity activity;

    public UserAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Mokap.getUsers().size();
    }

    @Override
    public Object getItem(int position) {
        return Mokap.getUsers().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        User user = Mokap.getUsers().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.user_list, null);

        TextView ime = (TextView)vi.findViewById(R.id.ime);
        TextView prezime = (TextView)vi.findViewById(R.id.prezime);
        TextView email = (TextView)vi.findViewById(R.id.email);

        ime.setText(user.getIme());
        prezime.setText(user.getPrezime());
        email.setText(user.getEmail());

        return vi;
    }
}
