package com.epicodus.loglegal.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.epicodus.loglegal.adapters.LegalListAdapter;
import com.epicodus.loglegal.models.Legal;
import com.epicodus.loglegal.R;
import com.epicodus.loglegal.services.YelpService;

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
    private String mRecentAddress;

    @Bind(R.id.searchQuery) TextView mSearchQuery;

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
        if (mRecentAddress != null) {
            getLegalOffices(mRecentAddress);
        }

        Intent findLegalActivityIntent = getIntent();
        String zipcode = findLegalActivityIntent.getStringExtra("zipcode");
        mSearchQuery.setText("You Searched: " + zipcode);

        getLegalOffices(zipcode);
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
