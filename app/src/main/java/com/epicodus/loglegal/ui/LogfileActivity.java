package com.epicodus.loglegal.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.epicodus.loglegal.LogLegalApplication;
import com.epicodus.loglegal.R;
import com.epicodus.loglegal.adapters.FirebaseIncidentListAdapter;
import com.epicodus.loglegal.models.Incident;
import com.epicodus.loglegal.models.LogFile;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogfileActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = LogfileActivity.class.getSimpleName();
    private Query mQuery;
    private Firebase mFirebaseRef;
    private FirebaseIncidentListAdapter mAdapter;
    private LogFile mLogfile;

    @Bind(R.id.incidentRecyclerView) RecyclerView mIncidentRecyclerView;
    @Bind(R.id.addNewIncidentButton) FloatingActionButton mAddNewIncidentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logfile);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mLogfile = Parcels.unwrap(bundle.getParcelable("chosenLogfile"));

        Firebase.setAndroidContext(this);
        mFirebaseRef = LogLegalApplication.getAppInstance().getFirebaseRef();

        setUpFirebaseQuery();
        setUpRecyclerView();

        mAddNewIncidentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addNewIncidentButton:
                launchAddIncidentFragment();
                break;
        }
    }

    private void launchAddIncidentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        AddIncidentFragment addIncident = AddIncidentFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("logFileId", mLogfile.getLogFileId());
        addIncident.setArguments(bundle);
        addIncident.show(fm, "fragment_add_incident");
    }

    private void setUpFirebaseQuery() {
        String location = mFirebaseRef.child("incidents/" + mLogfile.getLogFileId()).toString();
        mQuery = new Firebase(location);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseIncidentListAdapter(mQuery, Incident.class);
        mIncidentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mIncidentRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                this.logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mFirebaseRef.unauth();
        goToLoginActivity();
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
