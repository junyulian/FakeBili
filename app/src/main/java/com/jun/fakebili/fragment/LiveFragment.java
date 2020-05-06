package com.jun.fakebili.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.fakebili.R;
import com.jun.fakebili.base.BaseFragment;
import com.jun.fakebili.utils.LogUtil;

public class LiveFragment extends BaseFragment {

    private View mRootView;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mRootView == null){
            //mRootView = inflater.inflate(R.layout.fragment_live_main_home,container,false);
            mRootView = inflater.inflate(R.layout.fragment_live_main_home,null);
        }
        return mRootView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


}
