package defpackage;

import android.util.Log;

/* loaded from: classes2.dex */
public class oa {
    public static boolean d = false;

    public static void e(String str, String str2) {
        if (d) {
            Log.i("ChipseaLib_", str + ": " + str2);
        }
    }

    public static void c(String str, String str2) {
        if (d) {
            Log.e("ChipseaLib_", str + ": " + str2);
        }
    }
}
