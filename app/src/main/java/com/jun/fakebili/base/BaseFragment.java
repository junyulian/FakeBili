package com.jun.fakebili.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jun.fakebili.highabstract.IFragment;
import com.trello.rxlifecycle3.components.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends RxFragment implements IFragment {


    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        if(view != null){
            mUnbinder = ButterKnife.bind(this,view);
        }
        initData(savedInstanceState);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null && mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
        this.mUnbinder = null;
    }
}
