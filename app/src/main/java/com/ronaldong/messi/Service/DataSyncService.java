package com.ronaldong.messi.Service;

import android.app.IntentService;
import android.content.Intent;

import com.ronaldong.messi.Conversation;
import com.ronaldong.messi.DaoSession;
import com.ronaldong.messi.Organize;
import com.ronaldong.messi.Person;
import com.ronaldong.messi.Relation;
import com.ronaldong.messi.WorkGroup;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.api.Api;
import com.ronaldong.messi.data.api.RequestCallBack;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.data.db.DBManager;
import com.ronaldong.messi.data.entity.remote.IncrementalUpdateVO;
import com.ronaldong.messi.data.entity.remote.Update;
import com.ronaldong.messi.util.PingYinUtil;

/**
 * 同步组织架构、工作组、会话、个人信息、人以及它们之间的关联关系到本地数据库
 * Created by ronaldong on 2015/12/28.
 */
public class DataSyncService extends IntentService {

    private String accessToken;
    private DaoSession daoSession;

    public DataSyncService() {
        super("DataSyncService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = DBManager.getInstance().getDaoSession(this);
        accessToken = ACache.get(this).getAsString(Constants.ACCESSTOKEN);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        String params = intent.getExtras().getString("params");
        Api.getIncrementalUpdates(accessToken, params, new RequestCallBack<IncrementalUpdateVO>() {
            @Override
            public void onSuccess(IncrementalUpdateVO incrementalUpdateVO) {
                syncDataToDB(incrementalUpdateVO);
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }

    private void syncDataToDB(final IncrementalUpdateVO incrementalUpdateVO) {
        daoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                if (incrementalUpdateVO.list1 != null) {
                    for (Update update : incrementalUpdateVO.list1) {
                        syncOrganize(update);
                    }
                }
                if (incrementalUpdateVO.list2 != null) {
                    for (Update update : incrementalUpdateVO.list2) {
                        syncWorkGroup(update);
                    }
                }
                if (incrementalUpdateVO.list3 != null) {
                    for (Update update : incrementalUpdateVO.list3) {
                        syncConversation(update);
                    }
                }
                if (incrementalUpdateVO.list4 != null) {
                    for (Update update : incrementalUpdateVO.list4) {
                        syncPerson(update);
                    }
                }
                if (incrementalUpdateVO.list5 != null) {

                    for (Update update : incrementalUpdateVO.list5) {
                        syncRelation(update);
                    }
                }
            }
        });
    }

    public void syncOrganize(Update update) {
        Organize organize = new Organize(update.id, update.name, PingYinUtil.getPingYin4Search(update.name), update.avatar, update.indexOrder, update.organizeType, update.deleted, update.parentId, update.lut);
        daoSession.getOrganizeDao().insertOrReplace(organize);
    }

    public void syncWorkGroup(Update update) {
        WorkGroup workGroup = new WorkGroup(update.id, update.name, PingYinUtil.getPingYin4Search(update.name), update.avatar, update.indexOrder, update.groupType, update.deleted, update.lut, update.isPublic, update.isTmp, update.tagId, update.canEdit);
        daoSession.getWorkGroupDao().insertOrReplace(workGroup);
    }

    public void syncConversation(Update update) {
        Conversation conversation = new Conversation(update.id, update.name, update.avatar, update.title, update.content, update.deleted, update.lut, update.silent, update.isTmp, update.targetId, update.targetType);
        daoSession.getConversationDao().insertOrReplace(conversation);
    }

    public void syncPerson(Update update) {
        Person person = new Person(update.id, update.name, PingYinUtil.getPingYin4Search(update.name), update.avatar, update.room, update.hasInstalled, update.personType, update.mobileNum, update.workNum, update.shortNum, update.jobNum, update.email, update.duty, update.gender, update.deleted, update.lut);
        daoSession.getPersonDao().insertOrReplace(person);
    }

    public void syncRelation(Update update) {
        Relation relation = new Relation(update.id, update.indexOrder, update.deleted, update.groupId, update.userId, update.role, update.jobNum, update.lut);
        daoSession.getRelationDao().insertOrReplace(relation);
    }

}
