package defpackage;

import javax.crypto.SecretKey;

/* loaded from: classes10.dex */
public class vfg {
    public static void a(vbo vboVar, SecretKey secretKey) {
        if (secretKey == null || vfh.b(secretKey)) {
            vboVar.d((byte[]) null, 8);
        } else {
            byte[] encoded = secretKey.getEncoded();
            vboVar.d(encoded, 8);
            vbj.b(encoded);
            vbt.d(vboVar, secretKey.getAlgorithm(), 8);
        }
    }

    public static SecretKey e(vbn vbnVar) {
        byte[] h = vbnVar.h(8);
        if (h == null) {
            return null;
        }
        if (h.length == 0) {
            throw new IllegalArgumentException("key must not be empty!");
        }
        try {
            String c = vbt.c(vbnVar, 8);
            if (c == null) {
                throw new IllegalArgumentException("key must have algorithm!");
            }
            return vfh.e(h, c.intern());
        } finally {
            vbj.b(h);
        }
    }

    public static void d(vbo vboVar, vff vffVar) {
        if (vffVar == null || vfh.d(vffVar)) {
            vboVar.b(0, 8);
        } else {
            vboVar.b(vffVar.a(), 8);
            vffVar.a(vboVar);
        }
    }

    public static vff d(vbn vbnVar) {
        byte[] h = vbnVar.h(8);
        if (h == null) {
            return null;
        }
        vff a2 = vfh.a(h);
        vbj.b(h);
        return a2;
    }
}
