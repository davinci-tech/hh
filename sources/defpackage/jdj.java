package defpackage;

import android.app.NotificationChannel;
import com.huawei.haf.common.base.BaseNotification;
import health.compact.a.NotificationUtils;

/* loaded from: classes5.dex */
final class jdj extends BaseNotification {
    private static volatile jdj b;

    jdj() {
    }

    static jdj d() {
        if (b == null) {
            synchronized (jdj.class) {
                if (b == null) {
                    jdj jdjVar = new jdj();
                    jdjVar.a("channel_content_info_id", e("IDS_hw_app_name", "IDS_app_name_health"), 2);
                    b = jdjVar;
                }
            }
        }
        return b;
    }

    @Override // com.huawei.haf.common.base.BaseNotification
    public void bFy_(NotificationChannel notificationChannel) {
        super.bFy_(notificationChannel);
        NotificationUtils.bFw_(notificationChannel, 2);
    }
}
