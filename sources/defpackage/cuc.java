package defpackage;

import health.compact.a.WhiteBoxManager;

/* loaded from: classes3.dex */
public class cuc {
    public static byte[] e(byte[] bArr) {
        return WhiteBoxManager.d().a(bArr);
    }

    public static String c() {
        WhiteBoxManager d = WhiteBoxManager.d();
        return d.d(1, 22) + d.d(1, 1022) + d.d(1, 2022);
    }

    public static byte[] d(int i, byte[] bArr, byte[] bArr2) {
        byte[] c;
        try {
        } catch (UnsatisfiedLinkError e) {
            cpw.e(false, "WhiteBoxUtil", "whiteBoxEncrypt UnsatisfiedLinkError: ", e.getMessage());
        }
        if (i == 100) {
            c = WhiteBoxManager.d().c(2, bArr, bArr2);
        } else {
            if (i != 101) {
                if (i == 110) {
                    c = WhiteBoxManager.d().c(0, bArr, bArr2);
                }
                return null;
            }
            c = WhiteBoxManager.d().c(1, bArr, bArr2);
        }
        return c;
    }
}
