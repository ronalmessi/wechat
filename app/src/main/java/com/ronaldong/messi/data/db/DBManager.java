package com.ronaldong.messi.data.db;

import android.content.Context;

import com.ronaldong.messi.DaoMaster;
import com.ronaldong.messi.DaoSession;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.cache.ACache;

/**
 * Created by ronaldong on 2016/1/14.
 */
public class DBManager {

    private static final String DB_VERSION = "1";
    private static final String DB_NAME_TEMPLATE = "cache_user_%s_ver_%s.db";

    private static DBManager instance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;


    public DBManager() {
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public static void destroyInstance() {
        daoSession=null;
        daoMaster=null;
    }


    /*获取访问本地数据库请求工具*/
    public static DaoSession getDaoSession(Context context) {
        String userId = ACache.get(context).getAsString(Constants.USER_ID);
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context, genDbName(userId));
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }


    private static DaoMaster getDaoMaster(Context context, String tableName) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, tableName, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public static String genDbName(String userId) {
        return userId == null ? null : String.format(DB_NAME_TEMPLATE, userId,
                DB_VERSION);
    }

}
