package com.ronaldong.messi.presenter;

import android.content.Context;
import android.support.design.widget.TabLayout;

import com.ronaldong.messi.MessageDao;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.data.db.DBManager;
import com.ronaldong.messi.ui.activity.HomeActivity;
import com.ronaldong.messi.ui.view.IHomeView;
import com.ronaldong.messi.xmpp.XmppConnection;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by ronaldong on 2015/12/30.
 */
public class HomePresenter {

    private IHomeView iHomeView;
    private MessageDao messageDao;
    private ACache cache;
    private Context context;


    public HomePresenter(Context context, HomeActivity iHomeView) {
        this.iHomeView = iHomeView;
        this.messageDao=DBManager.getInstance().getDaoSession(context).getMessageDao();
        this.cache= ACache.get(context);
        this.context=context;
    }

    public void SetViewByState(TabLayout.Tab tab,int state){
        iHomeView.setTabViewByState(tab,state);
    }

    public void getUnReadMessageCount(){
        QueryBuilder qb = messageDao.queryBuilder();
        qb.where(MessageDao.Properties.State.eq(Constants.MSG_STATE_RECEIVED_UNREAD), MessageDao.Properties.Type.notEq(Constants.MSG_TYPE_Token));
        long count=qb.count();
        iHomeView.updateUnreadMessageCount(count);
    }


    public void loginXmppServer() {
        String account =cache.getAsString(Constants.USER_ID);
        String password = account + "iclass";
        XmppConnection.getInstance().login(account, password, Constants.XMPP_SERVER_NAME);
    }

}
