package com.ronaldong.messi.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ronaldong.messi.R;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.api.Api;
import com.ronaldong.messi.data.api.RequestCallBack;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.data.db.DBManager;
import com.ronaldong.messi.ui.Base.BaseActivity;
import com.ronaldong.messi.xmpp.XmppConnection;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ronaldong on 2016/1/19.
 */
public class SettingActivity extends BaseActivity {

    private RelativeLayout re_setting_about;
    private RelativeLayout re_setting_help;
    private Button btn_setting_logout;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        setTitle(getString(R.string.profile_setting));
        re_setting_about = (RelativeLayout) findViewById(R.id.re_setting_about);
        re_setting_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        re_setting_help = (RelativeLayout) findViewById(R.id.re_setting_help);
        btn_setting_logout = (Button) findViewById(R.id.btn_setting_logout);
        btn_setting_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SweetAlertDialog pDialog = new SweetAlertDialog(SettingActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("正在退出");
                pDialog.setCancelable(false);
                pDialog.show();
                String accessToken = ACache.get(getApplicationContext()).getAsString(Constants.ACCESSTOKEN);
                Api.logout(accessToken, new RequestCallBack() {
                    @Override
                    public void onSuccess(Object o) {
                        pDialog.dismissWithAnimation();
                        DBManager.destroyInstance();
                        XmppConnection.getInstance().closeConnection();
                        Intent intent = new Intent();
                        intent.setClass(SettingActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                    }
                });

            }
        });

    }

    @Override
    protected void loadData() {

    }
}
