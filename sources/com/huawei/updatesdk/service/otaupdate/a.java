package com.huawei.updatesdk.service.otaupdate;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

/* loaded from: classes7.dex */
public abstract class a {
    public static boolean a(Context context) {
        if (context == null) {
            return true;
        }
        if (!(context instanceof Activity)) {
            return false;
        }
        Activity activity = (Activity) context;
        if (!activity.isFinishing() && !activity.isDestroyed()) {
            return false;
        }
        com.huawei.updatesdk.a.a.c.a.a.a.b("ActivityUtil", "activity has bean finished, cannot instance:" + activity);
        return true;
    }

    public static void a(Context context, IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
        if (intentFilter == null || broadcastReceiver == null || a(context)) {
            return;
        }
        try {
            context.registerReceiver(broadcastReceiver, intentFilter);
        } catch (IllegalArgumentException e) {
            com.huawei.updatesdk.a.a.c.a.a.a.b("ActivityUtil", "registerReceiver error:" + e.getMessage());
        }
    }

    public static void a(Context context, BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null || context == null) {
            return;
        }
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            com.huawei.updatesdk.a.a.c.a.a.a.b("ActivityUtil", "unregisterReceiver error:" + e.getMessage());
        }
    }
}
