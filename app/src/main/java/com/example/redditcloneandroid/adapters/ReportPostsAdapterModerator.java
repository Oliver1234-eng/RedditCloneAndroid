package com.example.redditcloneandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.activities.DetailActivityReportPostModerator;
import com.example.redditcloneandroid.model.ReportPost;

import java.util.List;

public class ReportPostsAdapterModerator extends BaseAdapter {

    List<ReportPost> reportPosts;

    Context context;
    TextView reportReasonPostText;
    Button viewReportPostButton;

    public ReportPostsAdapterModerator(List<ReportPost> reportPosts, Context context) {
        this.reportPosts = reportPosts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return reportPosts.size();
    }

    @Override
    public Object getItem(int i) {
        return reportPosts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return reportPosts.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_of_report_posts, viewGroup, false);
        }

        reportReasonPostText = view.findViewById(R.id.reportReasonPostText);
        reportReasonPostText.setText(reportPosts.get(position).getReportReasonPost());
        viewReportPostButton = view.findViewById(R.id.viewReportPostButton);
        viewReportPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDetail(reportPosts.get(position).getId());
            }
        });

        return view;
    }

    private void callDetail(int id) {
        Intent intent = new Intent(context, DetailActivityReportPostModerator.class);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
