package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes7.dex */
public class twi {

    /* renamed from: a, reason: collision with root package name */
    private static SharedPreferences f17396a;

    public static void c(String str, String str2, Context context) {
        ffx_(context).edit().putString(str, str2).apply();
    }

    public static void a(String str, long j, Context context) {
        ffx_(context).edit().putLong(str, j).apply();
    }

    public static void a(String str, int i, Context context) {
        ffx_(context).edit().putInt(str, i).apply();
    }

    public static String b(String str, String str2, Context context) {
        return ffx_(context).getString(str, str2);
    }

    public static long b(String str, long j, Context context) {
        return ffx_(context).getLong(str, j);
    }

    public static int c(String str, int i, Context context) {
        return ffx_(context).getInt(str, i);
    }

    public static SharedPreferences ffx_(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (twi.class) {
            if (f17396a == null) {
                f17396a = context.createDeviceProtectedStorageContext().getSharedPreferences("ucs.sdk", 0);
            }
            sharedPreferences = f17396a;
        }
        return sharedPreferences;
    }
}
