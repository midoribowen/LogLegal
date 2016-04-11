package com.epicodus.loglegal.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.loglegal.R;
import com.epicodus.loglegal.adapters.FindLegalPagerAdapter;
import com.epicodus.loglegal.models.Legal;
import com.epicodus.loglegal.util.ScaleAndFadePageTransformer;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindLegalDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private FindLegalPagerAdapter adapterViewPager;
    ArrayList<Legal> mLegalOffices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_legal_detail);
        ButterKnife.bind(this);

        mLegalOffices = Parcels.unwrap(getIntent().getParcelableExtra("legalOffices"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        adapterViewPager = new FindLegalPagerAdapter(getSupportFragmentManager(), mLegalOffices);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
        mViewPager.setPageTransformer(true, new ScaleAndFadePageTransformer());
    }
}
