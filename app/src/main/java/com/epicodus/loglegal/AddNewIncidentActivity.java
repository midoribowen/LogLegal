package com.epicodus.loglegal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddNewIncidentActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.addIncidentButton) Button mAddIncidentButton;
    @Bind(R.id.dateInput) EditText mDateInput;
    @Bind(R.id.timeInput) EditText mTimeInput;
    @Bind(R.id.witnessesInput) EditText mWitnessesInput;
    @Bind(R.id.descriptionInput) EditText mDescriptionInput;
    @Bind(R.id.policeBadgeInput) EditText mPoliceBadgeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_incident);
        ButterKnife.bind(this);

        mAddIncidentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.addIncidentButton:
                String date = mDateInput.getText().toString();
                String time = mTimeInput.getText().toString();
                String witnesses = mWitnessesInput.getText().toString();
                String description = mDescriptionInput.getText().toString();
                String policeBadge = mPoliceBadgeInput.getText().toString();

                Intent intent = new Intent(AddNewIncidentActivity.this, LogbookActivity.class);

                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("witnesses", witnesses);
                intent.putExtra("description", description);
                intent.putExtra("policeBadge", policeBadge);
                startActivity(intent);
                break;
        }
    }
}
