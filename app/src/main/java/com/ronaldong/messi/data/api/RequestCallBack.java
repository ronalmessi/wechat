package com.ronaldong.messi.data.api;


/**
 * Created by ronaldong on 2016/1/4.
 */
public interface RequestCallBack<T> {

    void onSuccess(T t);

    void onFailure(String errorMsg);
}


