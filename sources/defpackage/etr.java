package defpackage;

import com.huawei.health.plan.model.data.DataApi;

/* loaded from: classes3.dex */
public class etr {
    private static volatile DataApi d;

    private etr() {
    }

    public static DataApi b() {
        if (d == null) {
            synchronized (etr.class) {
                if (d == null) {
                    d = new eth();
                }
            }
        }
        return d;
    }
}
