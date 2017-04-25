package com.cyxoder.httplib.core;

import android.os.Handler;

import com.innfotech.httplib.HttpConfiguration;
import com.innfotech.httplib.exception.ForceTimeoutException;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jade on 2017/4/24.
 */

public class RequestManager {
    private static final RequestManager instance = new RequestManager();
    private HttpConfiguration mConfig;
    private long forceTimeOut;
    private Handler mHandler = new Handler() {

    };
    private OkHttpClient mClient;

    public static RequestManager getInstance() {
        return instance;
    }

    private RequestManager() {
    }

    public void init(HttpConfiguration config) {
        this.mConfig = config;
        mClient = new OkHttpClient.Builder()
                .addInterceptor(config.getInterceptor())
                .connectTimeout(config.getConnectTimeOut(), TimeUnit.MILLISECONDS)
                .readTimeout(config.getReadTimeOut(), TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false)
                .build();
    }

    private Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .baseUrl(baseUrl)
                .build();
    }

    public Retrofit defaultRetrofit() {
        return getRetrofit(mConfig.getBaseUrl());
    }

    public <T> T createService(Class<T> clazz) {
        return defaultRetrofit().create(clazz);
    }

    public void forceStop(Consumer<Throwable> onError, Disposable subscribe, long timeout) {
        mHandler.postDelayed(() -> {
            if (!subscribe.isDisposed()) {
                try {
                    onError.accept(new ForceTimeoutException(mConfig.getForceTimeOut(), "force timeout"));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    subscribe.dispose();
                }
            }
        }, timeout);
    }
}
