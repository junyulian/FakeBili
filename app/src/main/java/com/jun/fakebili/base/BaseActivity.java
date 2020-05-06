package com.jun.fakebili.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jun.fakebili.highabstract.IActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements IActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        try {
            int layoutResId = initView(savedInstanceState);
            if(layoutResId != 0){
                setContentView(layoutResId);
                //绑定到butterknife
                mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null && mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
        this.mUnbinder = null;
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }
}
