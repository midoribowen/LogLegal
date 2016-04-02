package com.epicodus.loglegal.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.epicodus.loglegal.LogLegalApplication;
import com.epicodus.loglegal.R;
import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogfileActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = LogfileActivity.class.getSimpleName();
    private Firebase mFirebaseRef;

    @Bind(R.id.incident) TextView mIncident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logfile);
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

        mFirebaseRef = LogLegalApplication.getAppInstance().getAppInstance().getFirebaseRef();
    }

    @Override
    public void onClick(View view) {

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
