package com.epicodus.loglegal.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.loglegal.models.Legal;
import com.epicodus.loglegal.ui.FindLegalDetailFragment;

import java.util.ArrayList;

public class FindLegalPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Legal> mLegalOffices;

    public FindLegalPagerAdapter(FragmentManager fm, ArrayList<Legal> legalOffices) {
        super(fm);
        mLegalOffices = legalOffices;
    }

    @Override
    public Fragment getItem(int position) {
        return FindLegalDetailFragment.newInstance(mLegalOffices.get(position));
    }

    @Override
    public int getCount() {
        return mLegalOffices.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mLegalOffices.get(position).getName();
    }
}
