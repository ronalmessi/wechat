package com.ronaldong.messi.ui.view;

import com.ronaldong.messi.Message;

import java.util.List;

/**
 * Created by ronaldong on 2016/1/8.
 */
public interface IChatView {
    void showMessages(List<Message> messages);

    void showMessage(Message message);

    void updateMessage(Message message);

}
