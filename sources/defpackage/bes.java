package defpackage;

import android.util.Log;

/* loaded from: classes3.dex */
public class bes {
    public static void e(String str, String str2) {
        if (bec.a()) {
            Log.i("CodeValuePlatform_" + str, str2);
        }
    }

    public static void a(String str, String str2) {
        if (bec.a()) {
            Log.d("CodeValuePlatform_" + str, str2);
        }
    }

    public static void d(String str, String str2) {
        Log.e("CodeValuePlatform_" + str, str2);
    }

    public static void b(String str, String str2) {
        Log.w("CodeValuePlatform_" + str, str2);
    }
}
