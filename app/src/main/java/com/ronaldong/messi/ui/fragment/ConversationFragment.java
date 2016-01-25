package com.ronaldong.messi.ui.fragment;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.ronaldong.messi.Conversation;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.presenter.ConversationPresenter;
import com.ronaldong.messi.ui.activity.ChatActivity;
import com.ronaldong.messi.ui.adapter.ConversationAdapter;
import com.ronaldong.messi.ui.view.IConversationView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ronaldong on 2015/12/31.
 */
public class ConversationFragment extends ListFragment implements IConversationView {


    private ConversationAdapter adapter;
    private List<Conversation> conversationList;
    private ConversationPresenter conversationPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        conversationPresenter = new ConversationPresenter(getActivity(),this);
        conversationList = new ArrayList<Conversation>();
        adapter = new ConversationAdapter(getActivity(), conversationList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Conversation conversation = (Conversation) adapter.getItem(position);
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra(Constants.CHAT_EXTRAKEY_CONVERSATION_ID, conversation.getId());
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        setEmptyText("正在加载数据");
    }

    @Override
    public void onResume() {
        super.onResume();
        conversationPresenter.loadConversations();
    }


    @Override
    public void showConversations(List<Conversation> conversationList) {
        adapter.replaceAll(conversationList);
    }

    public ConversationPresenter getConversationPresenter() {
        return conversationPresenter;
    }
}
