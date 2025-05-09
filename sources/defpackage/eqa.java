package defpackage;

import com.huawei.health.plan.model.cloud.CloudApi;

/* loaded from: classes3.dex */
public class eqa {
    private static volatile CloudApi c;
    private static final Object d = new Object();

    public static CloudApi a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new epy();
                }
            }
        }
        return c;
    }
}
