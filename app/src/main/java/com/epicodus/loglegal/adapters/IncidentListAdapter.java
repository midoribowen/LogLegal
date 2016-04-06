package com.epicodus.loglegal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.Incident;

import java.util.ArrayList;

public class IncidentListAdapter extends RecyclerView.Adapter<IncidentViewHolder> {
    private ArrayList<Incident> mIncidents = new ArrayList<>();
    private Context mContext;

    public IncidentListAdapter(Context context, ArrayList<Incident> incidents) {
        mContext = context;
        mIncidents = incidents;
    }

    @Override
    public IncidentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.incident_list_item, parent, false);
        IncidentViewHolder viewHolder = new IncidentViewHolder(view, mIncidents);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IncidentViewHolder holder, int position) {
        holder.bindIncident(mIncidents.get(position));
    }

    @Override
    public int getItemCount() {
        return mIncidents.size();
    }
}
