package com.jun.fakebili.helper;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.jun.fakebili.R;


/**
 * 状态栏工具类
 * 状态栏两种模式(Android 4.4以上)
 * 1.沉浸式全屏模式
 * 2.状态栏着色模式
 */
public class SystemBarHelper {

    private static float DEFAULT_ALPHA = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 0.2f : 0.3f;


    /**
     * Android4.4以上的状态栏着色(针对于DrawerLayout)
     * 注:
     * 1.如果出现界面展示不正确,删除布局中所有fitsSystemWindows属性,尤其是DrawerLayout的fitsSystemWindows属性
     * 2.可以版本判断在5.0以上不调用该方法,使用系统自带
     *
     * @param activity       Activity对象
     * @param drawerLayout   DrawerLayout对象
     * @param statusBarColor 状态栏颜色
     */
    public static void tintStatusBarForDrawer(AppCompatActivity activity, DrawerLayout drawerLayout, @ColorInt int statusBarColor) {
        tintStatusBarForDrawer(activity, drawerLayout, statusBarColor, DEFAULT_ALPHA);
    }

    /**
     * Android4.4以上的状态栏着色(针对于DrawerLayout)
     * 注:
     * 1.如果出现界面展示不正确,删除布局中所有fitsSystemWindows属性,尤其是DrawerLayout的fitsSystemWindows属性
     * 2.可以版本判断在5.0以上不调用该方法,使用系统自带
     *
     * @param activity       Activity对象
     * @param drawerLayout   DrawerLayout对象
     * @param statusBarColor 状态栏颜色
     * @param alpha          透明栏透明度[0.0-1.0]
     */
    public static void tintStatusBarForDrawer(AppCompatActivity activity, DrawerLayout drawerLayout, @ColorInt int statusBarColor,
                                              @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }

        Window window = activity.getWindow();
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        ViewGroup drawContent = (ViewGroup) drawerLayout.getChildAt(0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            drawerLayout.setStatusBarBackgroundColor(statusBarColor);

            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setStatusBar(decorView, statusBarColor, true, true);
        setTranslucentView(decorView, alpha);

        drawerLayout.setFitsSystemWindows(false);
        drawContent.setFitsSystemWindows(true);
        ViewGroup drawer = (ViewGroup) drawerLayout.getChildAt(1);
        drawer.setFitsSystemWindows(false);
    }

    /** 创建假的状态栏View */
    private static void setStatusBar(ViewGroup container, @ColorInt int statusBarColor, boolean visible, boolean addToFirst) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View statusBarView = container.findViewById(R.id.statusbar_view);
            if (statusBarView == null) {
                statusBarView = new View(container.getContext());
                statusBarView.setId(R.id.statusbar_view);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(container.getContext()));
                if (addToFirst) {
                    container.addView(statusBarView, 0, lp);
                } else {
                    container.addView(statusBarView, lp);
                }
            }

            statusBarView.setBackgroundColor(statusBarColor);
            statusBarView.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    /** 创建假的透明栏 */
    private static void setTranslucentView(ViewGroup container,
                                           @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View translucentView = container.findViewById(R.id.translucent_view);
            if (translucentView == null) {
                translucentView = new View(container.getContext());
                translucentView.setId(R.id.translucent_view);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(container.getContext()));
                container.addView(translucentView, lp);
            }

            translucentView.setBackgroundColor(Color.argb((int) (alpha * 255), 0, 0, 0));
        }
    }

    /** 获取状态栏高度 */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelSize(resId);
        }
        return result;
    }

    /** 增加View的paddingTop,增加的值为状态栏高度 */
    public static void setPadding(Context context, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(context),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }


}
