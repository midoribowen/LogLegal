package com.epicodus.loglegal.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.epicodus.loglegal.adapters.LegalListAdapter;
import com.epicodus.loglegal.models.Legal;
import com.epicodus.loglegal.R;
import com.epicodus.loglegal.services.YelpService;
import com.firebase.client.Firebase;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FindLegalListActivity extends AppCompatActivity {
    public static final String TAG = FindLegalListActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    private Firebase mFirebaseRef;

    @Bind(R.id.legalOfficesRecyclerView) RecyclerView mLegalOfficesRecyclerView;
    private LegalListAdapter mAdapter;
    public ArrayList<Legal> mLegalOffices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_legal_list);
        ButterKnife.bind(this);

        mSharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        mRecentAddress = mSharedPreferences.getString("zipcode", null);
        mEditor = mSharedPreferences.edit();
        if (mRecentAddress != null) {
            getLegalOffices(mRecentAddress);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getLegalOffices(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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

    private void addToSharedPreferences(String zipcode) {
        mEditor.putString("zipcode", zipcode).commit();
    }

    private void logout() {
        mFirebaseRef.unauth();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void getLegalOffices(String zipcode) {
        final YelpService yelpService = new YelpService(this);

        yelpService.findLegalOffices(zipcode, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mLegalOffices = yelpService.processResults(response);

                FindLegalListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new LegalListAdapter(getApplicationContext(), mLegalOffices);

                        mLegalOfficesRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FindLegalListActivity.this);
                        mLegalOfficesRecyclerView.setLayoutManager(layoutManager);
                        mLegalOfficesRecyclerView.setHasFixedSize(true);
                        mLegalOfficesRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), R.drawable.divider_shape));
                    }
                });
            }
        });
    }
}
