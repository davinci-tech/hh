package com.huawei.secure.android.common.util;

import android.util.Log;

/* loaded from: classes6.dex */
public class SafeString {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8648a = "SafeString";
    private static final String b = "";

    public static String replace(String str, CharSequence charSequence, CharSequence charSequence2) {
        if (str != null && charSequence != null && charSequence2 != null) {
            try {
                return str.replace(charSequence, charSequence2);
            } catch (Exception e) {
                Log.e(f8648a, "replace: " + e.getMessage());
            }
        }
        return str;
    }

    public static String substring(String str, int i) {
        if (str == null || str.length() < i || i < 0) {
            return "";
        }
        try {
            return str.substring(i);
        } catch (Exception e) {
            Log.e(f8648a, "substring exception: " + e.getMessage());
            return "";
        }
    }

    public static String substring(String str, int i, int i2) {
        if (str == null || i < 0 || i2 > str.length() || i2 < i) {
            return "";
        }
        try {
            return str.substring(i, i2);
        } catch (Exception e) {
            Log.e(f8648a, "substring: " + e.getMessage());
            return "";
        }
    }
}
