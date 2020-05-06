package com.jun.fakebili.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.fakebili.R;
import com.jun.fakebili.base.BaseFragment;

public class TrackCartoonFragment extends BaseFragment {

    private View view;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //view = inflater.inflate(R.layout.fragment_track_cartoon_main_home,container,false);
        view = inflater.inflate(R.layout.fragment_track_cartoon_main_home,null);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
