package com.huawei.hms.mlkit.common.ha;

import android.text.TextUtils;
import android.util.Log;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static final Pattern f5046a = Pattern.compile("[0-9]*[a-z|A-Z]*[一-龥]*");

    public static String a(String str, boolean z) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                if (!TextUtils.isEmpty(str)) {
                    int length = str.length();
                    int i = 1;
                    if (1 == length) {
                        str = String.valueOf('*');
                    } else {
                        StringBuilder sb2 = new StringBuilder(length);
                        for (int i2 = 0; i2 < length; i2++) {
                            char charAt = str.charAt(i2);
                            if (f5046a.matcher(String.valueOf(charAt)).matches()) {
                                if (i % 2 == 0) {
                                    charAt = '*';
                                }
                                i++;
                            }
                            sb2.append(charAt);
                        }
                        str = sb2.toString();
                    }
                }
                sb.append(str);
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static void b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, a(str2, false));
    }

    public static void c(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, a(str2, false));
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, a(str2, false));
    }
}
