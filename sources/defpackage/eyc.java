package defpackage;

import android.content.Context;

/* loaded from: classes8.dex */
public class eyc {
    private static Context b;
    private static String e;

    public static void a(Context context) {
        eym.b("LocationSdkUtil", "setContext start");
        b = context;
        eyd.c();
    }

    public static Context a() {
        return b;
    }

    public static void d(String str) {
        eym.b("LocationSdkUtil", "setSerCountry start");
        e = str;
    }

    public static String e() {
        return e;
    }
}
