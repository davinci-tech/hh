package defpackage;

import android.text.TextUtils;

/* loaded from: classes.dex */
public final class ixo {
    public static <T> T e(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(str));
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(String.valueOf(str2));
        }
        return str;
    }
}
