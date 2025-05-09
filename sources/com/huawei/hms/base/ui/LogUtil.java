package com.huawei.hms.base.ui;

import android.text.TextUtils;
import android.util.Log;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class LogUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f4425a = Pattern.compile("[0-9]*[a-z|A-Z]*[一-龥]*");

    private static String a(String str, boolean z) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                sb.append(a(str));
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static void e(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, a(str2, z));
    }

    public static void e(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, a(str2, false));
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        int i = 1;
        if (1 == length) {
            return String.valueOf('*');
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (f4425a.matcher(String.valueOf(charAt)).matches()) {
                if (i % 2 == 0) {
                    charAt = '*';
                }
                i++;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }
}
