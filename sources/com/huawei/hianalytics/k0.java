package com.huawei.hianalytics;

/* loaded from: classes4.dex */
public class k0 {

    /* renamed from: a, reason: collision with root package name */
    public static volatile i0 f3881a;

    public static i0 a() {
        if (f3881a == null) {
            synchronized (k0.class) {
                if (f3881a == null) {
                    f3881a = j.m572e() ? new f0() : new g0();
                }
            }
        }
        return f3881a;
    }
}
