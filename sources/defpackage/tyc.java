package defpackage;

import android.util.Log;

/* loaded from: classes7.dex */
public class tyc {
    private static boolean b = true;

    public static void a(String str) {
        if (b) {
            Log.i("iReaderScheme", str);
        }
    }

    public static void b(String str) {
        if (b) {
            Log.e("iReaderScheme", str);
        }
    }
}
