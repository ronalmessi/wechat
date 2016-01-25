package com.ronaldong.messi.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.ui.activity.LoginActivity;

import java.util.List;

/**
 * 引导页面适配器
 * Created by ronaldong on 2015/12/23.
 */
public class ViewPagerAdapter extends PagerAdapter {

    //界面列表
    private List<View> views;
    private Activity activity;

    public ViewPagerAdapter (Activity activity,List<View> views){
        this.activity=activity;
        this.views = views;

    }

    @Override
    public int getCount() {
        return views!=null?views.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(views.get(position), 0);
        if(position+1==getCount()){
            views.get(position).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ACache mCache = ACache.get(activity);
                    mCache.put("hasLaunched","1");
                    toLoginActivity();
                }
            });
        }
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }


    private void toLoginActivity() {
        Intent intent = new Intent(activity,LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

}
