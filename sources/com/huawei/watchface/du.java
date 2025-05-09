package com.huawei.watchface;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes7.dex */
public class du {

    /* renamed from: a, reason: collision with root package name */
    private static Handler f10997a = new Handler(Looper.getMainLooper());

    public static void a(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        f10997a.post(runnable);
    }
}
