package com.jun.fakebili.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.fakebili.R;
import com.jun.fakebili.base.BaseFragment;
import com.jun.fakebili.bean.IndexData;
import com.jun.fakebili.net.utils.RetrofitUtil;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class RecommendFragment extends BaseFragment {

    private View mRootView;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(mRootView == null){
            //mRootView = inflater.inflate(R.layout.fragment_recommend_main_home,container,false);
            mRootView = inflater.inflate(R.layout.fragment_recommend_main_home,null);
        }
        return mRootView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//        RetrofitUtil.getNetSrvice()
////                .getRecommendIndexData(0,"false",0)
////                .flatMap(new Function<IndexData, ObservableSource<IndexData>>() {
////                    @Override
////                    public ObservableSource<IndexData> apply(IndexData indexData) throws Exception {
////                        //return RetrofitUtil.getNetSrvice()
////
////                    }
////                })
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
