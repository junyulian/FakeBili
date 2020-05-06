package com.jun.fakebili;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.jun.fakebili.base.BaseActivity;
import com.jun.fakebili.base.BaseFragment;
import com.jun.fakebili.bean.MessageEvent;
import com.jun.fakebili.factory.NavigationFragmentFactory;
import com.jun.fakebili.helper.SystemBarHelper;
import com.jun.fakebili.utils.ArmsUtils;
import com.jun.fakebili.utils.LogUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{


    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    @BindView(R.id.ll_root)
    LinearLayout mLlRoot;

    @BindView(R.id.nav)
    NavigationView mNav;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initStatusBar();
        initFragmentation();
        initNavigationView();
    }

    private void initFragmentation() {
        replaceFragment(NavigationFragmentFactory.getFragmentById(R.id.nav_home));
    }

    private void initNavigationView() {
        mNav.setBackgroundColor(ArmsUtils.getColor(this,R.color.nav_menu_bg));
        removeNavigationViewScrollbar(mNav);
        mNav.setCheckedItem(R.id.nav_home);
        mNav.setNavigationItemSelectedListener(this);
    }

    private void initStatusBar() {
        //状态栏高度为0， 状态栏时间 网络那些正常显示 ，但整个状态栏不计高度 ， APP工具栏覆盖到状态栏下方，状态栏背景色改变
        SystemBarHelper.tintStatusBarForDrawer(this,mDrawer, ArmsUtils.getColor(this,R.color.colorPrimary));
        //侧边栏 设置padding值为状态栏高度， 这样APP侧边栏不会在状态栏下面显示
        SystemBarHelper.setPadding(this,mNav.getHeaderView(0));
        //主页面 设置padding值为状态栏高度， 这样APP侧边栏不会在状态栏下面显示
        SystemBarHelper.setPadding(this,mLlRoot);
    }

    /**
     * 侧加栏条目点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
        replaceFragment(NavigationFragmentFactory.getFragmentById(item.getItemId()));
        return true;
    }

    private <T extends BaseFragment> void replaceFragment(T t){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content,t);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void closeDrawer(){
        mDrawer.closeDrawer(GravityCompat.START);
    }

    private void removeNavigationViewScrollbar(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    //EventBus处理事件  在主线程处理事件，  粘性事件,事件发送时未注册可待注册后再处理
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void openDrawer(MessageEvent event){
        if("openDrawer".equals(event.getMessage())){
            if(!mDrawer.isDrawerOpen(GravityCompat.START)){
                mDrawer.openDrawer(GravityCompat.START);
            }
        }
    }

}
