package defpackage;

import android.os.Build;
import com.huawei.android.os.SystemPropertiesEx;

/* loaded from: classes3.dex */
public class beo {
    public static int b(String str, int i) {
        if (!b()) {
            try {
                return SystemPropertiesEx.getInt(str, i);
            } catch (Throwable unused) {
                return ber.b(str, i);
            }
        }
        return ber.b(str, i);
    }

    public static String b(String str, String str2) {
        if (!b()) {
            try {
                return SystemPropertiesEx.get(str, str2);
            } catch (Throwable unused) {
                return ber.a(str, str2);
            }
        }
        return ber.a(str, str2);
    }

    private static boolean b() {
        return Build.VERSION.SDK_INT < 28;
    }
}
