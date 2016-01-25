package com.ronaldong.messi.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.ui.view.ISplashView;

/**
 * Created by ronaldong on 2015/12/23.
 */
public class SplashPresenter {

    private ISplashView splashView;
    private ACache cache;

    public SplashPresenter(Context context, ISplashView view) {
        this.splashView = view;
        this.cache = ACache.get(context);
    }

    public void redirectTo() {
        String hasLaunched = cache.getAsString(Constants.HAS_LAUNCHED);
        String accessToken = cache.getAsString(Constants.ACCESSTOKEN);
        if (TextUtils.isEmpty(hasLaunched)) {
            splashView.gotoGuideActivity();
        } else if (TextUtils.isEmpty(accessToken)) {
            splashView.gotoLoginActivity();
        } else {
            splashView.gotoHomeActivity();
        }
    }
}
