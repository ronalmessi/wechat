package com.ronaldong.messi.presenter;

import android.app.Activity;

import com.ronaldong.messi.Conversation;
import com.ronaldong.messi.ConversationDao;
import com.ronaldong.messi.data.db.DBManager;
import com.ronaldong.messi.ui.view.IConversationView;

import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

/**
 * Created by ronaldong on 2016/1/8.
 */
public class ConversationPresenter {

    private ConversationDao conversationDao;
    private IConversationView iConversationView;

    public ConversationPresenter(Activity activity, IConversationView iConversationView) {
        this.conversationDao = DBManager.getInstance().getDaoSession(activity).getConversationDao();
        this.iConversationView = iConversationView;
    }

    public void loadConversations() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        Query query = conversationDao.queryBuilder().where(
                new WhereCondition.StringCondition("_ID IN " +
                        "(SELECT CONVERSATION_ID FROM MESSAGES)")).build();
        List<Conversation> conversationList = query.listLazy();
        iConversationView.showConversations(conversationList);
    }
}
