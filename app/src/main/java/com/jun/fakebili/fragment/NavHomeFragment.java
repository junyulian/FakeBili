package com.jun.fakebili.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jun.fakebili.R;
import com.jun.fakebili.base.BaseFragment;
import com.jun.fakebili.bean.TabEntity;
import com.jun.fakebili.factory.MainFragmentFractory;
import com.jun.fakebili.utils.LogUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class NavHomeFragment extends BaseFragment {

    @BindView(R.id.bottom_bar)
    CommonTabLayout mBottomBar;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_nav,container,false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        initFragmentation();
        initBottomBar();
    }

    private void initBottomBar() {

        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        customTabEntities.add(new TabEntity("首页",R.mipmap.ic_home_selected,R.mipmap.ic_home_unselected));
        customTabEntities.add(new TabEntity("分区",R.mipmap.ic_category_selected,R.mipmap.ic_category_unselected));
        customTabEntities.add(new TabEntity("动态",R.mipmap.ic_dynamic_selected,R.mipmap.ic_dynamic_unselected));
        customTabEntities.add(new TabEntity("消息",R.mipmap.ic_communicate_selected,R.mipmap.ic_communicate_unselected));

        mBottomBar.setTabData(customTabEntities);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                replaceFragment(MainFragmentFractory.getFragmentByPositoin(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    private void initFragmentation() {
        replaceFragment(MainFragmentFractory.getFragmentByPositoin(0));
    }


    private <T extends BaseFragment> void replaceFragment(T t){
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_main_content,t);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
