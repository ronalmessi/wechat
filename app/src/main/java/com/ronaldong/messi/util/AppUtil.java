package com.ronaldong.messi.util;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by ronaldong on 2015/12/24.
 */
public class AppUtil {

    /**
     * 获取当前应用程序的版本号
     */
    public static String getAppVersion(Context context) {
        String version = "0";
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
           e.printStackTrace();
        }
        return version;
    }

}
