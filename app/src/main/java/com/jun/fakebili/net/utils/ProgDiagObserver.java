package com.jun.fakebili.net.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


import io.reactivex.disposables.Disposable;

/**
 * 定制的Observer，新增加载框功能
 *
 * @param <T>
 */
public abstract class ProgDiagObserver<T> extends BaseObserver<T> {
    private boolean mShowDialog;
    //private ProgressDialog dialog;
    private Context mContext;
    private Disposable d;

    public ProgDiagObserver(Context context, Boolean showDialog) {
        mContext = context;
        mShowDialog = showDialog;
    }

    public ProgDiagObserver(Context context) {
        this(context, true);
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        if (!isConnected(mContext)) {
            Toast.makeText(mContext, "未连接网络", Toast.LENGTH_SHORT).show();
            if (d.isDisposed()) {
                d.dispose();
            }
        } else {
//            if (dialog == null && mShowDialog == true) {
//                dialog = new ProgressDialog(mContext);
//                dialog.setTips("");
//                dialog.show();
//            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (d.isDisposed()) {
            d.dispose();
        }
        hidDialog();
        super.onError(e);
    }

    @Override
    public void onComplete() {
        if (d.isDisposed()) {
            d.dispose();
        }
        hidDialog();
        super.onComplete();
    }

    public void hidDialog() {
//        if (dialog != null && mShowDialog == true) {
//            dialog.hide();
//        }
    }

    /**
     * 是否有网络连接，不管是wifi还是数据流量
     *
     * @param context
     * @return
     */
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        boolean available = info.isAvailable();
        return available;
    }
}
