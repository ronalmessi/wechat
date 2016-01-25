package com.ronaldong.messi.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.orhanobut.logger.Logger;
import com.ronaldong.messi.R;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.ui.Base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ronaldong on 2016/1/8.
 */
public class AppsActivity extends BaseActivity {

    private WebView wvCourse;
    private HashMap<String, String> headers;

    List<String> titles = new ArrayList<String>();


    @Override
    protected void initVariables() {
        final String accessToken = ACache.get(this).getAsString(Constants.ACCESSTOKEN);
        headers = new HashMap<String, String>() {
            {
                put(Constants.ACCESSTOKEN, accessToken);
            }
        };
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_application);
        setTitle("我的应用");
        wvCourse = (WebView)findViewById(R.id.webview_courseFrag);
        WebSettings webSettings = wvCourse.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setSupportZoom(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        webSettings.setDatabaseEnabled(true);

        wvCourse.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url, headers);
                return true;
            }
        });

        wvCourse.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Logger.d(title);
                titles.add(title);
                setTitle(title);
            }
        });

        wvCourse.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wvCourse.canGoBack()) {
                        wvCourse.goBack();
                        if(titles.size()>2){
                            titles.remove(titles.size()-1);
                            String title=titles.get(titles.size()-1);
                            setTitle(title);
                        }else{
                            setTitle("我的应用");
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void loadData() {
        wvCourse.loadUrl(Constants.APP_MARKET_URL,headers);
    }
}
