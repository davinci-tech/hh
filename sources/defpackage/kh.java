package defpackage;

import com.alipay.sdk.m.j.c;

/* loaded from: classes7.dex */
public class kh {

    /* renamed from: a, reason: collision with root package name */
    public static String f14363a = null;
    public static boolean b = false;

    public static void a(String str) {
        f14363a = str;
    }

    public static String b() {
        c b2 = c.b(c.PARAMS_ERROR.b());
        return c(b2.b(), b2.a(), "");
    }

    public static boolean c() {
        return b;
    }

    public static String d() {
        c b2 = c.b(c.DOUBLE_REQUEST.b());
        return c(b2.b(), b2.a(), "");
    }

    public static String e() {
        return f14363a;
    }

    public static void a(boolean z) {
        b = z;
    }

    public static String a() {
        c b2 = c.b(c.CANCELED.b());
        return c(b2.b(), b2.a(), "");
    }

    public static String c(int i, String str, String str2) {
        return "resultStatus={" + i + "};memo={" + str + "};result={" + str2 + "}";
    }
}
