package defpackage;

import health.compact.a.SharedPreferenceManager;

/* loaded from: classes6.dex */
public class nsd {
    public static void a(boolean z) {
        SharedPreferenceManager.e("ecg_sharedpreference_start_msg", "showRedDot", z);
    }

    public static boolean d() {
        return SharedPreferenceManager.a("ecg_sharedpreference_start_msg", "showRedDot", false);
    }

    public static void e(boolean z) {
        SharedPreferenceManager.e(Integer.toString(10052), "NormalItemHolder_newDataDotExists", z);
    }

    public static void d(String str, long j) {
        SharedPreferenceManager.e("loginTime", str, j);
    }

    public static long c(String str) {
        return SharedPreferenceManager.b("loginTime", str, 0L);
    }

    public static boolean a(String str) {
        return SharedPreferenceManager.a("loginTime", str, true);
    }

    public static void e(String str, boolean z) {
        SharedPreferenceManager.e("loginTime", str, z);
    }

    public static int d(String str) {
        return SharedPreferenceManager.a("loginTime", str, 0);
    }

    public static void e(String str, int i) {
        SharedPreferenceManager.b("loginTime", str, i);
    }

    public static String e() {
        return SharedPreferenceManager.e("MAIN_NEED_NOVA_FLAG", "MAIN_NEED_NOVA_FLAG", "");
    }

    public static void f(String str) {
        SharedPreferenceManager.c("MAIN_NEED_NOVA_FLAG", "MAIN_NEED_NOVA_FLAG", str);
    }

    public static void b(String str) {
        SharedPreferenceManager.e(String.valueOf(20006), "THIRD_APP_FLAG" + str, true);
    }

    public static boolean e(String str) {
        return SharedPreferenceManager.a(String.valueOf(20006), "THIRD_APP_FLAG" + str, false);
    }
}
