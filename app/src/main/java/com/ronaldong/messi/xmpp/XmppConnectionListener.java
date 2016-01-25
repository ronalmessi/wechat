package com.ronaldong.messi.xmpp;

import com.orhanobut.logger.Logger;

import org.jivesoftware.smack.ConnectionListener;

/**
 * XMPP连接监听器
 * Created by ronaldong on 2016/1/5.
 */
public class XmppConnectionListener implements ConnectionListener {
    @Override
    public void reconnectionSuccessful() {

        Logger.d("reconnectionSuccessful");
        //当网络断线了，重新连接上服务器触发的事件
//				connect(myContext);
    }

    @Override
    public void reconnectionFailed(Exception arg0) {
        //重新连接失败
        Logger.d("reconnectionFailed");
    }

    @Override
    public void reconnectingIn(int arg0) {
        //重新连接的动作正在进行的动作，里面的参数arg0是一个倒计时的数字，
//				如果连接失败的次数增多，数字会越来越大，开始的时候是14

        Logger.d("reconnectingIn");
    }
    @Override
    public void connectionClosedOnError(Exception arg0) {
        //这里就是网络不正常断线激发的事件
        Logger.d("connectionClosedOnError");
    }

    @Override
    public void connectionClosed() {
        //这里是正常关闭连接的事件
        Logger.d("connectionClosed");
    }
}
