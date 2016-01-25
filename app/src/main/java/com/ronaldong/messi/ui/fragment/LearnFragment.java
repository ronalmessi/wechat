package com.ronaldong.messi.ui.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ronaldong.messi.R;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.ui.Base.BaseFragment;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 首页H5页面
 * Created by ronaldong on 2015/12/31.
 */
public class LearnFragment extends BaseFragment {

    private WebView wvCourse;

    private HashMap<String, String> headers;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    @Override
    protected void initVariables() {
        final String accessToken = ACache.get(getActivity()).getAsString("accessToken");
        headers = new HashMap<String, String>() {
            {
                put("accesstoken", accessToken);
            }
        };
    }

    @Override
    protected void initViews(View v) {
        wvCourse = (WebView) v.findViewById(R.id.webview_courseFrag);
        wvCourse.setVerticalScrollBarEnabled(false);
        WebSettings webSettings = wvCourse.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setSupportZoom(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        wvCourse.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.toString().equals(Constants.NETSUBJECT_URL)) {
                    goToNetSubject();
                } else {
                    view.loadUrl(url, headers);
                }
                return true;
            }
        });

        wvCourse.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wvCourse.canGoBack()) {  //表示按返回键时的操作
                        wvCourse.goBack();
                        return true;
                    }
                }
                return false;
            }
        });

    }

    @Override
    protected void loadData() {
        wvCourse.loadUrl(Constants.COURSE_URL);
    }

    private void goToNetSubject() {
        String loginId = ACache.get(getActivity()).getAsString("loginId");
        String passWord =ACache.get(getActivity()).getAsString("passWord");
        Uri marketUri = Uri.parse(String.format(Constants.FANYA_APK_LOGIN_URL,loginId,passWord));
        PackageManager pm = getActivity().getPackageManager();
        Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(marketUri);
        if (marketIntent.resolveActivity(pm) != null) {
            startActivity(marketIntent);
        } else {
            new SweetAlertDialog(getActivity())
                    .setTitleText("Here's a message!")
                    .setContentText("It's pretty, isn't it?")
                    .show();
        }
    }
}
