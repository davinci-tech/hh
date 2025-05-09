package defpackage;

import com.huawei.haf.common.base.BaseNotification;

/* loaded from: classes5.dex */
final class kjp extends BaseNotification {
    private static volatile kjp c;

    private kjp() {
    }

    static kjp d() {
        if (c == null) {
            synchronized (kjp.class) {
                if (c == null) {
                    kjp kjpVar = new kjp();
                    kjpVar.a("device_connect_id", e("IDS_hw_app_name", "IDS_app_name_health"), 3);
                    c = kjpVar;
                }
            }
        }
        return c;
    }
}
