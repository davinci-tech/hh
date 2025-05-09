package defpackage;

/* loaded from: classes8.dex */
public class aal {

    /* renamed from: a, reason: collision with root package name */
    private static int f128a = 0;
    private static int b = 0;
    private static boolean c = false;
    private static int e;

    public static int a() {
        return e;
    }

    public static int b() {
        return b;
    }

    public static void c() {
        b = 0;
    }

    public static int d() {
        return f128a;
    }

    public static boolean e() {
        return c;
    }

    public static void a(int i) {
        abd.c("CloudSyncCompatibility", "setHisyncOldVersion, oldVersion = " + i);
        f128a = i;
    }

    public static void b(int i) {
        b = i;
    }

    public static void c(int i) {
        e = i;
    }

    public static void d(boolean z) {
        abd.c("CloudSyncCompatibility", "IsRecycleProcess = " + z);
        c = z;
    }
}
