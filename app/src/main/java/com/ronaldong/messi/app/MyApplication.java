package com.ronaldong.messi.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.igexin.sdk.PushManager;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.ronaldong.messi.BuildConfig;
import com.ronaldong.messi.data.api.ApiService;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.data.entity.remote.ResultVO;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ronaldong on 2015/12/22.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public MyApplication() {
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
        Logger.init(Constants.LOG_TAG).logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
    }


    @Override
    public void onTrimMemory(int level) {
        Logger.d("界面被隐藏！");

        PushManager.getInstance().turnOnPush(getApplicationContext());
        String clientId = PushManager.getInstance().getClientid(this);
        if (clientId != null) {
            String accessToken = ACache.get(this).getAsString("accessToken");
            Call<ResultVO> observable = ApiService.Creator.newApiService().registPushToken(accessToken, clientId);
            observable.enqueue(new Callback<ResultVO>() {
                @Override
                public void onResponse(Response<ResultVO> response, Retrofit retrofit) {
                    if (response.isSuccess() && response.body() != null) {

                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });


        }

        super.onTrimMemory(level);
    }


}
