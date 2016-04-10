package com.epicodus.loglegal.adapters;


import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.loglegal.LogLegalApplication;
import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.LogFile;
import com.epicodus.loglegal.util.FirebaseRecyclerAdapter;
import com.epicodus.loglegal.util.ItemTouchHelperAdapter;
import com.epicodus.loglegal.util.OnStartDragListener;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.util.Collections;

public class FirebaseLogFileListAdapter extends FirebaseRecyclerAdapter<LogFileViewHolder, LogFile> implements ItemTouchHelperAdapter {

    private final OnStartDragListener mDragStartListener;

    public FirebaseLogFileListAdapter(Query query, Class<LogFile> itemClass, OnStartDragListener dragStartListener) {
        super(query, itemClass);
        mDragStartListener = dragStartListener;
    }

    @Override
    public LogFileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.log_file_list_item, parent, false);
        return new LogFileViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(final LogFileViewHolder holder, int position) {
        holder.bindLogFile(getItem(position));
        holder.mDragIcon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(getItems(), fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        Firebase ref = LogLegalApplication.getAppInstance().getFirebaseRef();
        ref.child("logfiles/" + ref.getAuth().getUid() + "/" + getItem(position).getLogFileId()).removeValue();
    }

    @Override
    public int getItemCount() {
        return getItems().size();
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
