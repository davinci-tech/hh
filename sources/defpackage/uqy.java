package defpackage;

import java.util.Arrays;
import net.lingala.zip4j.crypto.Decrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.AesKeyStrength;

/* loaded from: classes10.dex */
public class uqy implements Decrypter {

    /* renamed from: a, reason: collision with root package name */
    private urj f17509a;
    private urf e;
    private int b = 1;
    private byte[] d = new byte[16];
    private byte[] c = new byte[16];

    public uqy(use useVar, char[] cArr, byte[] bArr, byte[] bArr2, boolean z) throws ZipException {
        e(bArr, bArr2, cArr, useVar, z);
    }

    private void e(byte[] bArr, byte[] bArr2, char[] cArr, use useVar, boolean z) throws ZipException {
        if (cArr == null || cArr.length <= 0) {
            throw new ZipException("empty or null password provided for AES decryption", ZipException.Type.WRONG_PASSWORD);
        }
        AesKeyStrength d = useVar.d();
        byte[] a2 = urd.a(bArr, cArr, d, z);
        if (!Arrays.equals(bArr2, urd.b(a2, d))) {
            throw new ZipException("Wrong Password", ZipException.Type.WRONG_PASSWORD);
        }
        this.f17509a = urd.a(a2, d);
        this.e = urd.e(a2, d);
    }

    @Override // net.lingala.zip4j.crypto.Decrypter
    public int decryptData(byte[] bArr, int i, int i2) throws ZipException {
        int i3 = i;
        while (true) {
            int i4 = i + i2;
            if (i3 >= i4) {
                return i2;
            }
            int i5 = i3 + 16;
            int i6 = i5 <= i4 ? 16 : i4 - i3;
            this.e.a(bArr, i3, i6);
            urd.e(this.d, this.b);
            this.f17509a.a(this.d, this.c);
            for (int i7 = 0; i7 < i6; i7++) {
                int i8 = i3 + i7;
                bArr[i8] = (byte) (bArr[i8] ^ this.c[i7]);
            }
            this.b++;
            i3 = i5;
        }
    }

    public byte[] c(int i) {
        return this.e.e(i);
    }
}
