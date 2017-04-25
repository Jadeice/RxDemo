package com.cyxoder.httplib.core;

/**
 * Created by Jade on 2017/4/25.
 */

public interface ResponseListener<T> {
    /**
     * 开始请求
     */
    void onStart();

    /**
     * 请求成功
     *
     * @param t
     */
    void onSuccess(T t);

    /**
     * 请求失败
     *
     * @param t
     */
    void onFailure(Throwable t);
}
