package com.epicodus.loglegal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.username) EditText mUsername;
    @Bind(R.id.loginButton) Button mLoginButton;
    @Bind(R.id.welcomeMessage) TextView mWelcomeMessage;

    @Bind(R.id.dateInput) EditText mDateInput;
    @Bind(R.id.timeInput) EditText mTimeInput;
    @Bind(R.id.witnessesInput) EditText mWitnessesInput;
    @Bind(R.id.descriptionInput) EditText mDescriptionInput;
    @Bind(R.id.policeBadgeInput) EditText mPoliceBadgeInput;
    @Bind(R.id.addIncidentButton) Button mAddIncidentButton;

    @Bind(R.id.findLegalButton) Button mFindLegalButton;
    @Bind(R.id.fab) FloatingActionButton mLogbookActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Fake Login with welcome message
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                mWelcomeMessage.setText("Welcome, " + username + "!");
            }
        });

        // Adds a new incident to the Logbook
        mAddIncidentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = mDateInput.getText().toString();
                String time = mTimeInput.getText().toString();
                String witnesses = mWitnessesInput.getText().toString();
                String description = mDescriptionInput.getText().toString();
                String policeBadge = mPoliceBadgeInput.getText().toString();

                Intent intent = new Intent(MainActivity.this, LogbookActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("witnesses", witnesses);
                intent.putExtra("description", description);
                intent.putExtra("policeBadge", policeBadge);
                startActivity(intent);
            }
        });

        // Navigates to FindLegalActivity
        mFindLegalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindLegalActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to LogbookActivity
        mLogbookActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LogbookActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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

    // Navigates to LoginActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_legal) {
            // Navigates to FindLegalActivity
            Intent intent = new Intent(this, FindLegalActivity.class);
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
