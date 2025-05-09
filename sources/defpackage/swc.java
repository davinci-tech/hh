package defpackage;

/* loaded from: classes7.dex */
public class swc {
    private static volatile swc b;
    private static final byte[] d = new byte[0];

    public String c(String str, String str2) {
        return str2;
    }

    public static swc c() {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new swc();
                }
            }
        }
        return b;
    }
}
