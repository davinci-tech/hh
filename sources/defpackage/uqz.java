package defpackage;

import java.security.SecureRandom;
import net.lingala.zip4j.crypto.Encrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.AesKeyStrength;

/* loaded from: classes7.dex */
public class uqz implements Encrypter {

    /* renamed from: a, reason: collision with root package name */
    private urj f17510a;
    private final byte[] b;
    private boolean c;
    private final byte[] d;
    private byte[] e;
    private byte[] f;
    private urf h;
    private final SecureRandom i = new SecureRandom();
    private int g = 1;
    private int j = 0;

    public uqz(char[] cArr, AesKeyStrength aesKeyStrength, boolean z) throws ZipException {
        if (cArr == null || cArr.length == 0) {
            throw new ZipException("input password is empty or null");
        }
        if (aesKeyStrength != AesKeyStrength.KEY_STRENGTH_128 && aesKeyStrength != AesKeyStrength.KEY_STRENGTH_256) {
            throw new ZipException("Invalid AES key strength");
        }
        this.c = false;
        this.d = new byte[16];
        this.b = new byte[16];
        c(cArr, aesKeyStrength, z);
    }

    private void c(char[] cArr, AesKeyStrength aesKeyStrength, boolean z) throws ZipException {
        byte[] a2 = a(aesKeyStrength.getSaltLength());
        this.f = a2;
        byte[] a3 = urd.a(a2, cArr, aesKeyStrength, z);
        this.e = urd.b(a3, aesKeyStrength);
        this.f17510a = urd.a(a3, aesKeyStrength);
        this.h = urd.e(a3, aesKeyStrength);
    }

    @Override // net.lingala.zip4j.crypto.Encrypter
    public int encryptData(byte[] bArr) throws ZipException {
        if (bArr == null) {
            throw new ZipException("input bytes are null, cannot perform AES encryption");
        }
        return encryptData(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.crypto.Encrypter
    public int encryptData(byte[] bArr, int i, int i2) throws ZipException {
        int i3;
        if (this.c) {
            throw new ZipException("AES Encrypter is in finished state (A non 16 byte block has already been passed to encrypter)");
        }
        if (i2 % 16 != 0) {
            this.c = true;
        }
        int i4 = i;
        while (true) {
            int i5 = i + i2;
            if (i4 >= i5) {
                return i2;
            }
            int i6 = i4 + 16;
            this.j = i6 <= i5 ? 16 : i5 - i4;
            urd.e(this.b, this.g);
            this.f17510a.a(this.b, this.d);
            int i7 = 0;
            while (true) {
                i3 = this.j;
                if (i7 < i3) {
                    int i8 = i4 + i7;
                    bArr[i8] = (byte) (bArr[i8] ^ this.d[i7]);
                    i7++;
                }
            }
            this.h.a(bArr, i4, i3);
            this.g++;
            i4 = i6;
        }
    }

    private byte[] a(int i) throws ZipException {
        if (i != 8 && i != 16) {
            throw new ZipException("invalid salt size, cannot generate salt");
        }
        int i2 = i == 8 ? 2 : 4;
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i2; i3++) {
            int nextInt = this.i.nextInt();
            int i4 = i3 * 4;
            bArr[i4] = (byte) (nextInt >> 24);
            bArr[i4 + 1] = (byte) (nextInt >> 16);
            bArr[i4 + 2] = (byte) (nextInt >> 8);
            bArr[i4 + 3] = (byte) nextInt;
        }
        return bArr;
    }

    public byte[] a() {
        byte[] bArr = new byte[10];
        System.arraycopy(this.h.b(), 0, bArr, 0, 10);
        return bArr;
    }

    public byte[] b() {
        return this.e;
    }

    public byte[] e() {
        return this.f;
    }
}
