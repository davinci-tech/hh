package defpackage;

import android.content.Context;

/* loaded from: classes.dex */
public class gxf {
    private static Context b;

    public static void c(Context context) {
        if (context == null) {
            throw new RuntimeException("HealthApplication context is null");
        }
        b = context;
    }

    public static Context d() {
        return b;
    }
}
