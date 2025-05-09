package com.huawei.phoneservice.feedback.media.impl.utils;

/* loaded from: classes5.dex */
public final class b {
    private static String e;

    public static boolean d() {
        synchronized (b.class) {
            if (e == null) {
                e = c.c("ro.build.characteristics");
            }
        }
        return "tablet".equals(e) || "car".equals(e);
    }

    private b() {
    }
}
