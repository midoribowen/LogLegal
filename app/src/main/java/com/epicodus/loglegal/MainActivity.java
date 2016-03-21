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
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawer;
    @Bind(R.id.nav_view) NavigationView mNavigationView;

    @Bind(R.id.username) EditText mUsername;
    @Bind(R.id.loginButton) Button mLoginButton;
    @Bind(R.id.welcomeMessage) TextView mWelcomeMessage;

    @Bind(R.id.addNewIncidentButton) Button mAddNewIncidentButton;
    @Bind(R.id.findLegalButton) Button mFindLegalButton;
    @Bind(R.id.logbookActivityButton) FloatingActionButton mLogbookActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        // Click listeners
            //For Buttons
        mLoginButton.setOnClickListener(this);

        mAddNewIncidentButton.setOnClickListener(this);

        mFindLegalButton.setOnClickListener(this);

        mLogbookActivityButton.setOnClickListener(this);

            // Fo Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

    } // END onCreate


    // Handles button clicks for showing welcome message for fake login, navigating to findLegalActivity, and navigating to LogbookActivity
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.loginButton:
                String username = mUsername.getText().toString();
                mWelcomeMessage.setText("Welcome, " + username + "!");
                break;
            case R.id.addNewIncidentButton:
                Intent addNewIncidentActivityIntent = new Intent(MainActivity.this, AddNewIncidentActivity.class);
                startActivity(addNewIncidentActivityIntent);
                break;
            case R.id.findLegalButton:
                Intent findLegalActivityIntent = new Intent(MainActivity.this, FindLegalActivity.class);
                startActivity(findLegalActivityIntent);
                break;
            case R.id.logbookActivityButton:
                Intent logbookActivityIntent = new Intent(MainActivity.this, LogbookActivity.class);
                startActivity(logbookActivityIntent);
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
