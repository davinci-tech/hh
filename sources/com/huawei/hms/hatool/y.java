package com.huawei.hms.hatool;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class y {
    private static y b;

    /* renamed from: a, reason: collision with root package name */
    private volatile Map<String, p0> f4623a = new HashMap();

    public p0 a(String str, long j) {
        p0 a2 = a(str);
        a2.a(j);
        return a2;
    }

    private static void b() {
        synchronized (y.class) {
            if (b == null) {
                b = new y();
            }
        }
    }

    public static y a() {
        if (b == null) {
            b();
        }
        return b;
    }

    private p0 a(String str) {
        if (!this.f4623a.containsKey(str)) {
            this.f4623a.put(str, new p0());
        }
        return this.f4623a.get(str);
    }

    private y() {
    }
}
