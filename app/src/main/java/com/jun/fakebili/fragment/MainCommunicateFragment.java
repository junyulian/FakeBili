package com.jun.fakebili.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jun.fakebili.R;
import com.jun.fakebili.base.BaseFragment;
import com.jun.fakebili.bean.MessageEvent;
import com.jun.fakebili.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

public class MainCommunicateFragment extends BaseFragment {



    @OnClick(R.id.ll_toolbar)
    void openDrawer(){
        LogUtil.e("---打开点击");
        EventBus.getDefault().post(new MessageEvent("openDrawer"));
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_communicate_main,container,false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
