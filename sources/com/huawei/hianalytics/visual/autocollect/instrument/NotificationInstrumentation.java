package com.huawei.hianalytics.visual.autocollect.instrument;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.autocollect.EventType;
import com.huawei.hianalytics.visual.b;
import com.huawei.hianalytics.visual.i0;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes8.dex */
public class NotificationInstrumentation {
    public static boolean a() {
        if (b.a().b()) {
            return true;
        }
        return b.a().a(EventType.APP_NOTICE);
    }

    public static void b(Intent intent) {
        if (a()) {
            return;
        }
        i0.a().a(intent);
    }

    public static void handleIntentByBroadcastReceiver(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
        b(intent);
    }

    public static void handleIntentByGetActivity(Context context, int i, Intent intent, int i2) {
        a(intent);
    }

    public static void handleIntentByGetActivityBundle(Context context, int i, Intent intent, int i2, Bundle bundle) {
        a(intent);
    }

    public static void handleIntentByGetBroadcast(Context context, int i, Intent intent, int i2) {
        a(intent);
    }

    public static void handleIntentByGetForegroundService(Context context, int i, Intent intent, int i2) {
        a(intent);
    }

    public static void handleIntentByGetService(Context context, int i, Intent intent, int i2) {
        a(intent);
    }

    public static void handleIntentByServiceStart(Service service, Intent intent, int i) {
        b(intent);
    }

    public static void handleIntentByServiceStartCommand(Service service, Intent intent, int i, int i2) {
        b(intent);
    }

    public static void handlePendingIntentByGetActivity(PendingIntent pendingIntent, Context context, int i, Intent intent, int i2) {
        a(intent, pendingIntent);
    }

    public static void handlePendingIntentByGetActivityBundle(PendingIntent pendingIntent, Context context, int i, Intent intent, int i2, Bundle bundle) {
        a(intent, pendingIntent);
    }

    public static void handlePendingIntentByGetBroadcast(PendingIntent pendingIntent, Context context, int i, Intent intent, int i2) {
        a(intent, pendingIntent);
    }

    public static void handlePendingIntentByGetForegroundService(PendingIntent pendingIntent, Context context, int i, Intent intent, int i2) {
        a(intent, pendingIntent);
    }

    public static void handlePendingIntentByGetService(PendingIntent pendingIntent, Context context, int i, Intent intent, int i2) {
        a(intent, pendingIntent);
    }

    public static void onNewIntentByNotification(Object obj, Intent intent) {
        if (obj instanceof Activity) {
            b(intent);
        }
    }

    public static void onNotifyEvent(NotificationManager notificationManager, String str, int i, Notification notification) {
        if (a()) {
            return;
        }
        i0.a().a(i, notification);
    }

    public static void a(Intent intent) {
        if (a()) {
            return;
        }
        i0 a2 = i0.a();
        try {
            if (a2.f) {
                return;
            }
            try {
                if (intent.hasExtra("HA_NOTICE_ID")) {
                    return;
                }
            } catch (Exception unused) {
                HiLog.d("PushNotificationManager", "fail to get notice id");
            }
            intent.putExtra("HA_NOTICE_ID", a2.b + Constants.LINK + a2.c.getAndIncrement());
        } catch (Exception unused2) {
            HiLog.e("PushNotificationManager", "fail to handle Intent");
        }
    }

    public static void onNotifyEvent(NotificationManager notificationManager, int i, Notification notification) {
        if (a()) {
            return;
        }
        onNotifyEvent(notificationManager, null, i, notification);
    }

    public static void a(Intent intent, PendingIntent pendingIntent) {
        String str;
        if (a()) {
            return;
        }
        i0 a2 = i0.a();
        if (a2.f || intent == null) {
            return;
        }
        try {
            str = intent.getStringExtra("HA_NOTICE_ID");
        } catch (Throwable unused) {
            HiLog.w("PushNotificationManager", "handlePendingIntent error");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a2.f3927a.put(pendingIntent, str);
    }
}
