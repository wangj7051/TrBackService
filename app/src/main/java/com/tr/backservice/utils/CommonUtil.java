package com.tr.backservice.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


/**
 * 常用共同方法类，不涉及逻辑
 *
 * @author Jun.Wang
 */
public class CommonUtil {
    /**
     * Open Other APK
     *
     * @param pkg : Like "com.xxx.package"
     * @param cls : Like "com.xxx.package.ApkActivity"
     */
    public static void openApk(Context cxt, String pkg, String cls, Intent data) {
        Intent i = new Intent();
        i.putExtras(data);
        i.setComponent(new ComponentName(pkg, cls));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.setAction("android.intent.action.VIEW");
        cxt.startActivity(i);
    }
}
