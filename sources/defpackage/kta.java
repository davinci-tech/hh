package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: classes5.dex */
public class kta {
    public static String b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager == null ? "" : packageManager.getApplicationLabel(context.getApplicationInfo()).toString();
    }
}
