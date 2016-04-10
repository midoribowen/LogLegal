package com.epicodus.loglegal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.loglegal.LogLegalApplication;
import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.LogFile;
import com.epicodus.loglegal.ui.LogfileActivity;
import com.epicodus.loglegal.util.ItemTouchHelperViewHolder;
import com.firebase.client.Firebase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogFileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ItemTouchHelperViewHolder {
    @Bind(R.id.logFileNameTextView) TextView mLogFileNameTextView;
    private Context mContext;
    private ArrayList<LogFile> mLogFiles = new ArrayList<>();
    private Firebase mFirebaseRef;

    public LogFileViewHolder(View itemView, ArrayList<LogFile> logFiles) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);

        mLogFiles = logFiles;
        mFirebaseRef = LogLegalApplication.getAppInstance().getFirebaseRef();

        itemView.setOnClickListener(this);
    }

    public void bindLogFile(LogFile logFile) {
        mLogFileNameTextView.setText(logFile.getName());
    }

    @Override
    public void onClick(View v) {
        if (v == itemView) {
            Intent intent = new Intent(mContext, LogfileActivity.class);
            intent.putExtra("chosenLogfile", Parcels.wrap(mLogFiles.get(getLayoutPosition())));
            mContext.startActivity(intent);
        }
    }
    @Override
    public void onItemSelected() {
        // add animation
    }

    @Override
    public void onItemClear() {
        // add animation
    }

}
