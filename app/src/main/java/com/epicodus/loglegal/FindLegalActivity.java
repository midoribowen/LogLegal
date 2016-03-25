package com.epicodus.loglegal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FindLegalActivity extends AppCompatActivity {
    @Bind(R.id.searchQuery) TextView mSearchQuery;

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

        getLawyer(zipcode);

//        lawOffices = getResources().getStringArray(R.array.law_offices);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lawOffices);
//        mLawOfficesList.setAdapter(adapter);
    }

    private void getLawyer(String zipcode) {
        final YelpService yelpService = new YelpService(this);

        yelpService.findLegal(zipcode, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.i("JSON DATA", jsonData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
