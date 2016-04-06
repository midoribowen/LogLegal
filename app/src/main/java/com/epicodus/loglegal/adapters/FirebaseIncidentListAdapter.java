package com.epicodus.loglegal.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.loglegal.R;
import com.epicodus.loglegal.models.Incident;
import com.epicodus.loglegal.util.FirebaseRecyclerAdapter;
import com.firebase.client.Query;

public class FirebaseIncidentListAdapter extends FirebaseRecyclerAdapter<IncidentViewHolder, Incident> {

    public FirebaseIncidentListAdapter(Query query, Class<Incident> itemClass) {
        super(query, itemClass);
    }

    @Override
    public IncidentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.incident_list_item, parent, false);
        return new IncidentViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(IncidentViewHolder holder, int position) {
        holder.bindIncident(getItem(position));
    }

    @Override
    protected void itemAdded(Incident item, String key, int position) {

    }

    @Override
    protected void itemChanged(Incident oldItem, Incident newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Incident item, String key, int position) {

    }

    @Override
    protected void itemMoved(Incident item, String key, int oldPosition, int newPosition) {

    }
}
