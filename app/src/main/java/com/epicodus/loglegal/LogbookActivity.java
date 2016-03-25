package com.epicodus.loglegal;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogbookActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = LogbookActivity.class.getSimpleName();
    @Bind(R.id.incident) TextView mIncident;

    @Bind(R.id.zipcodeInput) EditText mZipcodeInput;
    @Bind(R.id.findLegalButton) Button mFindLegalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);
        ButterKnife.bind(this);

        Intent addNewIncidentActivityIntent = getIntent();
        String date = addNewIncidentActivityIntent.getStringExtra("date");
        String time = addNewIncidentActivityIntent.getStringExtra("time");
        String witnesses = addNewIncidentActivityIntent.getStringExtra("witnesses");
        String description = addNewIncidentActivityIntent.getStringExtra("description");
        String policeBadge = addNewIncidentActivityIntent.getStringExtra("policeBadge");

        Resources incidentRes = getResources();
        String incidentString;

        incidentString = String.format(incidentRes.getString(R.string.incident), date, time, witnesses, description, policeBadge);
        mIncident.setText(incidentString);

        mFindLegalButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.findLegalButton:
                String zipcode = mZipcodeInput.getText().toString();

                Intent findLegalActivityIntent = new Intent(this, FindLegalActivity.class);
                findLegalActivityIntent.putExtra("zipcode", zipcode);
                startActivity(findLegalActivityIntent);
                break;
        }
    }
}
