package defpackage;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public class stq {
    public static void d(String str, String str2, boolean z) {
        stt.b(str, str2, z);
    }

    private static String a(String str, String str2, Throwable th, boolean z) {
        StringBuilder sb = new StringBuilder(256);
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            sb.append("    ");
        }
        if (!TextUtils.isEmpty(str2)) {
            if (z) {
                sb.append(stt.a(str2));
            } else {
                sb.append(str2);
            }
        }
        if (th != null) {
            sb.append("    ");
            sb.append(stt.a(th));
        }
        return sb.toString();
    }

    public static void c(String str, String str2, boolean z) {
        stt.d(a("OverSea", str2, null, z));
        stt.a(str, str2, z);
    }

    public static void a(String str, String str2) {
        a(str, str2, false);
    }

    public static void a(String str, String str2, boolean z) {
        stt.d(a("OverSea", str2, null, z));
        stt.b(str, str2, null, z);
    }

    public static void e(String str, String str2, boolean z) {
        stt.d(a("OverSea", str2, null, z));
        stt.b(str, str2, null, z);
    }

    public static void e(String str, String str2, Throwable th, boolean z) {
        stt.d(a("OverSea", str2, th, z));
        stt.b(str, str2, th, z);
    }

    public static void c(String str, String str2) {
        d(str, str2, false);
    }

    public static void b(String str, String str2) {
        c(str, str2, false);
    }

    public static void e(String str, String str2) {
        e(str, str2, false);
    }
}
