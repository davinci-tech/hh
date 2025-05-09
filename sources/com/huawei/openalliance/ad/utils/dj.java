package com.huawei.openalliance.ad.utils;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes5.dex */
public abstract class dj {

    /* renamed from: a, reason: collision with root package name */
    protected static final ak f7699a = new ak(new Handler(Looper.getMainLooper()));

    public static void a(String str) {
        f7699a.a(str);
    }

    public static void a(Runnable runnable, String str, long j) {
        f7699a.a(runnable, str, j);
    }

    public static void a(Runnable runnable, long j) {
        f7699a.a(runnable, null, j);
    }

    public static void a(Runnable runnable) {
        f7699a.a(runnable);
    }
}
