package defpackage;

import android.content.Context;

/* loaded from: classes7.dex */
public final class id {
    public static id d = new id();

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
        } catch (Exception unused) {
            return "0.0.0";
        }
    }

    public static id b() {
        return d;
    }
}
