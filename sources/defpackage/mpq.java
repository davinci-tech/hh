package defpackage;

import com.huawei.pluginhealthzone.cloud.CloudApi;

/* loaded from: classes6.dex */
public class mpq {
    private static volatile CloudApi d;

    private mpq() {
    }

    public static CloudApi d() {
        if (d == null) {
            synchronized (mpq.class) {
                if (d == null) {
                    d = new mpn();
                }
            }
        }
        return d;
    }
}
