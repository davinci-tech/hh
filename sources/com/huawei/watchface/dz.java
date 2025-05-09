package com.huawei.watchface;

import android.content.SharedPreferences;
import com.huawei.watchface.environment.Environment;

/* loaded from: classes7.dex */
public class dz {
    public static void a(String str, String str2) {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "watch_face_shared_preference");
        if (a2 == null) {
            return;
        }
        SharedPreferences.Editor edit = a2.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static String a(String str) {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "watch_face_shared_preference");
        if (a2 == null) {
            return null;
        }
        return a2.getString(str, null);
    }

    public static void b(String str) {
        SharedPreferences b = dp.b(Environment.getApplicationContext(), "my_watch_face");
        if (b == null) {
            return;
        }
        SharedPreferences.Editor edit = b.edit();
        edit.putString("my_watchface_apply_time", str);
        edit.apply();
    }

    public static String a() {
        SharedPreferences b = dp.b(Environment.getApplicationContext(), "my_watch_face");
        if (b == null) {
            return null;
        }
        return b.getString("my_watchface_apply_time", null);
    }

    public static void a(String str, int i) {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "watch_face_shared_preference");
        if (a2 == null) {
            return;
        }
        SharedPreferences.Editor edit = a2.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static int b(String str, int i) {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "watch_face_shared_preference");
        if (a2 == null) {
            return 0;
        }
        return a2.getInt(str, i);
    }

    public static void b(String str, String str2) {
        a(str, ao.a(str2, "storagePw"));
    }

    public static String c(String str) {
        return ao.c(a(str), "storagePw");
    }
}
