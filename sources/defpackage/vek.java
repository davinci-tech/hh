package defpackage;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;

/* loaded from: classes7.dex */
public class vek {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f17693a = vbj.d(new SecureRandom(), 256);

    public static byte[] b(CipherSuite cipherSuite, SecretKey secretKey, SecretKey secretKey2, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = new byte[bArr2.length + Math.max(cipherSuite.getMacMessageBlockLength(), 256)];
        int recordIvLength = cipherSuite.getRecordIvLength();
        Cipher threadLocalCipher = cipherSuite.getThreadLocalCipher();
        int i = 0;
        threadLocalCipher.init(2, secretKey, new IvParameterSpec(bArr2, 0, recordIvLength));
        int doFinal = threadLocalCipher.doFinal(bArr2, recordIvLength, bArr2.length - recordIvLength, bArr3);
        System.arraycopy(f17693a, 0, bArr3, doFinal, cipherSuite.getMacMessageBlockLength());
        int macLength = cipherSuite.getMacLength();
        int i2 = bArr3[doFinal - 1] & 255;
        int i3 = (doFinal - macLength) - 1;
        int i4 = i3 - i2;
        if (i4 < 0) {
            i4 = i3;
            i2 = 0;
        }
        if (a(i2, bArr3, i4 + macLength)) {
            i = i2;
            i3 = i4;
        }
        int length = bArr.length;
        bArr[length - 2] = (byte) ((i3 >> 8) & 255);
        bArr[length - 1] = (byte) (i3 & 255);
        MessageDigest threadLocalMacMessageDigest = cipherSuite.getThreadLocalMacMessageDigest();
        threadLocalMacMessageDigest.reset();
        byte[] c = c(cipherSuite.getThreadLocalMac(), secretKey2, bArr, bArr3, i3);
        int macMessageLengthBytes = cipherSuite.getMacMessageLengthBytes();
        int macMessageBlockLength = cipherSuite.getMacMessageBlockLength();
        int length2 = bArr.length + i3 + macMessageLengthBytes;
        threadLocalMacMessageDigest.update(bArr3, i3, ((((i + length2) / macMessageBlockLength) - (length2 / macMessageBlockLength)) * macMessageBlockLength) + 1);
        threadLocalMacMessageDigest.reset();
        byte[] copyOfRange = Arrays.copyOfRange(bArr3, i3, macLength + i3);
        boolean isEqual = MessageDigest.isEqual(copyOfRange, c);
        vbj.b(c);
        vbj.b(copyOfRange);
        byte[] copyOf = isEqual ? Arrays.copyOf(bArr3, i3) : null;
        vbj.b(bArr3);
        if (isEqual) {
            return copyOf;
        }
        throw new veq();
    }

    public static byte[] d(CipherSuite cipherSuite, SecretKey secretKey, SecretKey secretKey2, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        vbo vboVar = new vbo(bArr2.length + cipherSuite.getMacLength() + cipherSuite.getRecordIvLength(), true);
        vboVar.d(bArr2);
        byte[] c = c(cipherSuite.getThreadLocalMac(), secretKey2, bArr, bArr2, bArr2.length);
        vboVar.d(c);
        vbj.b(c);
        int length = bArr2.length;
        int macLength = cipherSuite.getMacLength();
        int recordIvLength = cipherSuite.getRecordIvLength();
        int i = ((length + macLength) + 1) % recordIvLength;
        int i2 = i > 0 ? recordIvLength - i : 0;
        byte[] bArr3 = new byte[i2 + 1];
        Arrays.fill(bArr3, (byte) i2);
        vboVar.d(bArr3);
        vbj.b(bArr3);
        Cipher threadLocalCipher = cipherSuite.getThreadLocalCipher();
        threadLocalCipher.init(1, secretKey);
        byte[] iv = threadLocalCipher.getIV();
        byte[] c2 = vboVar.c();
        vboVar.a();
        byte[] copyOf = Arrays.copyOf(iv, iv.length + c2.length);
        threadLocalCipher.doFinal(c2, 0, c2.length, copyOf, iv.length);
        return copyOf;
    }

    public static byte[] c(Mac mac, SecretKey secretKey, byte[] bArr, byte[] bArr2, int i) throws InvalidKeyException {
        mac.init(secretKey);
        mac.update(bArr);
        mac.update(bArr2, 0, i);
        byte[] doFinal = mac.doFinal();
        mac.reset();
        return doFinal;
    }

    public static boolean a(int i, byte[] bArr, int i2) {
        if (bArr.length < i2 + 257) {
            throw new IllegalArgumentException("data must contain 257 bytes from offset on!");
        }
        byte b = (byte) i;
        byte b2 = 0;
        for (int i3 = 0; i3 <= i; i3++) {
            b2 = (byte) (b2 | (bArr[i2 + i3] ^ b));
        }
        byte b3 = 0;
        for (int i4 = i + 1; i4 < 256; i4++) {
            b3 = (byte) (b3 | (bArr[i2 + i4] ^ b));
        }
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] ^ b3);
        return b2 == 0;
    }
}
