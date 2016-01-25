package com.ronaldong.messi.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ronaldong.messi.R;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.presenter.LoginPresenter;
import com.ronaldong.messi.ui.Base.BaseActivity;
import com.ronaldong.messi.ui.view.ILoginView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ronaldong on 2015/12/23.
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    private Button btn_login;
    private Button btn_about;
    private EditText et_loginId;
    private EditText et_password;
    private SweetAlertDialog pDialog;

    private LoginPresenter loginPresenter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);

        et_loginId = (EditText) findViewById(R.id.login_et_loginId);
        et_password = (EditText) findViewById(R.id.login_et_password);

        btn_login = (Button) findViewById(R.id.login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login();
            }
        });

        btn_about = (Button) findViewById(R.id.login_btn_about);
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.gotoAboutActivity();
            }
        });

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.setTitleText("正在登录请稍后");
        pDialog.setCancelable(false);

    }

    @Override
    protected void initVariables() {
        loginPresenter = new LoginPresenter(getApplicationContext(), this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void gotoAboutActivity() {
        Intent intent = new Intent();
        intent.setClass(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoHomeActivity() {
        Intent intent = new Intent();
        intent.setClass(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void syncData() {
        Intent dataSyncServiceIntent = new Intent(Constants.ACTION_SYNCDATA);
        Bundle bundle = new Bundle();
        bundle.putString("params", "1:0,2:0,3:0,4:0,5:0");
        dataSyncServiceIntent.setPackage(getPackageName());
        dataSyncServiceIntent.putExtras(bundle);
        startService(dataSyncServiceIntent);
    }


    @Override
    public String getLoginId() {
        return et_loginId == null ? null : et_loginId.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_loginId == null ? null : et_password.getText().toString();
    }

    @Override
    public void showProgress() {
        pDialog.show();
    }

    @Override
    public void hideProgress() {
        pDialog.hide();
    }

}
