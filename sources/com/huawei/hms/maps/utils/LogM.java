package com.huawei.hms.maps.utils;

import android.text.TextUtils;
import android.util.Log;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class LogM {

    /* renamed from: a, reason: collision with root package name */
    private static StringBuffer f5032a = new StringBuffer();
    private static final Pattern b = Pattern.compile("[0-9]*[a-z|A-Z]*[一-龥]*");
    private static final boolean c = Log.isLoggable("HmsMapSite", 2);
    private static final boolean d = Log.isLoggable("HmsMapSite", 3);
    private static final boolean e = Log.isLoggable("HmsMapSite", 4);
    private static final boolean f = Log.isLoggable("HmsMapSite", 5);
    private static final boolean g = Log.isLoggable("HmsMapSite", 6);

    public static void w(String str, String str2, boolean z) {
        if (f) {
            w(str, str2, (Throwable) null, z);
        }
    }

    public static void w(String str, String str2, Throwable th, boolean z) {
        if (f) {
            String a2 = a(str);
            if (th == null) {
                maf.c(a2, str2, z);
            } else {
                maf.c(a2, str2, th, z);
            }
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (f) {
            w(str, str2, th, false);
        }
    }

    public static void w(String str, String str2, String str3, Throwable th) {
        if (f) {
            String a2 = a(str);
            if (th == null) {
                maf.c(a2, str2, str3);
            } else {
                maf.c(a2, str2, str3, th);
            }
        }
    }

    public static void w(String str, String str2, String str3) {
        if (f) {
            w(str, str2, str3, (Throwable) null);
        }
    }

    public static void w(String str, String str2) {
        if (f) {
            w(str, str2, (Throwable) null, false);
        }
    }

    public static void v(String str, String str2) {
        if (c) {
            maf.a(a(str), str2);
        }
    }

    public static void i(String str, String str2, boolean z) {
        if (e) {
            i(str, str2, (Throwable) null, z);
        }
    }

    public static void i(String str, String str2, Throwable th, boolean z) {
        if (e) {
            String a2 = a(str);
            if (th == null) {
                maf.b(a2, str2, z);
            } else {
                maf.b(a2, str2, th, z);
            }
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (e) {
            i(str, str2, th, false);
        }
    }

    public static void i(String str, String str2, String str3, Throwable th) {
        if (e) {
            String a2 = a(str);
            if (th == null) {
                maf.b(a2, str2, str3);
            } else {
                maf.b(a2, str2, str3, th);
            }
        }
    }

    public static void i(String str, String str2, String str3) {
        if (e) {
            i(str, str2, str3, (Throwable) null);
        }
    }

    public static void i(String str, String str2) {
        if (e) {
            i(str, str2, (Throwable) null, false);
        }
    }

    public static String getLogMsg(String str, String str2) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(maf.a(str2));
        }
        return sb.toString();
    }

    public static void e(String str, String str2, boolean z) {
        if (g) {
            e(str, str2, (Throwable) null, z);
        }
    }

    public static void e(String str, String str2, Throwable th, boolean z) {
        if (g) {
            String a2 = a(str);
            if (th == null) {
                maf.d(a2, str2, z);
            } else {
                maf.d(a2, str2, th, z);
            }
        }
    }

    public static void e(String str, String str2, String str3, Throwable th) {
        if (g) {
            String a2 = a(str);
            if (th == null) {
                maf.d(a2, str2, str3);
                getLogMsg(str2, str3);
            } else {
                maf.d(a2, str2, str3, th);
                getLogMsg(str2, str3);
                System.lineSeparator();
                Log.getStackTraceString(maf.a(th));
            }
        }
    }

    public static void e(String str, String str2, String str3) {
        if (g) {
            e(str, str2, str3, (Throwable) null);
        }
    }

    public static void e(String str, String str2) {
        if (g) {
            e(str, str2, (Throwable) null, false);
        }
    }

    public static void d(String str, String str2, boolean z) {
        if (d) {
            d(str, str2, (Throwable) null, z);
        }
    }

    public static void d(String str, String str2, Throwable th, boolean z) {
        if (d) {
            String a2 = a(str);
            if (th == null) {
                maf.a(a2, str2, z);
            } else {
                maf.a(a2, str2, th, z);
            }
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (d) {
            d(str, str2, th, false);
        }
    }

    public static void d(String str, String str2, String str3, Throwable th) {
        if (d) {
            String a2 = a(str);
            if (th == null) {
                maf.a(a2, str2, str3);
            } else {
                maf.a(a2, str2, str3, th);
            }
        }
    }

    public static void d(String str, String str2, String str3) {
        if (d) {
            d(str, str2, str3, (Throwable) null);
        }
    }

    public static void d(String str, String str2) {
        if (d) {
            d(str, str2, (Throwable) null, false);
        }
    }

    private static String a(String str) {
        if (str == null) {
            return "HmsMapKit";
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return "HmsMapKit_" + str + "_" + (stackTrace.length > 5 ? stackTrace[5].getLineNumber() : 0);
    }

    private LogM() {
    }
}
