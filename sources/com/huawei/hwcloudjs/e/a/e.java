package com.huawei.hwcloudjs.e.a;

import android.content.Context;

/* loaded from: classes9.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6220a = "CacheRequestManager";

    public static void a(String[] strArr, int i, Context context) {
        com.huawei.hwcloudjs.f.d.c(f6220a, "enter into preload", true);
        new Thread(new d(strArr, context, i)).start();
    }
}
