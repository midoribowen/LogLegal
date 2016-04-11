package com.epicodus.loglegal.ui;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.loglegal.LogLegalApplication;
import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.Incident;
import com.firebase.client.Firebase;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddIncidentFragment extends DialogFragment implements View.OnClickListener {
    private Firebase mFirebaseRef;
    private String logFileId;
    private String incidentId;

    @Bind(R.id.incidentDateEditText) EditText mIncidentDateEditText;
    @Bind(R.id.incidentTimeEditText) EditText mIncidentTimeEditText;
    @Bind(R.id.witnessesEditText) EditText mWitnessesEditText;
    @Bind(R.id.descriptionEditText) EditText mDescriptionEditText;
    @Bind(R.id.policeBadgeNumberEditText) EditText mPoliceBadgeNumberEditText;
    @Bind(R.id.addIncidentButton) Button mAddIncidentButton;

    private Context mContext;

    public AddIncidentFragment() {}

    public static AddIncidentFragment newInstance() {
        return new AddIncidentFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_incident, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        logFileId = bundle.getString("logFileId");

        mFirebaseRef = LogLegalApplication.getAppInstance().getFirebaseRef().child("incidents/" + logFileId).push();
        mAddIncidentButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mAddIncidentButton) {
            String incidentDate = mIncidentDateEditText.getText().toString();
            String incidentTime = mIncidentTimeEditText.getText().toString();
            String witnesses = mWitnessesEditText.getText().toString();
            String description = mDescriptionEditText.getText().toString();
            String policeBadgeNumber = mPoliceBadgeNumberEditText.getText().toString();

            createIncident(incidentDate, incidentTime, witnesses, description, policeBadgeNumber);
            dismiss();
        }
    }


    private void createIncident(String incidentDate, String incidentTime, String witnesses, String description, String policeBadgeNumber) {
        Incident incident = new Incident(logFileId, mFirebaseRef.getKey().toString(), incidentDate, incidentTime, witnesses, description, policeBadgeNumber);
        mFirebaseRef.setValue(incident);
    }
}
