package com.epicodus.loglegal.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.LogFile;
import com.epicodus.loglegal.util.FirebaseRecyclerAdapter;
import com.firebase.client.Query;

public class FirebaseLogFileListAdapter extends FirebaseRecyclerAdapter<LogFileViewHolder, LogFile> {

    public FirebaseLogFileListAdapter(Query query, Class<LogFile> itemClass) {
        super(query, itemClass);
    }

    @Override
    public LogFileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.log_file_list_item, parent, false);
        return new LogFileViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(LogFileViewHolder holder, int position) {
        holder.bindLogFile(getItem(position));
    }

    @Override
    protected void itemAdded(LogFile item, String key, int position) {

    }

    @Override
    protected void itemChanged(LogFile oldItem, LogFile newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(LogFile item, String key, int position) {

    }

    @Override
    protected void itemMoved(LogFile item, String key, int oldPosition, int newPosition) {

    }

}
