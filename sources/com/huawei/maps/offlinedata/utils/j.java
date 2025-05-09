package com.huawei.maps.offlinedata.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes5.dex */
public class j {
    public static String a(String str, String str2, Context context) {
        try {
            return context.getSharedPreferences("offlineMapPrefer", 0).getString(str, str2);
        } catch (Exception unused) {
            g.d("SharedPreUtil", "getString fail, wrong  with key: " + str);
            return str2;
        }
    }

    public static void b(String str, String str2, Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("offlineMapPrefer", 0).edit();
            edit.putString(str, str2);
            edit.apply();
        } catch (Exception unused) {
            g.d("SharedPreUtil", "putString, wrong  with key: " + str);
        }
    }

    public static long a(String str, long j, Context context) {
        try {
            return context.getSharedPreferences("offlineMapPrefer", 0).getLong(str, j);
        } catch (Exception unused) {
            g.d("SharedPreUtil", "getLong fail, wrong with key: " + str);
            return j;
        }
    }

    public static void b(String str, long j, Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("offlineMapPrefer", 0).edit();
            edit.putLong(str, j);
            edit.apply();
        } catch (Exception unused) {
            g.d("SharedPreUtil", "putLong, wrong with key: " + str);
        }
    }
}
