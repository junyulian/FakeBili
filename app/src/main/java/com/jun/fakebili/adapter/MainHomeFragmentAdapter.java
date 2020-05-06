package com.jun.fakebili.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jun.fakebili.R;
import com.jun.fakebili.fragment.ColumnFragment;
import com.jun.fakebili.fragment.LiveFragment;
import com.jun.fakebili.fragment.RecommendFragment;
import com.jun.fakebili.fragment.TrackCartoonFragment;
import com.jun.fakebili.utils.ArmsUtils;

import java.util.ArrayList;

public class MainHomeFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTabTitles;
    private Context mContext;

    public MainHomeFragmentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        mTabTitles = ArmsUtils.getStringArray(context, R.array.tab_titles_main_home);
        if(mFragments.size() == 0){
            mFragments.add(new LiveFragment());
            mFragments.add(new RecommendFragment());
            mFragments.add(new TrackCartoonFragment());
            mFragments.add(new ColumnFragment());
        }

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
