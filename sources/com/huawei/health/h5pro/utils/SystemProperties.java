package com.huawei.health.h5pro.utils;

import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes3.dex */
public class SystemProperties {

    /* renamed from: a, reason: collision with root package name */
    public static Class<?> f2459a;

    public static void initSystemProperties() {
        if (f2459a != null) {
            return;
        }
        try {
            f2459a = Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException e) {
            LogUtil.e("H5PRO_SystemProperties", "SystemProperties -> " + e.getMessage());
        }
    }

    public static String getString(String str, String str2) {
        initSystemProperties();
        if (f2459a != null && !TextUtils.isEmpty(str)) {
            try {
                return (String) f2459a.getMethod("get", String.class, String.class).invoke(f2459a, str, str2);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                LogUtil.e("H5PRO_SystemProperties", "getString -> " + e.getMessage());
            }
        }
        return str2;
    }

    public static int getInt(String str, int i) {
        initSystemProperties();
        if (f2459a != null && !TextUtils.isEmpty(str)) {
            try {
                Object invoke = f2459a.getMethod("getInt", String.class, Integer.TYPE).invoke(f2459a, str, Integer.valueOf(i));
                if (invoke instanceof Integer) {
                    return ((Integer) invoke).intValue();
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                LogUtil.e("H5PRO_SystemProperties", "getInt -> " + e.getMessage());
            }
        }
        return i;
    }
}
