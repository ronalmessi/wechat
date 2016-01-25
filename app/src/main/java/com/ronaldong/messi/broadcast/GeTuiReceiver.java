package com.ronaldong.messi.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.igexin.sdk.PushConsts;
import com.orhanobut.logger.Logger;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.cache.ACache;

/**
 * Created by ronaldong on 2016/1/11.
 */
public class GeTuiReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        for (String key : bundle.keySet()) {
            Logger.d(key + ":" + bundle.get(key));
        }
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_CLIENTID:
                String clientId = bundle.getString(Constants.GETUI_CLIENTID);
                ACache.get(context).put(Constants.GETUI_CLIENTID, clientId);
                break;
            case PushConsts.GET_MSG_DATA:
                break;
        }

    }
}
