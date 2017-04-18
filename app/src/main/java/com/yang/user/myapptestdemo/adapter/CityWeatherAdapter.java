package com.yang.user.myapptestdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by YangJie on 2017/4/17.
 */

public class CityWeatherAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public CityWeatherAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
