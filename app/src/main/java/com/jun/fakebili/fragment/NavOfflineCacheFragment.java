package com.jun.fakebili.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.fakebili.R;
import com.jun.fakebili.base.BaseFragment;

public class NavOfflineCacheFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_offline_cache_nav,container,false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
