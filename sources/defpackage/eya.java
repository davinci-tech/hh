package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes8.dex */
public class eya {
    private static SharedPreferences b;

    public static SharedPreferences atS_(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (eya.class) {
            if (b == null) {
                b = context.createDeviceProtectedStorageContext().getSharedPreferences("WearLbsAuth", 0);
            }
            sharedPreferences = b;
        }
        return sharedPreferences;
    }

    public static void a(String str, String str2, Context context) {
        atS_(context).edit().putString(str, str2).apply();
    }

    public static String d(String str, String str2, Context context) {
        return atS_(context).getString(str, str2);
    }
}
