package com.jun.fakebili.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * 一些框架常用工具
 */
public class ArmsUtils {

    private ArmsUtils(){
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 获得资源
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }

    /**
     * 获得颜色
     */
    public static int getColor(Context context, int rid) {
        return getResources(context).getColor(rid);
    }

    /**
     * 得到字符数组
     */
    public static String[] getStringArray(Context context, int id) {
        return getResources(context).getStringArray(id);
    }

}
