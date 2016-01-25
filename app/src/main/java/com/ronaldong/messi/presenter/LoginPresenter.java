package com.ronaldong.messi.presenter;


import android.content.Context;
import android.widget.Toast;

import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.api.Api;
import com.ronaldong.messi.data.api.RequestCallBack;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.data.entity.remote.PersonResult;
import com.ronaldong.messi.ui.view.ILoginView;


/**
 * Created by ronaldong on 2015/12/24.
 */
public class LoginPresenter {

    private Context context;
    private ILoginView iLoginView;

    public LoginPresenter(Context context, ILoginView iLoginView) {
        this.context = context;
        this.iLoginView = iLoginView;
    }

    public void gotoAboutActivity() {
        iLoginView.gotoAboutActivity();
    }


    public void cacheUserInfo(PersonResult personResult) {
        ACache cache = ACache.get(context);
        cache.put(Constants.ACCESSTOKEN, personResult.accessToken);
        cache.put(Constants.USER_ID, personResult.id + "");
        cache.put(Constants.LOGIN_ID, personResult.loginId);
        cache.put(Constants.PASSWORD, personResult.password);
        cache.put(Constants.SEX, personResult.sex);
        cache.put(Constants.AVATAR_URL, personResult.avatarURL);
    }

    public void login() {
        String loginId = iLoginView.getLoginId();
        String password = iLoginView.getPassword();
        String deviceType = "ANDROID";
        String deviceToken = android.os.Build.SERIAL;
        iLoginView.showProgress();
        Api.login(loginId, password, deviceToken, deviceType, new RequestCallBack<PersonResult>() {
            @Override
            public void onSuccess(PersonResult personResult) {
                cacheUserInfo(personResult);
                iLoginView.syncData();
                iLoginView.hideProgress();
                iLoginView.gotoHomeActivity();
            }

            @Override
            public void onFailure(String errorMsg) {
                Toast.makeText(context,errorMsg,Toast.LENGTH_LONG).show();
                iLoginView.hideProgress();
            }
        });
    }
}
