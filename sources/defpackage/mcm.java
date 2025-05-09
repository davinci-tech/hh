package defpackage;

/* loaded from: classes6.dex */
public final class mcm {
    public static int e(String str, int i) {
        if (mcq.a(str)) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }
}
