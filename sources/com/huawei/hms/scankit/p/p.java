package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public final class p {

    /* renamed from: a, reason: collision with root package name */
    private final o f5849a;
    private s b;

    public p(o oVar) {
        if (oVar == null) {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        }
        this.f5849a = oVar;
    }

    public r a(int i, int i2) throws a {
        int i3;
        int e = e();
        if (e < 45) {
            throw a.a();
        }
        r rVar = new r(e);
        byte[] bArr = new byte[e];
        a().c().a(i, bArr);
        int[] iArr = new int[e];
        int[] iArr2 = new int[e];
        int i4 = bArr[0] & 255;
        iArr[0] = i4;
        iArr2[0] = i4 * i4;
        for (int i5 = 1; i5 < e; i5++) {
            iArr[i5] = iArr[i5 - 1] + (bArr[i5] & 255);
        }
        if (i2 != 0) {
            return a(45, e, iArr, iArr2, bArr, 22);
        }
        int i6 = 23;
        while (true) {
            i3 = e - 22;
            if (i6 >= i3) {
                break;
            }
            if ((bArr[i6] & 255) + 5 < (iArr[i6 + 22] - iArr[i6 - 23]) / 45) {
                rVar.g(i6);
            }
            i6++;
        }
        if (rVar.b(23)) {
            rVar.c(0, 23);
        }
        if (rVar.b(e - 23)) {
            rVar.c(i3, e);
        }
        return rVar;
    }

    public s b() throws a {
        if (this.b == null) {
            this.b = this.f5849a.a();
        }
        return this.b;
    }

    public int c() {
        return this.f5849a.b();
    }

    public byte[] d() {
        return this.f5849a.c().b();
    }

    public int e() {
        return this.f5849a.d();
    }

    private r a(int i, int i2, int[] iArr, int[] iArr2, byte[] bArr, int i3) {
        int i4;
        r rVar = new r(i2);
        for (int i5 = 1; i5 < i2; i5++) {
            int i6 = iArr2[i5 - 1];
            int i7 = bArr[i5] & 255;
            iArr2[i5] = i6 + (i7 * i7);
        }
        int i8 = i3 + 1;
        int i9 = i8;
        while (true) {
            i4 = i2 - i3;
            if (i9 >= i4) {
                break;
            }
            double d = iArr[i9 + i3] - iArr[(i9 - i3) - 1];
            double d2 = i;
            if ((bArr[i9] & 255) <= (d / d2) * ((0.5f * (Math.sqrt(((iArr2[r6] - iArr2[r8]) - ((d * d) / d2)) / (i - 1)) / 127)) + 1.0d)) {
                rVar.g(i9);
            }
            i9++;
        }
        if (rVar.b(i8)) {
            rVar.c(0, i8);
        }
        if (rVar.b(i4 - 1)) {
            rVar.c(i4, i2);
        }
        return rVar;
    }

    public r a(int i, r rVar) throws a {
        return this.f5849a.a(i, rVar);
    }

    public void a(s sVar) {
        this.b = sVar;
    }

    public p a(int i, int i2, int i3, int i4) {
        return new p(this.f5849a.a(this.f5849a.c().a(i, i2, i3, i4)));
    }

    public o a() {
        return this.f5849a;
    }
}
