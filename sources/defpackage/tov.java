package defpackage;

import android.util.Log;

/* loaded from: classes7.dex */
public class tov {
    private static final boolean c = tnt.e;

    public static int b(String str, String str2) {
        if (!c) {
            return 0;
        }
        return Log.d("WearEngine_" + str, str2);
    }

    public static int a(String str, String str2) {
        return Log.i("WearEngine_" + str, str2);
    }

    public static int c(String str, String str2) {
        return Log.w("WearEngine_" + str, str2);
    }

    public static int d(String str, String str2) {
        return Log.e("WearEngine_" + str, str2);
    }
}
