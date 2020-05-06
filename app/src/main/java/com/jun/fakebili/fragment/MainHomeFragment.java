package com.jun.fakebili.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.jun.fakebili.R;
import com.jun.fakebili.adapter.MainHomeFragmentAdapter;
import com.jun.fakebili.base.BaseFragment;
import com.jun.fakebili.bean.MessageEvent;
import com.jun.fakebili.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class MainHomeFragment extends BaseFragment {

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @OnClick(R.id.ll_toolbar)
    void openDrawer(){
        EventBus.getDefault().post(new MessageEvent("openDrawer"));
    }

    private View mRootView;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home_main,container,false);
        return mRootView;
    }



    @Override
    public void initData(Bundle savedInstanceState) {
        initToolBar();
        initTabLayoutAndViewPager();
    }

    private void initTabLayoutAndViewPager() {
        viewPager.setAdapter(new MainHomeFragmentAdapter(getChildFragmentManager(),(AppCompatActivity)getActivity()));
        viewPager.setCurrentItem(1);
        tabLayout.setViewPager(viewPager);

    }

    private void initToolBar() {
        // 要让Fragment中的onCreateOptionsMenu()被回调，必须调用setHasOptionsMenu(true);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // 必须调用menu.clear();清空菜单栏，否则可能会出现Activity中的menu与Fragment中的menu重叠。
        menu.clear();
        inflater.inflate(R.menu.main_home_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_active:
                LogUtil.e("--活动功能待开发");
                break;
            case R.id.main_game:
                LogUtil.e("--游戏功能待开发");
                break;
            case R.id.main_download:
                LogUtil.e("--下载功能待开发");
                break;
            case R.id.main_search:
                LogUtil.e("--搜索功能待开发");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        //ViewPager里面的fragment没有调onDestroy 会导致view重复添加 所以需要在此删除
        viewPager.removeAllViews();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
