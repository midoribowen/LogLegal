package com.epicodus.loglegal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindLegalActivity extends AppCompatActivity {
    @Bind(R.id.lawOfficesList) ListView mLawOfficesList;
    String[] lawOffices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_legal);
        ButterKnife.bind(this);

        lawOffices = getResources().getStringArray(R.array.law_offices);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lawOffices);
        mLawOfficesList.setAdapter(adapter);
    }
}
