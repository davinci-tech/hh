package defpackage;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public final class utq {
    public static <T> T a(T t) {
        t.getClass();
        return t;
    }

    public static <T> T d(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static String d(String str, Object obj) {
        d(str, obj);
        e(!TextUtils.isEmpty(str), obj);
        return str;
    }

    public static String e(String str, Object obj) {
        if (str != null) {
            d(str, obj);
        }
        return str;
    }

    public static void e(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void e(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }
}
