package com.ronaldong.messi.xmpp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.app.MyApplication;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 * XMPP数据包监听器
 * Created by ronaldong on 2016/1/5.
 */
public class XmppPacketListener implements PacketListener {

    /**
     * @param packet 消息数据包
     *               <p/>
     *               解析xmpp服务器发送过来的消息数据包
     */
    @Override
    public void processPacket(Packet packet) {
        if (packet != null && packet instanceof Message) {
            org.jivesoftware.smack.packet.Message msg = (org.jivesoftware.smack.packet.Message) packet;
            if (!TextUtils.isEmpty(msg.getBody())) {
                Logger.d(msg.getBody());
                archiveMessage(MyApplication.getInstance(), msg);
            }
        }
    }


    /**
     * @param msg 消息数据包
     *            <p/>
     *            同步消息到本地数据库
     */
    private void archiveMessage(Context context, Message msg) {
        Intent archiveMessageServiceIntent = new Intent("android.intent.action.MESSAGE_RECEIVE");
        Bundle bundle = new Bundle();
        bundle.putString(Constants.XMPP_EXTRAKEY_MESSAGE_BODY, msg.getBody());
        bundle.putString(Constants.XMPP_EXTRAKEY_MESSAGE_FROM, msg.getFrom());
        bundle.putString(Constants.XMPP_EXTRAKEY_MESSAGE_TO, msg.getTo());
        bundle.putString(Constants.XMPP_EXTRAKEY_MESSAGE_PACKEDID, msg.getPacketID());
        archiveMessageServiceIntent.setPackage(context.getPackageName());
        archiveMessageServiceIntent.putExtras(bundle);
        context.startService(archiveMessageServiceIntent);
    }
}
