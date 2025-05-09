package com.huawei.secure.android.common.detect.b;

import android.util.Log;

/* loaded from: classes6.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8581a = "SecurityComp10201302: ";

    public static void a(String str, String str2) {
    }

    public static void a(String str, String str2, Throwable th) {
        Log.e(a(str), str2, th);
    }

    public static void b(String str, String str2) {
        Log.e(a(str), str2);
    }

    public static void c(String str, String str2) {
        Log.i(a(str), str2);
    }

    public static void d(String str, String str2) {
        Log.v(a(str), str2);
    }

    public static void e(String str, String str2) {
        Log.w(a(str), str2);
    }

    private static String a(String str) {
        return f8581a + str;
    }
}
