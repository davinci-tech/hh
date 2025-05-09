package com.huawei.hms.hatool;

/* loaded from: classes4.dex */
public abstract class z {
    public static boolean k(String str, String str2) {
        j0 c = c(str, str2);
        return c != null && c.h();
    }

    public static String j(String str, String str2) {
        j0 c = c(str, str2);
        return c != null ? c.c() : "";
    }

    public static boolean i(String str, String str2) {
        s0 a2;
        l1 a3 = s.c().a(str);
        if (a3 == null || (a2 = a3.a(str2)) == null) {
            return false;
        }
        return a2.e();
    }

    public static boolean h(String str, String str2) {
        j0 c = c(str, str2);
        return c != null && c.g();
    }

    public static String g(String str, String str2) {
        j0 c = c(str, str2);
        return c != null ? c.d() : "";
    }

    public static boolean f(String str, String str2) {
        s0 a2;
        l1 a3 = s.c().a(str);
        if (a3 == null || (a2 = a3.a(str2)) == null) {
            return false;
        }
        return a2.c();
    }

    public static boolean e(String str, String str2) {
        j0 c = c(str, str2);
        return c != null && c.f();
    }

    public static String d(String str, String str2) {
        j0 c = c(str, str2);
        return c != null ? c.b() : "";
    }

    private static j0 c(String str, String str2) {
        s0 a2;
        l1 a3 = s.c().a(str);
        if (a3 == null || (a2 = a3.a(str2)) == null) {
            return null;
        }
        return a2.j();
    }

    public static boolean b(String str, String str2) {
        j0 c = c(str, str2);
        return c != null && c.e();
    }

    public static String a(String str, String str2) {
        j0 c = c(str, str2);
        return c != null ? c.a() : "";
    }
}
