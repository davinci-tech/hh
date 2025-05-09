package defpackage;

/* loaded from: classes3.dex */
public class bmd {
    public static void a(String str, int i, String str2) {
        d(str, i, 0L, str2);
    }

    public static void d(String str, int i, long j, String str2) {
        snu.e().applyOrReleaseResource(true, str, i, j, str2);
    }

    public static void b(String str, int i, String str2) {
        snu.e().applyOrReleaseResource(false, str, i, 0L, str2);
    }
}
