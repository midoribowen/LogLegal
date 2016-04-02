package com.epicodus.loglegal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.LogFile;

import java.util.ArrayList;

public class LogFileListAdapter extends RecyclerView.Adapter<LogFileViewHolder> {
    private ArrayList<LogFile> mLogFiles = new ArrayList<>();
    private Context mContext;

    public LogFileListAdapter(Context context, ArrayList<LogFile> logFiles) {
        mContext = context;
        mLogFiles = logFiles;
    }

    @Override
    public LogFileViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_file_list_item, parent, false);
        LogFileViewHolder viewHolder = new LogFileViewHolder(view, mLogFiles);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LogFileViewHolder holder, int position) {
        holder.bindLogFile(mLogFiles.get(position));
    }

    @Override
    public int getItemCount() {
        return mLogFiles.size();
    }
}
