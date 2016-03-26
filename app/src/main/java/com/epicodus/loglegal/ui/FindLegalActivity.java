package com.epicodus.loglegal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class FindLegalActivity extends AppCompatActivity {
    public static final String TAG = FindLegalActivity.class.getSimpleName();

    @Bind(R.id.searchQuery) TextView mSearchQuery;
    public ArrayList<Legal> mLegalOffices = new ArrayList<>();
    @Bind(R.id.legalOfficesListView) ListView mLegalOfficesListView;

//    @Bind(R.id.lawOfficesList) ListView mLawOfficesList;
//    String[] lawOffices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_legal);
        ButterKnife.bind(this);

        Intent findLegalActivityIntent = getIntent();
        String zipcode = findLegalActivityIntent.getStringExtra("zipcode");
        mSearchQuery.setText("You Searched: " + zipcode);

        getLegalOffices(zipcode);

//        lawOffices = getResources().getStringArray(R.array.law_offices);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lawOffices);
//        mLawOfficesList.setAdapter(adapter);
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

                FindLegalActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] legalNames = new String[mLegalOffices.size()];
                        for (int i = 0; i < legalNames.length; i++) {
                            legalNames[i] = mLegalOffices.get(i).getName();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(FindLegalActivity.this, android.R.layout.simple_list_item_1, legalNames);
                        mLegalOfficesListView.setAdapter(adapter);

                        for (Legal legal : mLegalOffices) {
                            Log.d(TAG, "Name: " + legal.getName());
                            Log.d(TAG, "Phone: " + legal.getPhone());
                            Log.d(TAG, "Website: " + legal.getWebsite());
                            Log.d(TAG, "Image url: " + legal.getImageUrl());
                            Log.d(TAG, "Rating: " + legal.getRating());
                            Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", legal.getAddress()));
                            Log.d(TAG, "Categories: " + legal.getCategories().toString());
                        }
                    }
                });
            }
        });
    }
}
