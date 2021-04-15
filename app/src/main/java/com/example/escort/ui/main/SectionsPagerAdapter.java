package com.example.escort.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.escort.CategoryBeachesFragment;
import com.example.escort.CategoryBuildingsFragment;
import com.example.escort.CategoryColonialFragment;
import com.example.escort.CategoryCulturalFragment;
import com.example.escort.CategoryMuseumsFragment;
import com.example.escort.CategoryParksFragment;
import com.example.escort.CategoryPreColonialFragment;
import com.example.escort.CategoryWorkshipFragment;
import com.example.escort.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if (position == 0) {
            return new CategoryPreColonialFragment();
        } else if (position == 1) {
            return new CategoryColonialFragment();
        } else if (position == 2) {
            return new CategoryBeachesFragment();
        } else if (position == 3){
            return new CategoryWorkshipFragment();
        } else if (position == 4) {
            return new CategoryCulturalFragment();
        } else if (position == 5) {
            return new CategoryParksFragment();
        } else if (position == 6){
            return new CategoryMuseumsFragment();
        } else {
            return new CategoryBuildingsFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_precolonial);
        } else if (position == 1) {
            return mContext.getString(R.string.category_colonial);
        } else if (position == 2) {
            return mContext.getString(R.string.category_beaches);
        } else if (position == 3) {
            return mContext.getString(R.string.category_workship);
        } else if (position == 4){
            return  mContext.getString(R.string.category_cultural);
        } else if (position == 5){
            return  mContext.getString(R.string.category_parks);
        } else if (position == 6){
            return  mContext.getString(R.string.category_museum);
        } else {
            return mContext.getString(R.string.category_buildings);
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 8;
    }
}