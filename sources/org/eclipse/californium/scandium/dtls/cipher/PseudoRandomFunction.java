package org.eclipse.californium.scandium.dtls.cipher;

import defpackage.vbj;
import defpackage.vbo;
import defpackage.vfh;
import java.security.InvalidKeyException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import org.eclipse.californium.elements.util.StandardCharsets;

/* loaded from: classes7.dex */
public final class PseudoRandomFunction {

    public enum Label {
        MASTER_SECRET_LABEL("master secret", 48),
        KEY_EXPANSION_LABEL("key expansion", 128),
        CLIENT_FINISHED_LABEL("client finished", 12),
        SERVER_FINISHED_LABEL("server finished", 12),
        EXTENDED_MASTER_SECRET_LABEL("extended master secret", 48);

        private final byte[] bytesValue;
        private final int length;
        private final String value;

        Label(String str, int i) {
            this.value = str;
            this.bytesValue = str.getBytes(StandardCharsets.UTF_8);
            this.length = i;
        }

        public String value() {
            return this.value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] getBytes() {
            return this.bytesValue;
        }

        public int length() {
            return this.length;
        }
    }

    static byte[] e(Mac mac, SecretKey secretKey, byte[] bArr, byte[] bArr2, int i) {
        try {
            mac.init(secretKey);
            byte[] a2 = a(mac, bArr, bArr2, i);
            mac.reset();
            return a2;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException("Cannot run Pseudo Random Function with invalid key", e);
        }
    }

    public static final byte[] a(Mac mac, SecretKey secretKey, Label label, byte[] bArr) {
        return e(mac, secretKey, label.getBytes(), bArr, label.length());
    }

    public static final byte[] e(Mac mac, SecretKey secretKey, Label label, byte[] bArr, int i) {
        return e(mac, secretKey, label.getBytes(), bArr, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0028, code lost:
    
        r6.doFinal(r1, 0);
        java.lang.System.arraycopy(r1, 0, r2, r7, r9 - r7);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final byte[] a(javax.crypto.Mac r6, byte[] r7, byte[] r8, int r9) {
        /*
            int r0 = r6.getMacLength()
            int r1 = r7.length
            int r1 = r1 + r0
            int r2 = r8.length
            int r1 = r1 + r2
            byte[] r1 = new byte[r1]
            byte[] r2 = new byte[r9]
            int r3 = r7.length     // Catch: javax.crypto.ShortBufferException -> L3b
            r4 = 0
            java.lang.System.arraycopy(r7, r4, r1, r0, r3)     // Catch: javax.crypto.ShortBufferException -> L3b
            int r3 = r7.length     // Catch: javax.crypto.ShortBufferException -> L3b
            int r3 = r3 + r0
            int r5 = r8.length     // Catch: javax.crypto.ShortBufferException -> L3b
            java.lang.System.arraycopy(r8, r4, r1, r3, r5)     // Catch: javax.crypto.ShortBufferException -> L3b
            r6.update(r7)     // Catch: javax.crypto.ShortBufferException -> L3b
            r6.update(r8)     // Catch: javax.crypto.ShortBufferException -> L3b
            r7 = r4
        L1e:
            r6.doFinal(r1, r4)     // Catch: javax.crypto.ShortBufferException -> L3b
            r6.update(r1)     // Catch: javax.crypto.ShortBufferException -> L3b
            int r8 = r7 + r0
            if (r8 <= r9) goto L30
            r6.doFinal(r1, r4)     // Catch: javax.crypto.ShortBufferException -> L3b
            int r9 = r9 - r7
            java.lang.System.arraycopy(r1, r4, r2, r7, r9)     // Catch: javax.crypto.ShortBufferException -> L3b
            goto L3f
        L30:
            r6.doFinal(r2, r7)     // Catch: javax.crypto.ShortBufferException -> L3b
            if (r8 != r9) goto L36
            goto L3f
        L36:
            r6.update(r1, r4, r0)     // Catch: javax.crypto.ShortBufferException -> L3b
            r7 = r8
            goto L1e
        L3b:
            r6 = move-exception
            r6.printStackTrace()
        L3f:
            defpackage.vbj.b(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.dtls.cipher.PseudoRandomFunction.a(javax.crypto.Mac, byte[], byte[], int):byte[]");
    }

    public static SecretKey b(Mac mac, SecretKey secretKey, byte[] bArr, boolean z) {
        byte[] a2 = a(mac, secretKey, z ? Label.EXTENDED_MASTER_SECRET_LABEL : Label.MASTER_SECRET_LABEL, bArr);
        SecretKey e = vfh.e(a2, "MAC");
        vbj.b(a2);
        return e;
    }

    public static SecretKey a(SecretKey secretKey, SecretKey secretKey2) {
        byte[] encoded = secretKey2.getEncoded();
        int length = encoded.length;
        byte[] encoded2 = secretKey != null ? secretKey.getEncoded() : new byte[length];
        vbo vboVar = new vbo(encoded2.length + length + 4, true);
        vboVar.d(encoded2, 16);
        vboVar.d(encoded, 16);
        byte[] c = vboVar.c();
        vboVar.a();
        SecretKey e = vfh.e(c, "MAC");
        vbj.b(encoded);
        vbj.b(encoded2);
        vbj.b(c);
        return e;
    }
}
