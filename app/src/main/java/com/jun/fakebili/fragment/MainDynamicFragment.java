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

import com.jun.fakebili.R;
import com.jun.fakebili.base.BaseFragment;
import com.jun.fakebili.bean.MessageEvent;
import com.jun.fakebili.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class MainDynamicFragment extends BaseFragment {

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @OnClick(R.id.ll_toolbar)
    void openDrawer(){
        EventBus.getDefault().post(new MessageEvent("openDrawer"));
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 要让Fragment中的onCreateOptionsMenu()被回调，必须调用setHasOptionsMenu(true);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_dynamic_main,container,false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        // 必须让Fragment中的Toolbar成为Activity的ActionBar，否则setHasOptionsMenu(true)就没有意义了。
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // 必须调用menu.clear();清空菜单栏，否则可能会出现Activity中的menu与Fragment中的menu重叠。
        menu.clear();
        inflater.inflate(R.menu.main_dynamic_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_edit:
                LogUtil.e("功能待开发");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
