package com.huawei.haf.bundle.extension;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.huawei.haf.common.utils.CommonConstant;
import health.compact.a.LogUtil;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
final class ComponentInfoReader {
    private ComponentInfoReader() {
    }

    private static Class d() throws ClassNotFoundException {
        return Class.forName("com.huawei.haf.bundle.extension.ComponentInfo");
    }

    static String[] a(String str) {
        return b(str + "_A_", str, "Activities");
    }

    static String[] d(String str) {
        return b(str + "_S_", str, "Services");
    }

    static String[] c(String str) {
        return b(str + "_R_", str, "Receivers");
    }

    static String e(Context context, String str) {
        return a(context, str, b(str));
    }

    static String c(Context context, String str) {
        String b = b(str);
        String d = d(context, str, b + "_desc", (String) null);
        return TextUtils.isEmpty(d) ? a(context, str, b) : d;
    }

    private static String a(Context context, String str, String str2) {
        return d(context, str, str2, str);
    }

    private static String d(Context context, String str, String str2, String str3) {
        try {
            Resources resources = context.getResources();
            return resources.getString(resources.getIdentifier(str2, "string", context.getPackageName()));
        } catch (Resources.NotFoundException e) {
            LogUtil.a("Bundle_ComponentInfoMgr", "getModuleString ", str, ", resId=", str2, ", ex =", LogUtil.a(e));
            return str3;
        }
    }

    private static String b(String str) {
        return a(str + "_T", str, str, "Title");
    }

    private static String[] b(String str, String str2, String str3) {
        int d = d(str + "N", 0, str2, str3);
        if (d == 0) {
            return CommonConstant.e;
        }
        String[] strArr = new String[d];
        for (int i = 0; i < d; i++) {
            strArr[i] = a(str + i, null, str2, str3);
        }
        return strArr;
    }

    private static String a(String str, String str2, String str3, String str4) {
        Object d = d(str, str3, str4);
        return d instanceof String ? ((String) d).intern() : str2;
    }

    private static int d(String str, int i, String str2, String str3) {
        Object d = d(str, str2, str3);
        return d instanceof Integer ? ((Integer) d).intValue() : i;
    }

    private static Object d(String str, String str2, String str3) {
        try {
            Field field = d().getField(str);
            field.setAccessible(true);
            return field.get(null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            LogUtil.d("Bundle_ComponentInfoMgr", "getComponentInfoValue, ", str2, " not exist ", str3);
            return null;
        }
    }
}
