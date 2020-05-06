package com.jun.fakebili.net.utils;


import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * 服务器返回异常信息处理类
 */
public class RxExceptionUtil {
    public static String exceptionHandler(Throwable e){
        String errorMsg ="未知错误";
        if (e instanceof UnknownHostException) {
            errorMsg = "无网络";
        } else if (e instanceof SocketTimeoutException) {
            errorMsg =  "超时";
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            errorMsg = convertStatusCode(httpException);
        } else if (e instanceof ParseException || e instanceof JSONException
                || e instanceof JSONException) {
            errorMsg =  "数据解析异常";
        }
        return errorMsg;
    }

    private static String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() >= 500 && httpException.code() < 600) {
            msg =  "服务处理异常";
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            msg =  "服务异常";
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            msg =  "重定向异常";
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}
