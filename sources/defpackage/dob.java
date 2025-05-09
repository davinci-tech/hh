package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.up.utils.ErrorCode;

/* loaded from: classes3.dex */
public class dob {
    public static void b(String str) {
        Intent intent = new Intent();
        intent.setPackage("com.huawei.health");
        intent.setClassName("com.huawei.health", "com.huawei.health.MainActivity");
        Notification build = jdh.c().xf_().setAutoCancel(false).setPriority(0).setSmallIcon(R$drawable.healthlogo_ic_notification).setShowWhen(true).setOngoing(true).setWhen(System.currentTimeMillis()).setContentIntent(PendingIntent.getActivity(BaseApplication.getContext(), 0, intent, 201326592)).setContentText(BaseApplication.getContext().getResources().getString(R$string.IDS_daily_music_notification) + " · " + str).build();
        jdh.c().xh_(ErrorCode.HWID_IS_STOPED, build);
        StringBuilder sb = new StringBuilder("sendNotification complete, notification: ");
        sb.append(build.toString());
        LogUtil.a("NotificationUtil", sb.toString());
    }

    public static void b() {
        jdh.c().a(ErrorCode.HWID_IS_STOPED);
        LogUtil.a("NotificationUtil", "cancelNotification complete");
    }
}
