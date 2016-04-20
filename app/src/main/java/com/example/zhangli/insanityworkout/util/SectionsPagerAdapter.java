package com.example.zhangli.insanityworkout.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        //   mFragments = fragments;
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
         return 3;
        //return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
       /* switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        } */
        return null;
    }
}

