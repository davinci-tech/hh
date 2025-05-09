package com.huawei.health.manager.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jdh;

/* loaded from: classes.dex */
public class HuaweiServerTokenNotification {
    private HuaweiServerTokenNotification() {
    }

    public static void b(Context context) {
        if (context == null) {
            LogUtil.b("Step_HuaweiServerTokenNotification", "pushServerTokenInvalidNotification context is null");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.MainActivity");
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 1140850688);
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification).setContentTitle(context.getString(R.string.IDS_plugindameon_server_token_invalid_relogin_title)).setContentText(context.getString(R.string.IDS_plugindameon_server_token_invalid_relogin_content)).setContentIntent(activity).setStyle(new Notification.BigTextStyle().bigText(context.getString(R.string.IDS_plugindameon_server_token_invalid_relogin_content)));
        xf_.setAutoCancel(true);
        jdh.c().xh_(121, xf_.build());
        LogUtil.c("Step_HuaweiServerTokenNotification", "showNotification");
    }
}
