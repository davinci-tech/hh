package defpackage;

import com.huawei.health.suggestion.data.DietDataApi;

/* loaded from: classes4.dex */
public class fje {
    private static final Object b = new Object();
    private static volatile DietDataApi d;

    public static DietDataApi c() {
        if (d == null) {
            synchronized (b) {
                if (d == null) {
                    d = new fjf();
                }
            }
        }
        return d;
    }
}
