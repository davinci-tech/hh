package defpackage;

import android.text.TextUtils;

/* loaded from: classes9.dex */
public class tom {
    public static <T> T e(T t, Object obj) {
        if (t != null) {
            return t;
        }
        tos.e("ServicePreconditions", String.valueOf(obj));
        throw new IllegalStateException(String.valueOf(5));
    }

    public static String c(String str, Object obj) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        tos.e("ServicePreconditions", String.valueOf(obj));
        throw new IllegalArgumentException(String.valueOf(obj));
    }
}
