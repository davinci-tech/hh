package com.huawei.secure.android.common.util;

import android.util.Log;

/* loaded from: classes9.dex */
public class SafeStringBuffer {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8649a = "SafeStringBuffer";
    private static final String b = "";

    public static String substring(StringBuffer stringBuffer, int i) {
        if (stringBuffer == null || stringBuffer.length() < i || i < 0) {
            return "";
        }
        try {
            return stringBuffer.substring(i);
        } catch (Exception e) {
            Log.e(f8649a, "substring exception: " + e.getMessage());
            return "";
        }
    }

    public static String substring(StringBuffer stringBuffer, int i, int i2) {
        if (stringBuffer == null || i < 0 || i2 > stringBuffer.length() || i2 < i) {
            return "";
        }
        try {
            return stringBuffer.substring(i, i2);
        } catch (Exception e) {
            Log.e(f8649a, "substring: " + e.getMessage());
            return "";
        }
    }
}
