package defpackage;

import android.content.Context;

/* loaded from: classes2.dex */
public class wm {
    private static boolean e() {
        return true;
    }

    public static Context d(Context context) {
        return e() ? context.createDeviceProtectedStorageContext() : context;
    }
}
