package com.huawei.wear.oversea.util;

import defpackage.stq;

/* loaded from: classes7.dex */
public abstract class PropertyUtil {
    private static final String TAG = "PropertyUtil";

    public static String getProp(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, str);
        } catch (IllegalAccessException unused) {
            stq.e(TAG, "IllegalAccessException can not get language and region:IllegalAccessException", false);
            return "";
        } catch (Exception unused2) {
            stq.e(TAG, "can not get language and region Exception", false);
            return "";
        }
    }
}
