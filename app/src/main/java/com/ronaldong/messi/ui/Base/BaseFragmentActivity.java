package com.ronaldong.messi.ui.Base;

import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by ronaldong on 2016/1/7.
 */
public abstract class BaseFragmentActivity extends BaseActivity {
    /**
     * 你应该在这里调用changeFragment(R.id.content, addStack, targetFragment);
     *
     * @param targetFragment
     *            要改变的Activity
     */
    protected abstract void changeFragment(Fragment targetFragment);


    /** 改变界面的fragment */
    protected void changeFragment(int resView, Fragment targetFragment) {
        if (!targetFragment.isAdded()) {
            FragmentTransaction transaction = getFragmentManager()
                    .beginTransaction();
            transaction.replace(resView, targetFragment, targetFragment
                    .getClass().getName());
            transaction.commitAllowingStateLoss();
        }
    }
}
