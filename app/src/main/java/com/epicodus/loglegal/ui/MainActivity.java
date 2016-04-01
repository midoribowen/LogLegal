package com.epicodus.loglegal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.loglegal.LogLegalApplication;
import com.epicodus.loglegal.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Firebase mFirebaseRef;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawer;
    @Bind(R.id.nav_view) NavigationView mNavigationView;

    @Bind(R.id.dateInput) EditText mDateInput;
    @Bind(R.id.timeInput) EditText mTimeInput;
    @Bind(R.id.witnessesInput) EditText mWitnessesInput;
    @Bind(R.id.descriptionInput) EditText mDescriptionInput;
    @Bind(R.id.policeBadgeInput) EditText mPoliceBadgeInput;
    @Bind(R.id.addNewIncidentButton) Button mAddNewIncidentButton;

    @Bind(R.id.logbookActivityButton) FloatingActionButton mLogbookActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFirebaseRef = LogLegalApplication.getAppInstance().getFirebaseRef();
        checkForAuthenticatedUser();

        setSupportActionBar(mToolbar);

        // Click listeners
            //For Buttons
        mAddNewIncidentButton.setOnClickListener(this);

        mLogbookActivityButton.setOnClickListener(this);

            // For Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

    } // END onCreate

    // Check for Authenticated User - if not logged in, send to LoginActivity
    private void checkForAuthenticatedUser() {
        AuthData authData = mFirebaseRef.getAuth();
        if (authData == null) {
            goToLoginActivity();
        }
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    // Handles button clicks for adding a new incident, navigating to findLegalActivity, and navigating to LogbookActivity
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.addNewIncidentButton:
                String date = mDateInput.getText().toString();
                String time = mTimeInput.getText().toString();
                String witnesses = mWitnessesInput.getText().toString();
                String description = mDescriptionInput.getText().toString();
                String policeBadge = mPoliceBadgeInput.getText().toString();

                Intent addNewIncidentActivityIntent = new Intent(this, LogbookActivity.class);

                addNewIncidentActivityIntent.putExtra("date", date);
                addNewIncidentActivityIntent.putExtra("time", time);
                addNewIncidentActivityIntent.putExtra("witnesses", witnesses);
                addNewIncidentActivityIntent.putExtra("description", description);
                addNewIncidentActivityIntent.putExtra("policeBadge", policeBadge);
                startActivity(addNewIncidentActivityIntent);
                break;
            case R.id.findLegalButton:
                Intent findLegalActivityIntent = new Intent(this, FindLegalListActivity.class);
                startActivity(findLegalActivityIntent);
                break;
            case R.id.logbookActivityButton:
                Intent logbookActivityIntent = new Intent(this, LogbookActivity.class);
                startActivity(logbookActivityIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Navigates to LoginActivity and logs a user out
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.action_logout:
                this.logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mFirebaseRef.unauth();
        Toast.makeText(MainActivity.this, "Logging Out...", Toast.LENGTH_SHORT).show();
        goToLoginActivity();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_legal) {
            // Navigates to FindLegalListActivity
            Intent intent = new Intent(this, FindLegalListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logs) {
            // Navigates to LogbookActivity
            Intent intent = new Intent(this, LogbookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_camera) {
            // Navigates to CameraActivity
        } else if (id == R.id.nav_settings) {
            // Navigates to SettingsActivity
        } else if (id == R.id.nav_send) {
            // Navigates to SendActivity
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
