package com.epicodus.loglegal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
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
import com.epicodus.loglegal.adapters.FirebaseLogFileListAdapter;
import com.epicodus.loglegal.models.LogFile;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Query mQuery;
    private Firebase mFirebaseRef;
    private String mCurrentUserId;
    private FirebaseLogFileListAdapter mAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawer;
    @Bind(R.id.nav_view) NavigationView mNavigationView;

    @Bind(R.id.addNewLogFileButton) FloatingActionButton mAddNewLogFileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_activity_title);
        ButterKnife.bind(this);


        mFirebaseRef = LogLegalApplication.getAppInstance().getFirebaseRef();
        checkForAuthenticatedUser();

        setSupportActionBar(mToolbar);

        // Click listeners
            //For Buttons
        mAddNewLogFileButton.setOnClickListener(this);

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
        } else {
            mCurrentUserId = mFirebaseRef.getAuth().getUid();
            setUpFirebaseQuery();
            setUpRecyclerView();
        }
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    // Displays list of logfiles in recyclerview
    private void setUpFirebaseQuery() {
        Firebase.setAndroidContext(this);
        String location = mFirebaseRef.child("logfiles/" + mCurrentUserId).toString();
        mQuery = new Firebase(location);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseLogFileListAdapter(mQuery, LogFile.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }


    // Handles button clicks for adding a new incident, navigating to findLegalActivity, and navigating to LogfileActivity
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.addNewLogFileButton:
                launchAddLogFileFragment();
                break;
        }
    }

    private void launchAddLogFileFragment() {
        FragmentManager fm = getSupportFragmentManager();
        AddLogFileFragment addLogFile = AddLogFileFragment.newInstance();
        addLogFile.show(fm, "fragment_add_log_file");
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    // Logs a user out and navigates to LoginActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
            // Navigates to MainActivity
            Intent intent = new Intent(this, MainActivity.class);
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
