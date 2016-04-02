package com.epicodus.loglegal.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.loglegal.LogLegalApplication;
import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.LogFile;
import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddLogFileFragment extends DialogFragment implements View.OnClickListener {
    private Firebase mFirebaseRef;
    private String logFileId;
    private String mCurrentUserId;
    private LogFile mLogFile;

    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.addLogFileButton) Button mAddLogFileButton;

    private Context mContext;

    public AddLogFileFragment() {}

    public static AddLogFileFragment newInstance() {
        return new AddLogFileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseRef = LogLegalApplication.getAppInstance().getFirebaseRef();
        mCurrentUserId = mFirebaseRef.getAuth().getUid();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_log_file, container, false);
        ButterKnife.bind(this, view);

        mAddLogFileButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mAddLogFileButton) {
            String name = mNameEditText.getText().toString();
            createLogFile(name);
            dismiss();
        }
    }

    private void createLogFile(String name) {
        mFirebaseRef = mFirebaseRef.child("logfiles/" + mCurrentUserId + "/").push();
        LogFile logFile = new LogFile(name, mFirebaseRef.getKey().toString());
        mFirebaseRef.setValue(logFile);
    }
}
