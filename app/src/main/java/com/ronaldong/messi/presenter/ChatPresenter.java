package com.ronaldong.messi.presenter;

import android.content.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronaldong.messi.Conversation;
import com.ronaldong.messi.ConversationDao;
import com.ronaldong.messi.Message;
import com.ronaldong.messi.MessageDao;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.api.Api;
import com.ronaldong.messi.data.api.RequestCallBack;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.data.db.DBManager;
import com.ronaldong.messi.data.entity.remote.MessageResult;
import com.ronaldong.messi.data.entity.remote.MessageVO;
import com.ronaldong.messi.ui.activity.ChatActivity;
import com.ronaldong.messi.ui.view.IChatView;

import java.util.Date;
import java.util.List;


/**
 * Created by ronaldong on 2016/1/8.
 */
public class ChatPresenter {

    private ConversationDao conversationDao;
    private MessageDao messageDao;
    private IChatView iChatView;
    private ACache cache;

    public ChatPresenter(Context context, ChatActivity iChatView) {
        this.conversationDao = DBManager.getInstance().getDaoSession(context).getConversationDao();
        this.messageDao = DBManager.getInstance().getDaoSession(context).getMessageDao();
        this.iChatView = iChatView;
        this.cache = ACache.get(context);
    }

    public Conversation getConversation(long conversationId) {
        return conversationDao.load(conversationId);
    }

    public void updateMessageState(Message message, int state) {
        message.setState(state);
        messageDao.update(message);
    }

    public void loadMessages(long conversationId) {
        Conversation conversation = getConversation(conversationId);
        conversation.resetMessages();
        List<Message> messages = conversation.getMessages();
        for (Message message : messages) {
            if (message.getState() == Constants.MSG_STATE_RECEIVED_UNREAD) {
                updateMessageState(message, Constants.MSG_STATE_RECEIVED_READ);
            }
        }
        iChatView.showMessages(messages);
    }

    public void loadInComingMessage(Message message) {
        updateMessageState(message, Constants.MSG_STATE_RECEIVED_READ);
        iChatView.showMessage(message);
    }

    public Message createTextMessage(long senderId, long conversationId, String content) {
        Message message = new Message(senderId, conversationId, Constants.MSG_TYPE_TEXT, null, content, Constants.MSG_STATE_SENDING, true, new Date());
        long rowId = messageDao.insert(message);
        if (rowId != -1) {
            return messageDao.loadByRowId(rowId);
        } else {
            return null;
        }
    }

    public void sendTextMessage(long conversationId, String content) {
        String senderId = cache.getAsString(Constants.USER_ID);
        String accessToken = cache.getAsString(Constants.ACCESSTOKEN);
        final Message message = createTextMessage(Long.valueOf(senderId), conversationId, content);
        if (message != null) {
            iChatView.showMessage(message);
            try {
                MessageVO messageVO = new MessageVO(message);
                String msg = new ObjectMapper().writeValueAsString(messageVO);
                Api.postMessage(accessToken, msg, new RequestCallBack<MessageResult.MResult1>() {
                    @Override
                    public void onSuccess(MessageResult.MResult1 mResult1) {
                        message.setMessageId(mResult1.id);
                        message.setSendTime(new Date(mResult1.sendTime));
                        updateMessageState(message, Constants.MSG_STATE_SENDING_SUCCESS);
                        iChatView.updateMessage(message);

                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        updateMessageState(message, Constants.MSG_STATE_SENDING_FAIL);
                    }
                });

            } catch (JsonProcessingException e) {
                e.printStackTrace();

            }

        }
    }


}
