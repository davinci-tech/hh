package com.huawei.hms.kit.awareness.barrier.internal.type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes9.dex */
public final class c {
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    @interface a {
    }

    public static boolean a(int i) {
        return i == 2 || i == 3 || i == 4;
    }

    public static String b(int i) {
        return i != 2 ? i != 3 ? i != 4 ? "unknown" : "keeping" : "ending" : "beginning";
    }

    private c() {
    }
}
