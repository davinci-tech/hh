package defpackage;

import net.lingala.zip4j.crypto.PBKDF2.PRF;

/* loaded from: classes7.dex */
public class ure {

    /* renamed from: a, reason: collision with root package name */
    private urh f17512a;
    private PRF c;

    public ure(urh urhVar) {
        this(urhVar, null);
    }

    public ure(urh urhVar, PRF prf) {
        this.f17512a = urhVar;
        this.c = prf;
    }

    public byte[] c(char[] cArr, int i, boolean z) {
        cArr.getClass();
        d(utd.e(cArr, z));
        if (i == 0) {
            i = this.c.getHLen();
        }
        return a(this.c, this.f17512a.b(), this.f17512a.e(), i);
    }

    private void d(byte[] bArr) {
        if (this.c == null) {
            this.c = new urf(this.f17512a.a());
        }
        this.c.init(bArr);
    }

    private byte[] a(PRF prf, byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr == null ? new byte[0] : bArr;
        int hLen = prf.getHLen();
        int c = c(i2, hLen);
        byte[] bArr3 = new byte[c * hLen];
        int i3 = 0;
        for (int i4 = 1; i4 <= c; i4++) {
            e(bArr3, i3, prf, bArr2, i, i4);
            i3 += hLen;
        }
        if (i2 - ((c - 1) * hLen) >= hLen) {
            return bArr3;
        }
        byte[] bArr4 = new byte[i2];
        System.arraycopy(bArr3, 0, bArr4, 0, i2);
        return bArr4;
    }

    private int c(int i, int i2) {
        return (i / i2) + (i % i2 > 0 ? 1 : 0);
    }

    private void e(byte[] bArr, int i, PRF prf, byte[] bArr2, int i2, int i3) {
        int hLen = prf.getHLen();
        byte[] bArr3 = new byte[hLen];
        byte[] bArr4 = new byte[bArr2.length + 4];
        System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
        e(bArr4, bArr2.length, i3);
        for (int i4 = 0; i4 < i2; i4++) {
            bArr4 = prf.doFinal(bArr4);
            a(bArr3, bArr4);
        }
        System.arraycopy(bArr3, 0, bArr, i, hLen);
    }

    private void a(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    protected void e(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 / 16777216);
        bArr[i + 1] = (byte) (i2 / 65536);
        bArr[i + 2] = (byte) (i2 / 256);
        bArr[i + 3] = (byte) i2;
    }
}
