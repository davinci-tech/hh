package com.huawei.hianalytics.core.log;

/* loaded from: classes4.dex */
public class HiLog {

    /* renamed from: a, reason: collision with root package name */
    public static LogAdapter f3841a = null;
    public static boolean b = false;
    public static int c = 6;
    public static String d = "";

    /* loaded from: classes8.dex */
    public interface ErrorCode {
        public static final String NE002 = "NE-002";
        public static final String NE003 = "NE-003";
        public static final String NE004 = "NE-004";
        public static final String NE005 = "NE-005";
        public static final String NE006 = "NE-006";
    }

    public static void i(String str, String str2) {
        if (!a(4) || str == null || str2 == null) {
            return;
        }
        f3841a.println(4, str, str2);
    }

    public static void si(String str, String str2) {
        if (a(str, str2)) {
            if (b && !a(6)) {
                return;
            }
            f3841a.println(4, str, str2);
        }
    }

    public static void sw(String str, String str2) {
        if (a(str, str2)) {
            if (b && !a(7)) {
                return;
            }
            f3841a.println(5, str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (!a(5) || str == null || str2 == null) {
            return;
        }
        f3841a.println(5, str, str2);
    }

    public static void setLogAdapter(LogAdapter logAdapter) {
        if (f3841a == null) {
            f3841a = logAdapter;
        }
        if (b) {
            init(c, d);
        }
    }

    public static boolean isDebugEnable() {
        return a(3);
    }

    public static void init(int i, String str) {
        b = true;
        c = i;
        d = str;
        LogAdapter logAdapter = f3841a;
        if (logAdapter != null) {
            logAdapter.init(i, str);
        }
    }

    public static void e(String str, String str2) {
        if (a(str, str2)) {
            f3841a.println(6, str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (!isDebugEnable() || str == null || str2 == null) {
            return;
        }
        f3841a.println(3, str, str2);
    }

    public static boolean a(String str, String str2) {
        return (f3841a == null || str == null || str2 == null) ? false : true;
    }

    public static boolean a(int i) {
        LogAdapter logAdapter = f3841a;
        if (logAdapter != null) {
            return logAdapter.isLoggable(i);
        }
        return false;
    }
}
