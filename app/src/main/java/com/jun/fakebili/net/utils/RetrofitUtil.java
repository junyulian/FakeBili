package com.jun.fakebili.net.utils;



import androidx.annotation.NonNull;

import com.jun.fakebili.net.service.NetConfig;
import com.jun.fakebili.net.service.NetService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit封装
 */
public class RetrofitUtil {
    private static final String TAG = "RetrofitUtil";
    private static NetService netSrvice;

    /**
     * 单例模式
     */
    public static NetService getNetSrvice() {
        if (netSrvice == null) {
            synchronized (RetrofitUtil.class) {
                if (netSrvice == null) {
                    netSrvice = new RetrofitUtil().getRetrofit();
                }
            }
        }
        return netSrvice;
    }


    private RetrofitUtil() {
    }

    /**
     * 正常从后台获取json格式数据直接转java对象
     *
     * @return
     */
    public NetService getRetrofit() {
        // 初始化NetService
        NetService netService = initRetrofit(initOkHttp()).create(NetService.class);
        return netService;
    }


    /**
     * 初始化Retrofit
     * json转java对象
     */
    @NonNull
    private Retrofit initRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(NetConfig.RECOMMEND_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//json 转java对象
                .build();
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
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                //.addInterceptor(new ReceivedCookiesInterceptor())//添加cookie拦截器获取登录cookie
                //.addInterceptor(new AddCookiesInterceptor())//从缓存取cookie添加到消息头
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .sslSocketFactory(TrustAllCerts.createSSLSocketFactory())
                .hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier())
                .build();
    }

}