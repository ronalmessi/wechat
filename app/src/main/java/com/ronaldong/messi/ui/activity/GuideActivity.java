package com.ronaldong.messi.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.ronaldong.messi.R;
import com.ronaldong.messi.ui.Base.BaseActivity;
import com.ronaldong.messi.ui.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronaldong on 2015/12/22.
 */
public class GuideActivity extends BaseActivity {

    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;

    //引导图片
    private static final int[] guidePics= {R.mipmap.nav1,R.mipmap.nav2,R.mipmap.nav3,R.mipmap.nav4,R.mipmap.nav5};
    //引导图控件列表
    private List<View> guideViews;

    @Override
    protected void initVariables() {
        guideViews=new ArrayList<>();
        //初始化引导图片列表
        for(int i=0; i<guidePics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(guidePics[i]);
            guideViews.add(iv);
        }

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);

        vpAdapter = new ViewPagerAdapter(this,guideViews);
        vp = (ViewPager) findViewById(R.id.viewpager_guide);
        vp.setAdapter(vpAdapter);

    }

    @Override
    protected void loadData() {

    }
}
