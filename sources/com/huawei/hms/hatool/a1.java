package com.huawei.hms.hatool;

import java.util.Map;

/* loaded from: classes4.dex */
public abstract class a1 {
    public static String o(String str, String str2) {
        s0 h = h(str, str2);
        return h != null ? h.p() : "";
    }

    public static String n(String str, String str2) {
        s0 h = h(str, str2);
        return h != null ? h.m() : "";
    }

    public static String m(String str, String str2) {
        s0 h = h(str, str2);
        return h != null ? h.q() : "";
    }

    public static String l(String str, String str2) {
        s0 h = h(str, str2);
        return h != null ? h.o() : "";
    }

    public static int k(String str, String str2) {
        s0 h = h(str, str2);
        if (h != null) {
            return h.b();
        }
        return 10;
    }

    public static long j(String str, String str2) {
        s0 h = h(str, str2);
        if (h != null) {
            return h.l();
        }
        return 0L;
    }

    public static Map<String, String> i(String str, String str2) {
        s0 h = h(str, str2);
        if (h != null) {
            return h.k();
        }
        return null;
    }

    private static s0 h(String str, String str2) {
        l1 a2 = s.c().a(str);
        if (a2 == null) {
            return null;
        }
        if (!"alltype".equals(str2)) {
            return a2.a(str2);
        }
        s0 a3 = a2.a("oper");
        return a3 == null ? a2.a("maint") : a3;
    }

    public static String g(String str, String str2) {
        s0 h = h(str, str2);
        return h != null ? h.n() : "";
    }

    public static String f(String str, String str2) {
        s0 h = h(str, str2);
        return h != null ? h.h() : "";
    }

    public static boolean e(String str, String str2) {
        s0 h = h(str, str2);
        if (h != null) {
            return h.i();
        }
        return false;
    }

    public static String d(String str, String str2) {
        s0 h = h(str, str2);
        return h != null ? h.f() : "";
    }

    public static boolean c(String str, String str2) {
        s0 h = h(str, str2);
        if (h != null) {
            return h.g();
        }
        return true;
    }

    public static int b(String str, String str2) {
        s0 h = h(str, str2);
        if (h != null) {
            return h.d();
        }
        return 7;
    }

    public static boolean a(String str, String str2) {
        s0 h = h(str, str2);
        if (h != null) {
            return h.a();
        }
        return true;
    }

    public static void a(String str, String str2, long j) {
        s0 h = h(str, str2);
        if (h != null) {
            h.a(j);
        }
    }
}
