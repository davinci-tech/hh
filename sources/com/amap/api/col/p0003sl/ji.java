package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class ji {

    /* renamed from: a, reason: collision with root package name */
    static byte[] f1217a;
    static byte[] b;
    private String c;

    public ji(String str) {
        this.c = hv.b(TextUtils.isDigitsOnly(str) ? "SPUtil" : str);
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putString(str2, ia.g(a(context, ia.a(str3))));
            a(edit);
        } catch (Throwable unused) {
        }
    }

    public static byte[] a(Context context, byte[] bArr) {
        try {
            return hs.b(a(context), bArr, b(context));
        } catch (Throwable unused) {
            return new byte[0];
        }
    }

    public static byte[] b(Context context, byte[] bArr) {
        try {
            return hs.a(a(context), bArr, b(context));
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    private static byte[] a(Context context) {
        if (context == null) {
            return new byte[0];
        }
        byte[] bArr = f1217a;
        if (bArr != null && bArr.length > 0) {
            return bArr;
        }
        byte[] bytes = hn.f(context).getBytes();
        f1217a = bytes;
        return bytes;
    }

    private static byte[] b(Context context) {
        byte[] bArr = b;
        if (bArr != null && bArr.length > 0) {
            return bArr;
        }
        byte[] copyOfRange = Arrays.copyOfRange(a(context), 0, a(context).length / 2);
        b = copyOfRange;
        return copyOfRange;
    }

    public static String a(Context context, String str, String str2) {
        if (context == null) {
            return "";
        }
        try {
            return ia.a(b(context, ia.d(context.getSharedPreferences(str, 0).getString(str2, ""))));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static SharedPreferences.Editor a(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return context.getSharedPreferences(str, 0).edit();
        } catch (Throwable th) {
            is.a(th, "sp", "ge");
            return null;
        }
    }

    public static void a(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        try {
            editor.apply();
        } catch (Throwable th) {
            is.a(th, "sp", "cm");
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, boolean z) {
        try {
            editor.putBoolean(str, z);
        } catch (Throwable th) {
            iv.c(th, "csp", "setPrefsStr");
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, int i) {
        try {
            editor.putInt(str, i);
        } catch (Throwable th) {
            iv.c(th, "csp", "putPrefsInt");
        }
    }

    public static boolean a(Context context, String str, String str2, boolean z) {
        try {
            return context.getSharedPreferences(str, 0).getBoolean(str2, z);
        } catch (Throwable th) {
            iv.c(th, "csp", "gbv");
            return z;
        }
    }

    public static int a(Context context, String str, String str2, int i) {
        try {
            return context.getSharedPreferences(str, 0).getInt(str2, i);
        } catch (Throwable th) {
            iv.c(th, "csp", "giv");
            return i;
        }
    }

    public static long b(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getLong(str2, 0L);
        } catch (Throwable th) {
            iv.c(th, "csp", "glv");
            return 0L;
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, long j) {
        if (editor == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            editor.putLong(str, j);
        } catch (Throwable th) {
            iv.c(th, "csp", "plv");
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, String str2) {
        if (editor != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    editor.putString(str, str2);
                }
            } catch (Throwable th) {
                is.a(th, "sp", "ps");
            }
        }
    }

    public static String b(Context context, String str, String str2, String str3) {
        if (context == null) {
            return null;
        }
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
            iv.c(th, "csp", "gsv");
            return str3;
        }
    }

    public static void a(SharedPreferences.Editor editor, String str) {
        if (editor != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                editor.remove(str);
            } catch (Throwable th) {
                is.a(th, "sp", "rk");
            }
        }
    }
}
