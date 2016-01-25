package com.ronaldong.messi.ui.view;

/**
 * Created by ronaldong on 2015/12/24.
 */
public interface ILoginView {
    String getLoginId();

    String getPassword();

    void showProgress();

    void hideProgress();

    void gotoAboutActivity();

    void gotoHomeActivity();

    void syncData();
    
}
