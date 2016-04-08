package com.epicodus.loglegal.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.loglegal.LogLegalApplication;
import com.epicodus.loglegal.R;
import com.epicodus.loglegal.adapters.LegalListAdapter;
import com.epicodus.loglegal.models.Legal;
import com.epicodus.loglegal.services.YelpService;
import com.firebase.client.Firebase;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindLegalListFragment extends Fragment {
    public static final String TAG = FindLegalListActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    private Firebase mFirebaseRef;

    @Bind(R.id.legalOfficesRecyclerView) RecyclerView mLegalOfficesRecyclerView;
    private LegalListAdapter mAdapter;
    public ArrayList<Legal> mLegalOffices = new ArrayList<>();

    public FindLegalListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseRef = LogLegalApplication.getAppInstance().getFirebaseRef();
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_legal_list, container, false);
        ButterKnife.bind(this, view);

        mSharedPreferences = view.getContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        mRecentAddress = mSharedPreferences.getString("zipcode", null);
        mEditor = mSharedPreferences.edit();
        if (mRecentAddress != null) {
            getLegalOffices(mRecentAddress);
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
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
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void getLegalOffices(String zipcode) {
        final YelpService yelpService = new YelpService(getContext());

        yelpService.findLegalOffices(zipcode, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mLegalOffices = yelpService.processResults(response);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new LegalListAdapter(getContext(), mLegalOffices);

                        mLegalOfficesRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        mLegalOfficesRecyclerView.setLayoutManager(layoutManager);
                        mLegalOfficesRecyclerView.setHasFixedSize(true);
                        mLegalOfficesRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), R.drawable.divider_shape));
                    }
                });
            }
        });
    }


}
