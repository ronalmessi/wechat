package com.ronaldong.messi.ui.Base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ronaldong on 2015/12/31.
 */
public abstract class BaseFragment extends Fragment {


    /**
     * 渲染视图
     */
    protected abstract View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle);

    /**
     * 初始化变量，包括Intent带的数据和Activity内的变量
     */
    protected abstract void initVariables();

    /**
     * 加载layout布局文件，初始化控件，为控件绑定事件方法
     */
    protected abstract void initViews(View v);


    /**
     * 从数据源加载数据
     */
    protected abstract void loadData();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflaterView(inflater, container, savedInstanceState);
        initVariables();
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}
