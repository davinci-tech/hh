package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.operation.utils.Constants;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes2.dex */
public final class it {

    /* renamed from: a, reason: collision with root package name */
    public static final String f1197a = "/a/";
    static final String b = "b";
    static final String c = "c";
    static final String d = "d";
    public static String e = "s";
    public static final String f = "g";
    public static final String g = "h";
    public static final String h = "e";
    public static final String i = "f";
    public static final String j = "j";
    public static final String k = "k";
    private static long l;
    private static Vector<hz> m = new Vector<>();

    public static String a(Context context, String str) {
        return context.getSharedPreferences("AMSKLG_CFG", 0).getString(str, "");
    }

    public static void a(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("AMSKLG_CFG", 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static void b(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences("AMSKLG_CFG", 0).edit();
        edit.remove(str);
        edit.apply();
    }

    public static String c(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + f1197a + str;
    }

    public static void a(final Context context) {
        try {
            if (System.currentTimeMillis() - l < 60000) {
                return;
            }
            l = System.currentTimeMillis();
            la.a().a(new lb() { // from class: com.amap.api.col.3sl.it.1
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    try {
                        iw.b(context);
                        iw.d(context);
                        iw.c(context);
                        kj.a(context);
                        kh.a(context);
                    } catch (RejectedExecutionException unused) {
                    } catch (Throwable th) {
                        iv.c(th, "Lg", "proL");
                    }
                }
            });
        } catch (Throwable th) {
            iv.c(th, "Lg", "proL");
        }
    }

    public static void a(hz hzVar) {
        try {
            synchronized (Looper.getMainLooper()) {
                if (hzVar == null) {
                    return;
                }
                if (m.contains(hzVar)) {
                    return;
                }
                m.add(hzVar);
            }
        } catch (Throwable unused) {
        }
    }

    static List<hz> a() {
        Vector<hz> vector;
        try {
            synchronized (Looper.getMainLooper()) {
                vector = m;
            }
            return vector;
        } catch (Throwable th) {
            th.printStackTrace();
            return m;
        }
    }

    static boolean a(String[] strArr, String str) {
        if (strArr != null && str != null) {
            try {
                String[] split = str.split("\n");
                for (String str2 : split) {
                    String trim = str2.trim();
                    if (!TextUtils.isEmpty(trim) && trim.startsWith("at ") && trim.contains("uncaughtException")) {
                        return false;
                    }
                }
                for (String str3 : split) {
                    if (b(strArr, str3.trim())) {
                        return true;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    static boolean b(String[] strArr, String str) {
        if (strArr != null && str != null) {
            try {
                for (String str2 : strArr) {
                    str = str.trim();
                    if (str.startsWith("at ")) {
                        if (str.contains(str2 + ".") && str.endsWith(Constants.RIGHT_BRACKET_ONLY) && !str.contains("uncaughtException")) {
                            return true;
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }
}
