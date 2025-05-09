package com.huawei.hms.scankit.p;

import android.text.TextUtils;
import android.util.Log;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class o4 {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f5846a = Pattern.compile("[0-9]*[a-z|A-Z]*[一-龥]*");

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

    private static Throwable a(Throwable th) {
        return th;
    }

    public static void b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, a(str2, false));
    }

    private static String c(String str, String str2) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(a(str2));
        }
        return sb.toString();
    }

    public static void d(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, a(str2, false));
    }

    public static void e(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, a(str2, false));
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, a(str2, false));
    }

    public static void a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.i(str, c(str2, str3));
    }

    public static void a(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.w(str, a(str2, false), a(th));
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
            if (f5846a.matcher(String.valueOf(charAt)).matches()) {
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
