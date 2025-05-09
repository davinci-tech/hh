package defpackage;

import java.security.SecureRandom;
import net.lingala.zip4j.crypto.Encrypter;
import net.lingala.zip4j.exception.ZipException;

/* loaded from: classes7.dex */
public class urm implements Encrypter {
    private final uri d = new uri();
    private byte[] e;

    public urm(char[] cArr, long j, boolean z) throws ZipException {
        a(cArr, j, z);
    }

    private void a(char[] cArr, long j, boolean z) throws ZipException {
        if (cArr == null || cArr.length <= 0) {
            throw new ZipException("input password is null or empty, cannot initialize standard encrypter");
        }
        this.d.c(cArr, z);
        this.e = e();
        this.d.c(cArr, z);
        byte[] bArr = this.e;
        bArr[11] = (byte) (j >>> 24);
        bArr[10] = (byte) (j >>> 16);
        encryptData(bArr);
    }

    @Override // net.lingala.zip4j.crypto.Encrypter
    public int encryptData(byte[] bArr) throws ZipException {
        bArr.getClass();
        return encryptData(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.crypto.Encrypter
    public int encryptData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 < 0) {
            throw new ZipException("invalid length specified to decrpyt data");
        }
        for (int i3 = i; i3 < i + i2; i3++) {
            bArr[i3] = b(bArr[i3]);
        }
        return i2;
    }

    protected byte b(byte b) {
        byte b2 = (byte) ((this.d.b() & 255) ^ b);
        this.d.e(b);
        return b2;
    }

    protected byte[] e() {
        byte[] bArr = new byte[12];
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 12; i++) {
            bArr[i] = b((byte) secureRandom.nextInt(256));
        }
        return bArr;
    }

    public byte[] b() {
        return this.e;
    }
}
