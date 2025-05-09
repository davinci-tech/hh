package com.huawei.openalliance.ad.utils;

/* loaded from: classes5.dex */
public abstract class bo {

    /* renamed from: a, reason: collision with root package name */
    private static final al f7641a;

    public static void a(String str) {
        f7641a.a(str);
    }

    public static void a(Runnable runnable, String str, long j) {
        f7641a.a(runnable, str, j);
    }

    public static void a(Runnable runnable, long j) {
        f7641a.a(runnable, null, j);
    }

    public static void a(Runnable runnable) {
        f7641a.a(runnable);
    }

    static {
        al alVar = new al("pps-msg-queue");
        f7641a = alVar;
        alVar.a();
    }
}
