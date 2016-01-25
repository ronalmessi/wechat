package com.ronaldong.messi.ui.Base;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ronaldong.messi.R;

/**
 * Created by ronaldong on 2015/12/22.
 */
public abstract  class BaseSplash extends BaseActivity {

    /**
     * 用于显示启动界面的背景图片
     */
    protected ImageView mImageView;

    protected abstract void setRootBackground(ImageView view);


    @Override
    protected void initViews(Bundle savedInstanceState) {

        mImageView = new ImageView(this);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(mImageView);

        setRootBackground(mImageView);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        // 监听动画过程
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                redirctTo();
            }
        });

        mImageView.setAnimation(animation);
    }

    protected abstract void redirctTo();


}
