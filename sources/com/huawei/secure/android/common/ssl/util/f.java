package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes9.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8638a = "aegis";
    private static SharedPreferences b;

    public static long a(String str, long j, Context context) {
        return b(context).getLong(str, j);
    }

    public static SharedPreferences b(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (f.class) {
            if (b == null) {
                b = context.createDeviceProtectedStorageContext().getSharedPreferences(f8638a, 0);
            }
            sharedPreferences = b;
        }
        return sharedPreferences;
    }

    public static int a(String str, int i, Context context) {
        return b(context).getInt(str, i);
    }

    public static String a(String str, String str2, Context context) {
        return b(context).getString(str, str2);
    }

    public static void a(String str, Context context) {
        b(context).edit().remove(str).apply();
    }

    public static void a(Context context) {
        b(context).edit().clear().apply();
    }

    public static void b(String str, long j, Context context) {
        b(context).edit().putLong(str, j).apply();
    }

    public static void b(String str, int i, Context context) {
        b(context).edit().putInt(str, i).apply();
    }

    public static void b(String str, String str2, Context context) {
        b(context).edit().putString(str, str2).apply();
    }
}
