package com.jun.fakebili.net.service;

import com.jun.fakebili.bean.IndexData;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface NetService {

    @Headers({"Domain-Name: recommend"})
    @GET("/x/feed/index?appkey=1d8b6e7d45233436&build=515000&mobi_app=android&network=wifi&open_event=cold&platform=android&style=2&ts=1508556998&sign=8215c7d01711e2f11e75830d856d32f5")
    Observable<IndexData> getRecommendIndexData(@Query("idx") int idx, @Query("pull") String pull, @Query("login_event") int login_event);//login_event为1时加载banner

}
