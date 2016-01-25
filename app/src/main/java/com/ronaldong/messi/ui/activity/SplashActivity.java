package com.ronaldong.messi.ui.activity;


import android.content.Intent;
import android.widget.ImageView;

import com.ronaldong.messi.R;
import com.ronaldong.messi.presenter.SplashPresenter;
import com.ronaldong.messi.ui.Base.BaseSplash;
import com.ronaldong.messi.ui.view.ISplashView;

/**
 * Created by ronaldong on 2015/12/22.
 */
public class SplashActivity extends BaseSplash implements ISplashView {

    /*
    * 第三方登录参数
    */
    private String mSign;


    /*
    * 应用是否启动过
    */
    private String hasLaunched;


    private SplashPresenter splashPresenter;


    @Override
    protected void setRootBackground(ImageView view) {
        view.setBackgroundResource(R.mipmap.splash_bg);
    }

    @Override
     protected void redirctTo() {
        splashPresenter.redirectTo();
    }

    @Override
    protected void initVariables() {
        splashPresenter = new SplashPresenter(getApplicationContext(), this);
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void gotoGuideActivity() {
        Intent intent = new Intent();
        intent.setClass(this, GuideActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void gotoLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void gotoHomeActivity() {
        Intent intent = new Intent();
        intent.setClass(this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }
}
