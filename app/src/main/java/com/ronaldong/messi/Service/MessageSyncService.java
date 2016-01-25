package com.ronaldong.messi.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orhanobut.logger.Logger;
import com.ronaldong.messi.Message;
import com.ronaldong.messi.MessageDao;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.data.db.DBManager;
import com.ronaldong.messi.ui.activity.LoginActivity;
import com.ronaldong.messi.xmpp.XmppConnection;

import org.jivesoftware.smackx.receipts.DeliveryReceipt;

import java.io.IOException;

/**
 * 同步消息到本地数据库并发出消息同步完成广播
 * Created by ronaldong on 2016/1/5.
 */
public class MessageSyncService extends IntentService {

    private MessageDao messageDao;
    private ACache aCache;

    @Override
    public void onCreate() {
        super.onCreate();
        messageDao = DBManager.getInstance().getDaoSession(this).getMessageDao();
        aCache = ACache.get(this);
    }

    public MessageSyncService() {
        super("MessageSyncService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        String from = bundle.getString(Constants.XMPP_EXTRAKEY_MESSAGE_FROM);
        String to = bundle.getString(Constants.XMPP_EXTRAKEY_MESSAGE_TO);
        String packedID = bundle.getString(Constants.XMPP_EXTRAKEY_MESSAGE_PACKEDID);
        String msgBody = intent.getExtras().getString(Constants.XMPP_EXTRAKEY_MESSAGE_BODY);
        sendAckMessage(to, from, packedID);
        try {
            Message msg = new ObjectMapper().readValue(msgBody, Message.class);
            if (msg.getType().equals(Constants.MSG_TYPE_TEXT)) {
                msg.setState(Constants.MSG_STATE_RECEIVED_UNREAD);
                msg.setIsSend(false);
                messageDao.insert(msg);
                sendMessageReceivedBroadcast(msg);
            } else if (msg.getType().equals(Constants.MSG_TYPE_Token)) {
                String accessToken = aCache.getAsString(Constants.ACCESSTOKEN);
                Logger.d(accessToken);
                if (accessToken.equals(msg.getContent())) {
                    DBManager.destroyInstance();
                    XmppConnection.getInstance().closeConnection();
                    gotoLoginActivity();
                }
            }
        } catch (IOException e) {
            Logger.d("消息对象json数据解析失败");
            e.printStackTrace();
        }
    }

    private void sendAckMessage(String to, String from, String packedID) {
        org.jivesoftware.smack.packet.Message receiptMessage = new org.jivesoftware.smack.packet.Message();
        receiptMessage.setTo(from);
        receiptMessage.setFrom(to);
        receiptMessage.setPacketID("");
        receiptMessage.addExtension(new DeliveryReceipt(packedID));
        XmppConnection.getInstance().getConnection().sendPacket(receiptMessage);
    }

    private void sendMessageReceivedBroadcast(Message msg) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent(Constants.ACTION_MESSAGE_RECEIVED);
        intent.putExtra(Constants.XMPP_EXTRAKEY_MESSAGE_OBJECT, msg);
        localBroadcastManager.sendBroadcast(intent);
    }

    public void gotoLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
