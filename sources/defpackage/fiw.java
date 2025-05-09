package defpackage;

import com.huawei.health.suggestion.cloud.DietCloudApi;

/* loaded from: classes4.dex */
public class fiw {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12533a = new Object();
    private static volatile DietCloudApi d;

    public static DietCloudApi d() {
        if (d == null) {
            synchronized (f12533a) {
                if (d == null) {
                    d = new fiv();
                }
            }
        }
        return d;
    }
}
