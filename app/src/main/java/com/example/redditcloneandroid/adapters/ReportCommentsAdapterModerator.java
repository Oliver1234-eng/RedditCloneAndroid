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
import com.example.redditcloneandroid.activities.DetailActivityReportComment;
import com.example.redditcloneandroid.activities.DetailActivityReportCommentModerator;
import com.example.redditcloneandroid.model.ReportComment;

import java.util.List;

public class ReportCommentsAdapterModerator extends BaseAdapter {

    List<ReportComment> reportComments;

    Context context;
    TextView reportReasonCommentText;
    Button viewReportCommentButton;

    public ReportCommentsAdapterModerator(List<ReportComment> reportComments, Context context) {
        this.reportComments = reportComments;
        this.context = context;
    }

    @Override
    public int getCount() {
        return reportComments.size();
    }

    @Override
    public Object getItem(int i) {
        return reportComments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return reportComments.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_of_report_comments, viewGroup, false);
        }

        reportReasonCommentText = view.findViewById(R.id.reportReasonCommentText);
        reportReasonCommentText.setText(reportComments.get(position).getReportReasonComment());
        viewReportCommentButton = view.findViewById(R.id.viewReportCommentButton);
        viewReportCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDetail(reportComments.get(position).getId());
            }
        });

        return view;
    }

    private void callDetail(int id) {
        Intent intent = new Intent(context, DetailActivityReportCommentModerator.class);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
