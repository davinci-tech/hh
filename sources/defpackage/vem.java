package defpackage;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import org.eclipse.californium.scandium.dtls.cipher.CCMBlockCipher;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class vem {
    private static final Logger d = vha.a((Class<?>) vem.class);

    public static final boolean d(String str) {
        return "AES/CCM/NoPadding".equalsIgnoreCase(str) || "AES/CCM".equalsIgnoreCase(str);
    }

    public static final boolean d(String str, int i) {
        int i2;
        try {
            i2 = Cipher.getMaxAllowedKeyLength(str);
        } catch (NoSuchAlgorithmException unused) {
            i2 = 0;
        }
        if (i2 == 0) {
            d.debug("{} is not supported!", str);
        } else if (i2 == Integer.MAX_VALUE) {
            d.debug("{} is not restricted!", str);
        } else {
            d.debug("{} is restricted to {} bits.", str, Integer.valueOf(i2));
        }
        return i * 8 <= i2;
    }

    public static final byte[] e(CipherSuite cipherSuite, SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2) throws GeneralSecurityException {
        if (d(cipherSuite.getTransformation())) {
            return CCMBlockCipher.b(secretKey, bArr, bArr2, bArr3, i, i2, cipherSuite.getMacLength());
        }
        return b(cipherSuite, secretKey, bArr, bArr2, bArr3, i, i2);
    }

    public static final byte[] e(CipherSuite cipherSuite, SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (d(cipherSuite.getTransformation())) {
            return CCMBlockCipher.e(cipherSuite.getRecordIvLength(), secretKey, bArr, bArr2, bArr3, cipherSuite.getMacLength());
        }
        return b(cipherSuite.getRecordIvLength(), cipherSuite, secretKey, bArr, bArr2, bArr3);
    }

    private static final byte[] b(CipherSuite cipherSuite, SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2) throws GeneralSecurityException {
        Cipher threadLocalCipher = cipherSuite.getThreadLocalCipher();
        threadLocalCipher.init(2, secretKey, new GCMParameterSpec(cipherSuite.getMacLength() * 8, bArr));
        threadLocalCipher.updateAAD(bArr2);
        try {
            return threadLocalCipher.doFinal(bArr3, i, i2);
        } catch (AEADBadTagException e) {
            throw new veq(e.getMessage());
        }
    }

    private static final byte[] b(int i, CipherSuite cipherSuite, SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        Cipher threadLocalCipher = cipherSuite.getThreadLocalCipher();
        threadLocalCipher.init(1, secretKey, new GCMParameterSpec(cipherSuite.getMacLength() * 8, bArr));
        threadLocalCipher.updateAAD(bArr2);
        byte[] bArr4 = new byte[threadLocalCipher.getOutputSize(bArr3.length) + i];
        threadLocalCipher.doFinal(bArr3, 0, bArr3.length, bArr4, i);
        return bArr4;
    }
}
