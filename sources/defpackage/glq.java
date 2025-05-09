package defpackage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import com.google.gson.Gson;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.base.BaseNotification;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class glq extends BaseNotification {
    private glq() {
    }

    private static void aNX_(NotificationManager notificationManager) {
        LogUtil.a("TwsNotificationManager", "addTwsNotificationChannel() in");
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.huawei.health");
        if (notificationChannel == null || notificationChannel.getImportance() != 4) {
            new glq().a("com.huawei.health", e("IDS_device_transfer_data_notification_title", "IDS_app_name_health"), 4);
        }
    }

    public static void aNY_(int i, Notification.Builder builder, String str) {
        LogUtil.a("TwsNotificationManager", "createTwsNotification() in");
        Object systemService = BaseApplication.e().getSystemService(RemoteMessageConst.NOTIFICATION);
        if (systemService instanceof NotificationManager) {
            NotificationManager notificationManager = (NotificationManager) systemService;
            aNX_(notificationManager);
            HashMap hashMap = new HashMap();
            hashMap.put("isPlay", true);
            hashMap.put("playContent", str);
            Bundle bundle = new Bundle();
            bundle.putString("com.huawei.audioaccessorymanager.NOTIFICATION_PLAY", new Gson().toJson(hashMap));
            builder.setChannelId("com.huawei.health").setOngoing(false).setTicker("ticketTws").setExtras(bundle);
            notificationManager.notify(i, builder.build());
            return;
        }
        LogUtil.h("TwsNotificationManager", "createTwsNotification() Type matching error");
    }
}
