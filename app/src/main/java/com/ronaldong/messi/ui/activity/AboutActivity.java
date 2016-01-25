package com.ronaldong.messi.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.ronaldong.messi.R;
import com.ronaldong.messi.ui.Base.BaseActivity;
import com.ronaldong.messi.util.AppUtil;

/**
 * Created by ronaldong on 2015/12/24.
 */
public class AboutActivity extends BaseActivity {

    private TextView tv_version;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about);
        setTitle(getString(R.string.setting_about));
        tv_version = (TextView)findViewById(R.id.about_tv_version);
    }

    @Override
    protected void loadData() {
        String versionName = String.format(getString(R.string.version), AppUtil.getAppVersion(this));
        tv_version.setText(versionName);
    }

}
