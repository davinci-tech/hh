package com.huawei.watchface;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public final class dh {
    public static int a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static long a(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static double a(String str, double d) {
        if (TextUtils.isEmpty(str)) {
            return d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d;
        }
    }
}
