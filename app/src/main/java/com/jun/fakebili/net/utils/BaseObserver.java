package com.jun.fakebili.net.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.jun.fakebili.utils.LogUtil;


import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {
    private final String tag = this.getClass().getSimpleName();
    @Override
    public void onSubscribe(Disposable d) {
        Log.e(tag,"subscribe");
    }

    @Override
    public void onNext(T data) {
        onSuccess(data);
    }

    @Override
    public void onError(Throwable e) {

        LogUtil.e("------onError-----"+new Gson().toJson(e));
        try {
            JSONObject obj = new JSONObject(new Gson().toJson(e));
            if(obj.has("code")){
                int code = obj.getInt("code");
                LogUtil.e("errorCode:"+code);
            }
        }catch (Exception exp){
            LogUtil.e("onError:"+exp.getMessage());
        }

        onFailure(RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T data);


    public abstract void onFailure(String errorMsg);


}
