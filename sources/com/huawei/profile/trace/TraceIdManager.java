package com.huawei.profile.trace;

import java.util.Locale;

/* loaded from: classes6.dex */
public final class TraceIdManager {
    private static final int SEQ_BIT_SIZE = 100;
    private static final int TIME_BIT = 268435455;
    private static final Object LOCK = new Object();
    private static InheritableThreadLocal<String> traceIdThreadLocal = new InheritableThreadLocal<>();
    private static int count = 0;

    private TraceIdManager() {
    }

    public static String genTraceId() {
        int i;
        synchronized (LOCK) {
            i = count;
            int i2 = i + 1;
            count = i2;
            count = i2 % 100;
        }
        String str = String.format(Locale.ENGLISH, "%07X", Long.valueOf(System.currentTimeMillis() & 268435455)) + String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i));
        traceIdThreadLocal.set(str);
        return str;
    }

    public static void resetTraceId(String str) {
        traceIdThreadLocal.set(str);
    }

    public static String getTraceId() {
        return traceIdThreadLocal.get();
    }

    public static void clearTraceId() {
        traceIdThreadLocal.remove();
    }
}
