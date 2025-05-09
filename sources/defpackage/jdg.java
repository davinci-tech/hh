package defpackage;

import android.app.NotificationChannel;
import com.huawei.haf.common.base.BaseNotification;
import health.compact.a.NotificationUtils;

/* loaded from: classes5.dex */
final class jdg extends BaseNotification {
    private static volatile jdg b;

    private jdg() {
    }

    static jdg a() {
        if (b == null) {
            synchronized (jdg.class) {
                if (b == null) {
                    jdg jdgVar = new jdg();
                    jdgVar.a("com.huawei.health", e("IDS_device_transfer_data_notification_title", "IDS_app_name_health"), 4);
                    b = jdgVar;
                }
            }
        }
        return b;
    }

    @Override // com.huawei.haf.common.base.BaseNotification
    public void bFy_(NotificationChannel notificationChannel) {
        notificationChannel.setSound(null, null);
        NotificationUtils.bFw_(notificationChannel, 4);
    }
}
