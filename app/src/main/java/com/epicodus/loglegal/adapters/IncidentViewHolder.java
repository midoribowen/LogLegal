package com.epicodus.loglegal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.loglegal.LogLegalApplication;
import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.Incident;
import com.firebase.client.Firebase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IncidentViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.incidentDateTextView) TextView mIncidentDateTextView;
    @Bind(R.id.incidentTimeTextView) TextView mIncidentTimeTextView;
    @Bind(R.id.witnessesTextView) TextView mWitnessesTextView;
    @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;
    @Bind(R.id.policeBadgeNumberTextView) TextView mPoliceBadgeNumberTextView;

    private Context mContext;
    private ArrayList<Incident> mIncidents = new ArrayList<>();
    private Firebase mFirebaseRef;

    public IncidentViewHolder(View itemView, ArrayList<Incident> incidents) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mIncidents = incidents;
        mFirebaseRef = LogLegalApplication.getAppInstance().getFirebaseRef();
    }

    public void bindIncident(Incident incident) {
        mIncidentDateTextView.setText(incident.getIncidentDate());
        mIncidentTimeTextView.setText(incident.getIncidentTime());
        mWitnessesTextView.setText(incident.getWitnesses());
        mDescriptionTextView.setText(incident.getDescription());
        mPoliceBadgeNumberTextView.setText(incident.getPoliceBadgeNumber());
    }
}
