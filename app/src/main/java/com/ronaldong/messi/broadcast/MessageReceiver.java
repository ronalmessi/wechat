package com.ronaldong.messi.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ronaldong.messi.Message;
import com.ronaldong.messi.app.Constants;

/**
 * Created by ronaldong on 2016/1/21.
 */
public class MessageReceiver extends BroadcastReceiver {
    private OnMessageReceivedListener onMessageReceivedListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Message message = (Message) intent.getExtras().getSerializable(Constants.XMPP_EXTRAKEY_MESSAGE_OBJECT);
        onMessageReceivedListener.onReceive(message);
    }

    public interface OnMessageReceivedListener {
        void onReceive(Message message);
    }

    public void setOnActionResponse(OnMessageReceivedListener onMessageReceivedListener) {
        this.onMessageReceivedListener = onMessageReceivedListener;
    }
}
