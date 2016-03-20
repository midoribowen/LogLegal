package com.epicodus.loglegal;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogbookActivity extends AppCompatActivity {
    public static final String TAG = LogbookActivity.class.getSimpleName();
    @Bind(R.id.incident) TextView mIncident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String witnesses = intent.getStringExtra("witnesses");
        String description = intent.getStringExtra("description");
        String policeBadge = intent.getStringExtra("policeBadge");

        Resources incidentRes = getResources();
        String incidentString;

        incidentString = String.format(incidentRes.getString(R.string.incident), date, time, witnesses, description, policeBadge);
        mIncident.setText(incidentString);
    }
}
