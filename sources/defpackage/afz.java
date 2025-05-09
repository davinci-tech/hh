package defpackage;

import android.app.UiModeManager;
import android.content.Context;
import com.huawei.android.app.PackageManagerEx;

/* loaded from: classes3.dex */
public class afz {
    private static int d = -1;

    private static boolean b(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    private static boolean c(Context context) {
        return ((UiModeManager) context.getSystemService("uimode")).getCurrentModeType() == 4;
    }

    private static boolean d(Context context) {
        return context.getPackageManager().hasSystemFeature("com.huawei.software.features.car");
    }

    private static boolean a(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }

    private static boolean c(String str) {
        try {
            return PackageManagerEx.hasHwSystemFeature(str);
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean c() {
        return c("com.huawei.software.features.kidpad");
    }

    public static int e(Context context) {
        int i = d;
        if (i >= 0) {
            return i;
        }
        int i2 = c(context) ? 1 : b(context) ? 2 : a(context) ? 3 : c() ? 4 : d(context) ? 7 : 0;
        d = i2;
        return i2;
    }
}
