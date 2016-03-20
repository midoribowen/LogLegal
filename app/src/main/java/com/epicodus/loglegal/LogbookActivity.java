package com.epicodus.loglegal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;

public class LogbookActivity extends AppCompatActivity {
    public static final String TAG = LogbookActivity.class.getSimpleName();
    @Bind(R.id.date) TextView mDate;
    @Bind(R.id.time) TextView mTime;
    @Bind(R.id.witnesses) TextView mWitnesses;
    @Bind(R.id.description) TextView mDescription;
    @Bind(R.id.policeBadge) TextView mPoliceBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String witnesses = intent.getStringExtra("witnesses");
        String description = intent.getStringExtra("description");
        String policeBadge = intent.getStringExtra("policeBadge");
        mDate.setText("Date: " + date);
        mTime.setText("Time: " + time);
        mWitnesses.setText("Witnesses: " + witnesses);
        mDescription.setText(description);
        mPoliceBadge.setText("Police Badge #: " + policeBadge);
    }
}
