package com.ronaldong.messi.ui.view;

import android.support.design.widget.TabLayout;

/**
 * Created by ronaldong on 2015/12/30.
 */
public interface IHomeView {
    void setTabViewByState(TabLayout.Tab tab,int state);
    void updateUnreadMessageCount(long count);
}
