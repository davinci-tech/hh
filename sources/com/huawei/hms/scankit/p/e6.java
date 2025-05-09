package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public final class e6 extends p4 {
    private final byte[] c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;

    public e6(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        super(i5, i6);
        if (i3 + i5 > i || i4 + i6 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.c = bArr;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = i4;
        if (z) {
            a(i5, i6);
        }
    }

    @Override // com.huawei.hms.scankit.p.p4
    public byte[] a(int i, byte[] bArr) {
        if (i < 0 || i >= a()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i);
        }
        int c = c();
        if (bArr == null || bArr.length < c) {
            bArr = new byte[c];
        }
        int i2 = this.g;
        System.arraycopy(this.c, ((i + i2) * this.d) + this.f, bArr, 0, c);
        return bArr;
    }

    @Override // com.huawei.hms.scankit.p.p4
    public byte[] b() {
        int c = c();
        int a2 = a();
        int i = this.d;
        if (c == i && a2 == this.e) {
            return this.c;
        }
        int i2 = c * a2;
        byte[] bArr = new byte[i2];
        int i3 = (this.g * i) + this.f;
        if (c == i) {
            try {
                System.arraycopy(this.c, i3, bArr, 0, i2);
                return bArr;
            } catch (ArrayIndexOutOfBoundsException | Exception unused) {
                return null;
            }
        }
        for (int i4 = 0; i4 < a2; i4++) {
            try {
                System.arraycopy(this.c, i3, bArr, i4 * c, c);
                i3 += this.d;
            } catch (ArrayIndexOutOfBoundsException | Exception unused2) {
                return null;
            }
        }
        return bArr;
    }

    @Override // com.huawei.hms.scankit.p.p4
    public p4 a(int i, int i2, int i3, int i4) {
        return new e6(this.c, this.d, this.e, this.f + i, this.g + i2, i3, i4, false);
    }

    private void a(int i, int i2) {
        byte[] bArr = this.c;
        int i3 = (this.g * this.d) + this.f;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = i / 2;
            int i6 = (i3 + i) - 1;
            int i7 = i3;
            while (i7 < i5 + i3) {
                if (w7.a(bArr, i7) && w7.a(bArr, i6)) {
                    byte b = bArr[i7];
                    bArr[i7] = bArr[i6];
                    bArr[i6] = b;
                }
                i7++;
                i6--;
            }
            i4++;
            i3 += this.d;
        }
    }
}
