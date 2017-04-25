package com.cyxoder.httplib.service;

import com.innfotech.httplib.base.BaseEntity;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Jade on 2017/4/25.
 */

public interface StringService {
    @GET("hotel/api.php")
    <E> Flowable<BaseEntity<E>> get(@QueryMap Map<String, String> params);
}
