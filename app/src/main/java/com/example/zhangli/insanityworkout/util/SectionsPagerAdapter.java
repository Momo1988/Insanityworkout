package com.example.zhangli.insanityworkout.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager Fm;
    private final String[] titles = { "1", "2", "3" };
    private static final String TAG = "SectionsPagerAdapter";

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        Fm = fm;
        //   mFragments = fragments;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below)
        //return PlaceholderFragment.newcolor(position);
        return PlaceholderFragment.newFragment(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return titles.length;
        //return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }

    @Override
    public int getItemPosition(Object object) {
    return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        Log.d(TAG,"Destroy " + position);
    }

}

