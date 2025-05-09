package defpackage;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.service.notification.StatusBarNotification;
import com.huawei.haf.common.base.BaseNotification;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.NotificationUtils;

/* loaded from: classes.dex */
public final class jdh {
    public static BaseNotification b() {
        if (!EnvironmentInfo.j()) {
            return NotificationUtils.e();
        }
        return jdj.d();
    }

    public static BaseNotification c() {
        return NotificationUtils.e();
    }

    public static BaseNotification d() {
        return jdg.a();
    }

    public static PendingIntent bFr_(Context context) {
        return NotificationUtils.bFv_(context);
    }

    public static boolean c(int i) {
        StatusBarNotification[] activeNotifications;
        NotificationManager xB_ = CommonUtil.xB_();
        if (xB_ != null && (activeNotifications = xB_.getActiveNotifications()) != null && activeNotifications.length != 0) {
            for (StatusBarNotification statusBarNotification : activeNotifications) {
                if (statusBarNotification != null && statusBarNotification.getId() == i) {
                    return true;
                }
            }
        }
        return false;
    }
}
