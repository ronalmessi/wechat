package com.ronaldong.messi.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ListView;

import com.orhanobut.logger.Logger;
import com.ronaldong.messi.Message;
import com.ronaldong.messi.R;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.broadcast.MessageReceiver;
import com.ronaldong.messi.presenter.ChatPresenter;
import com.ronaldong.messi.ui.Base.BaseActivity;
import com.ronaldong.messi.ui.adapter.MessageAdapter;
import com.ronaldong.messi.ui.view.IChatView;
import com.ronaldong.messi.ui.widget.MessageInputToolBox;
import com.ronaldong.messi.ui.widget.OnOperationListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronaldong on 2016/1/8.
 */
public class ChatActivity extends BaseActivity implements IChatView,MessageReceiver.OnMessageReceivedListener {

    private ListView lv_messages;
    private MessageInputToolBox messageInputToolBox;
    private ChatPresenter chatPresenter;
    private MessageAdapter messageAdapter;
    private LocalBroadcastManager lbm;
    private MessageReceiver messageReceiver;
    private long conversationId;
    List<Message> messages = new ArrayList<Message>();

    @Override
    protected void initVariables() {
        chatPresenter = new ChatPresenter(getApplicationContext(), this);
        conversationId = getIntent().getExtras().getLong(Constants.CHAT_EXTRAKEY_CONVERSATION_ID);
        messageAdapter = new MessageAdapter(this, messages);

        messageReceiver=new MessageReceiver();
        messageReceiver.setOnActionResponse(this);


        lbm= LocalBroadcastManager.getInstance(this);
        IntentFilter filter_dynamic = new IntentFilter();
        filter_dynamic.addAction(Constants.ACTION_MESSAGE_RECEIVED);
        lbm.registerReceiver(messageReceiver, filter_dynamic);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_chat);

        setTitle(chatPresenter.getConversation(conversationId).getTitle());

        lv_messages = (ListView) findViewById(R.id.chat_lv_messages);
        lv_messages.setAdapter(messageAdapter);

        messageInputToolBox = (MessageInputToolBox) findViewById(R.id.messageInputToolBox);

        initMessageInputToolBox();
    }

    private void initMessageInputToolBox() {
        messageInputToolBox.setOnOperationListener(new OnOperationListener() {
            @Override
            public void send(String content) {
                chatPresenter.sendTextMessage(conversationId, content);
            }

            @Override
            public void selectedFace(String content) {
                Logger.d("bbbb");
            }

            @Override
            public void selectedFuncation(int index) {

            }
        });

    }

    @Override
    protected void loadData() {
        chatPresenter.loadMessages(conversationId);
    }


    @Override
    public void showMessages(List<Message> messages) {
        messageAdapter.refresh(messages);
        lv_messages.setSelection(messageAdapter.getCount() - 1);
    }

    @Override
    public void showMessage(Message message) {
        messageAdapter.add(message);
        lv_messages.setSelection(messageAdapter.getCount() - 1);
    }

    @Override
    public void updateMessage(Message message) {
        if (lv_messages != null) {
            int start = lv_messages.getFirstVisiblePosition();
            for (int i = start, j = lv_messages.getLastVisiblePosition(); i <= j; i++)
                if (message.getId() == ((Message) lv_messages.getItemAtPosition(i)).getId()) {
                    View view = lv_messages.getChildAt(i - start);
                    messageAdapter.getView(i, view, lv_messages);
                    break;
                }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        lbm.unregisterReceiver(messageReceiver);
    }


    @Override
    public void onReceive(Message message) {
        chatPresenter.loadInComingMessage(message);
    }
}
