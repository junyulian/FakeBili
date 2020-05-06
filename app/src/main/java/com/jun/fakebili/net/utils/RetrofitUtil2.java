package com.jun.fakebili.net.utils;



import androidx.annotation.NonNull;

import com.jun.fakebili.net.service.NetConfig;
import com.jun.fakebili.net.service.NetService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Retrofit封装
 */
public class RetrofitUtil2 {

    private static RetrofitUtil2 util;
    private Retrofit.Builder builder;

    private RetrofitUtil2() {
        OkHttpClient okHttpClient = initOkHttp();
        builder = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(NetConfig.RECOMMEND_BASE_URL);
    }

    public static RetrofitUtil2 get(){
        if (util == null){
            synchronized (RetrofitUtil2.class){
                if (util == null){
                    util = new RetrofitUtil2();
                }
            }
        }
        return util;
    }

    /**
     * 添加 String 解析器
     * @return
     */
    public RetrofitUtil2 addStringParser(){
        builder.addConverterFactory(ScalarsConverterFactory.create());
        return this;
    }
    /**
     * 添加Gson解析器
     * json 转java对象
     * @return
     */
    public RetrofitUtil2 addGsonParser(){
        builder.addConverterFactory(GsonConverterFactory.create());
        return this;
    }

    public NetService build(){
       return builder.build().create(NetService.class);
    }

    /**
     * 初始化okhttp
     * json数据转java对象
     */
    @NonNull
    private OkHttpClient initOkHttp() {
        return new OkHttpClient().newBuilder()
                .readTimeout(NetConfig.DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(NetConfig.DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(NetConfig.DEFAULT_TIME, TimeUnit.SECONDS)//设置写入超时时间
                //.addInterceptor(new LogInterceptor())//添加打印拦截器
                //.addInterceptor(new ReceivedCookiesInterceptor())//添加cookie拦截器获取登录cookie
                //.addInterceptor(new AddCookiesInterceptor())//从缓存取cookie添加到消息头
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();
    }
}