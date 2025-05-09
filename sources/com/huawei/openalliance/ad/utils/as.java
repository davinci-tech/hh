package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.ho;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes5.dex */
public class as {

    /* renamed from: a, reason: collision with root package name */
    public static final String f7592a = a("ro.build.version.emui", "");

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static final int f7593a = as.a("ro.build.hw_emui_api_level", 0);
        public static final int b = as.a("ro.build.magic_api_level", 0);
    }

    public static String a(String str, String str2) {
        StringBuilder sb;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getDeclaredMethod("get", String.class, String.class).invoke(cls, str, str2);
            if (invoke instanceof String) {
                return (String) invoke;
            }
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            sb = new StringBuilder("An exception occurred while reading SystemProperties: ");
            sb.append(str);
            ho.d("HwBuildEx", sb.toString());
            return str2;
        } catch (Throwable unused2) {
            sb = new StringBuilder("An throwable occurred while reading SystemProperties: ");
            sb.append(str);
            ho.d("HwBuildEx", sb.toString());
            return str2;
        }
        return str2;
    }

    public static int a(String str, int i) {
        StringBuilder sb;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getDeclaredMethod("getInt", String.class, Integer.TYPE).invoke(cls, str, Integer.valueOf(i));
            if (invoke instanceof Integer) {
                return ((Integer) invoke).intValue();
            }
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            sb = new StringBuilder("An exception occurred while reading SystemProperties: ");
            sb.append(str);
            ho.d("HwBuildEx", sb.toString());
            return i;
        } catch (Throwable unused2) {
            sb = new StringBuilder("An Throwable occurred while reading SystemProperties: ");
            sb.append(str);
            ho.d("HwBuildEx", sb.toString());
            return i;
        }
        return i;
    }
}
