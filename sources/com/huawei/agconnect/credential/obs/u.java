package com.huawei.agconnect.credential.obs;

import android.util.Log;

/* loaded from: classes2.dex */
public class u {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1780a = "AGC_LOG";
    private static final String b = "[AGC_LOG]";

    public void d(String str, String str2) {
        if (a(5)) {
            Log.w(b + str, str2);
        }
    }

    public void c(String str, String str2) {
        if (a(4)) {
            Log.i(b + str, str2);
        }
    }

    public void b(String str, String str2) {
        if (a(3)) {
            Log.d(b + str, str2);
        }
    }

    public void a(String str, String str2, Throwable th) {
        if (a(6)) {
            Log.e(b + str, str2, th);
        }
    }

    public void a(String str, String str2) {
        if (a(2)) {
            Log.d(b + str, str2);
        }
    }

    private static boolean a(int i) {
        return Log.isLoggable(f1780a, i);
    }
}
