package com.sofydev.twitterapp.main.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainSectionsPagerAdapter  extends FragmentPagerAdapter{

    private String[] titles;
    private Fragment[] fragments;

    public MainSectionsPagerAdapter(FragmentManager fm, String[] titles, Fragment[] fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return this.fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
