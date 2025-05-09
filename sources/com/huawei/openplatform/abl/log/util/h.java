package com.huawei.openplatform.abl.log.util;

import android.os.Build;
import android.util.Log;

/* loaded from: classes5.dex */
public abstract class h {
    public static boolean a() {
        try {
            int a2 = a("com.hihonor.android.os.Build$VERSION", "MAGIC_SDK_INT");
            int a3 = a("com.hihonor.android.os.Build$VERSION_CODES", "MAGIC_6_0");
            if (Build.MANUFACTURER.equalsIgnoreCase("HONOR")) {
                if (Build.VERSION.SDK_INT >= 31 && a2 >= a3) {
                    return true;
                }
            }
        } catch (Throwable th) {
            Log.e("SystemUtil", "isHonor6UpPhone Error:" + th.getClass().getSimpleName());
        }
        return false;
    }

    public static String a(String str) {
        StringBuilder sb;
        Class<?> cls;
        try {
            if (Build.VERSION.SDK_INT >= 27) {
                try {
                    cls = Class.forName(a() ? "com.hihonor.android.os.SystemPropertiesEx" : "com.huawei.android.os.SystemPropertiesEx");
                } catch (ClassNotFoundException unused) {
                }
                return (String) cls.getMethod("get", String.class).invoke(cls, str);
            }
            cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, str);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("getSystemProperties RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            Log.w("SystemUtil", sb.toString());
            return null;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("getSystemProperties Exception:");
            sb.append(e.getClass().getSimpleName());
            Log.w("SystemUtil", sb.toString());
            return null;
        }
    }

    private static int a(String str, String str2) {
        try {
            return ((Integer) Class.forName(str).getDeclaredField(str2).get(null)).intValue();
        } catch (Throwable th) {
            Log.w("SystemUtil", "getIntFieldValue Exception:" + th.getClass().getSimpleName());
            return 0;
        }
    }
}
