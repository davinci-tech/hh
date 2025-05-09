package com.huawei.openalliance.ad.views.gif;

import android.graphics.Bitmap;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.cy;
import java.io.Closeable;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class a {
    private static final String c = "a";
    private int[] C;
    private int D;
    private Bitmap F;
    private int[] G;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;

    /* renamed from: a, reason: collision with root package name */
    public int f8076a;
    public int b;
    private final int d;
    private InputStream e;
    private boolean k;
    private boolean l;
    private boolean m;
    private short[] n;
    private byte[] o;
    private byte[] q;
    private byte[] r;
    private int s;
    private int w;
    private int x;
    private int y;
    private int z;
    private final Object f = new Object();
    private final Object g = new Object();
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private byte[] p = new byte[512];
    private int t = 100;
    private int u = 0;
    private int v = 0;
    private int[] A = null;
    private int[] B = null;
    private int E = 0;
    private int H = 0;
    private int Q = 0;
    private int[] R = null;

    public boolean c() {
        boolean z;
        synchronized (this.f) {
            z = this.h;
        }
        return z;
    }

    public void b() {
        synchronized (this.f) {
            if (!this.h) {
                this.h = true;
                cy.a((Closeable) this.e);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0050, code lost:
    
        if (t() == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0052, code lost:
    
        b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0055, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.openalliance.ad.views.gif.c a() {
        /*
            r4 = this;
            boolean r0 = r4.c()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto Lc
            r4.a(r2)
            return r1
        Lc:
            boolean r0 = r4.t()
            if (r0 != 0) goto L4c
            boolean r0 = r4.i()
            if (r0 == 0) goto L1c
            r4.a(r2)
            goto L4c
        L1c:
            int r0 = r4.f()
            if (r0 == 0) goto Lc
            r3 = 33
            if (r0 == r3) goto L3c
            r3 = 44
            if (r0 == r3) goto L35
            r3 = 59
            if (r0 == r3) goto L31
            r4.v = r2
            goto Lc
        L31:
            r4.a(r2)
            goto Lc
        L35:
            com.huawei.openalliance.ad.views.gif.c r0 = r4.j()
            if (r0 == 0) goto Lc
            return r0
        L3c:
            r0 = 249(0xf9, float:3.49E-43)
            int r3 = r4.f()
            if (r0 != r3) goto L48
            r4.m()
            goto Lc
        L48:
            r4.n()
            goto Lc
        L4c:
            boolean r0 = r4.t()
            if (r0 == 0) goto L55
            r4.b()
        L55:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.gif.a.a():com.huawei.openalliance.ad.views.gif.c");
    }

    private boolean t() {
        boolean z;
        synchronized (this.g) {
            z = this.i;
        }
        return z;
    }

    private void s() {
        if (this.R == null) {
            this.R = new int[this.f8076a * this.b];
        }
        int i = this.E;
        if (i > 0) {
            if (3 == i) {
                this.G = null;
            }
            int[] iArr = this.G;
            if (iArr != null) {
                this.R = iArr;
                if (2 == i) {
                    int i2 = !this.j ? this.s : 0;
                    for (int i3 = 0; i3 < this.P; i3++) {
                        int i4 = ((this.N + i3) * this.f8076a) + this.M;
                        int i5 = this.O;
                        for (int i6 = i4; i6 < i5 + i4; i6++) {
                            this.R[i6] = i2;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int r() {
        /*
            r4 = this;
            int r0 = r4.f()
            r4.Q = r0
            r1 = 0
            if (r0 <= 0) goto L2f
        L9:
            int r0 = r4.Q     // Catch: java.lang.Exception -> L1c java.io.IOException -> L21
            if (r1 >= r0) goto L28
            java.io.InputStream r2 = r4.e     // Catch: java.lang.Exception -> L1c java.io.IOException -> L21
            byte[] r3 = r4.p     // Catch: java.lang.Exception -> L1c java.io.IOException -> L21
            int r0 = r0 - r1
            int r0 = r2.read(r3, r1, r0)     // Catch: java.lang.Exception -> L1c java.io.IOException -> L21
            r2 = -1
            if (r0 != r2) goto L1a
            goto L28
        L1a:
            int r1 = r1 + r0
            goto L9
        L1c:
            java.lang.String r0 = com.huawei.openalliance.ad.views.gif.a.c
            java.lang.String r2 = "read block fail"
            goto L25
        L21:
            java.lang.String r0 = com.huawei.openalliance.ad.views.gif.a.c
            java.lang.String r2 = "read block IOException"
        L25:
            com.huawei.openalliance.ad.ho.c(r0, r2)
        L28:
            int r0 = r4.Q
            if (r1 >= r0) goto L2f
            r0 = 1
            r4.v = r0
        L2f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.gif.a.r():int");
    }

    private void q() {
        this.E = this.u;
        this.M = this.I;
        this.N = this.J;
        this.O = this.K;
        this.P = this.L;
        this.s = this.z;
        this.G = this.R;
        this.j = false;
        this.u = 0;
        this.B = null;
        this.t = this.d;
    }

    private void p() {
        this.v = 3;
    }

    private void o() {
        int i;
        try {
            s();
            int i2 = 0;
            int i3 = 1;
            int i4 = 8;
            int i5 = 0;
            while (true) {
                int i6 = this.L;
                if (i2 >= i6) {
                    this.F = a(this.R, this.f8076a, this.b, this.F);
                    return;
                }
                if (this.m) {
                    if (i5 >= i6) {
                        i3++;
                        if (i3 == 2) {
                            i5 = 4;
                        } else if (i3 == 3) {
                            i4 = 4;
                            i5 = 2;
                        } else if (i3 == 4) {
                            i5 = 1;
                            i4 = 2;
                        }
                    }
                    i = i5 + i4;
                } else {
                    i = i5;
                    i5 = i2;
                }
                int i7 = i5 + this.J;
                if (i7 < this.b) {
                    int i8 = this.f8076a;
                    int i9 = i7 * i8;
                    int i10 = this.I + i9;
                    int i11 = this.K;
                    int i12 = i10 + i11;
                    int i13 = i9 + i8;
                    if (i13 < i12) {
                        i12 = i13;
                    }
                    int i14 = i11 * i2;
                    while (i10 < i12) {
                        int i15 = this.C[this.r[i14] & 255];
                        if (i15 != 0) {
                            this.R[i10] = i15;
                        }
                        i10++;
                        i14++;
                    }
                }
                i2++;
                i5 = i;
            }
        } catch (Exception | StackOverflowError unused) {
            this.v = 1;
            ho.c(c, "set pixel error");
        }
    }

    private void n() {
        do {
            r();
            if (this.Q <= 0) {
                return;
            }
        } while (!i());
    }

    private void m() {
        f();
        int f = f();
        int i = (f & 28) >> 2;
        this.u = i;
        if (i == 0) {
            this.u = 1;
        }
        this.j = (f & 1) != 0;
        int h = h() * 10;
        this.t = h;
        int i2 = this.d;
        if (i2 > h) {
            this.t = i2;
        }
        this.D = f();
        f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v13, types: [short] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    private void l() {
        int i;
        int i2;
        int i3;
        short s;
        int i4 = this.K * this.L;
        b(i4);
        int f = f();
        int i5 = 1;
        int i6 = 1 << f;
        int i7 = i6 + 1;
        int i8 = i6 + 2;
        int i9 = f + 1;
        int i10 = (1 << i9) - 1;
        for (int i11 = 0; i11 < i6; i11++) {
            this.n[i11] = 0;
            this.o[i11] = (byte) i11;
        }
        int i12 = i9;
        int i13 = i10;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = -1;
        int i23 = i8;
        while (i14 < i4) {
            if (i15 != 0) {
                i = i9;
                i2 = i6;
                i3 = i5;
            } else if (i17 >= i12) {
                int i24 = i18 & i13;
                i18 >>= i12;
                i17 -= i12;
                if (i24 > i23 || i24 == i7) {
                    break;
                }
                if (i24 == i6) {
                    i12 = i9;
                    i23 = i8;
                    i13 = i10;
                    i22 = -1;
                } else if (i22 == -1) {
                    this.q[i15] = this.o[i24];
                    i15++;
                    i22 = i24;
                    i16 = i22;
                    i5 = 1;
                } else {
                    if (i24 == i23) {
                        this.q[i15] = (byte) i16;
                        i15++;
                        s = i22;
                    } else {
                        s = i24;
                    }
                    while (s > i6) {
                        this.q[i15] = this.o[s];
                        s = this.n[s];
                        i15++;
                        i9 = i9;
                    }
                    i = i9;
                    byte[] bArr = this.o;
                    int i25 = bArr[s] & 255;
                    if (i23 >= 4096) {
                        break;
                    }
                    i2 = i6;
                    byte b = (byte) i25;
                    this.q[i15] = b;
                    this.n[i23] = (short) i22;
                    bArr[i23] = b;
                    i23++;
                    if ((i23 & i13) == 0 && i23 < 4096) {
                        i12++;
                        i13 += i23;
                    }
                    i15++;
                    i16 = i25;
                    i22 = i24;
                    i3 = 1;
                }
            } else {
                if (i19 == 0) {
                    i19 = r();
                    if (i19 <= 0) {
                        break;
                    } else {
                        i20 = 0;
                    }
                }
                i18 += (this.p[i20] & 255) << i17;
                i17 += 8;
                i20++;
                i19--;
            }
            i15 -= i3;
            this.r[i21] = this.q[i15];
            i14++;
            i21++;
            i5 = i3;
            i9 = i;
            i6 = i2;
        }
        for (int i26 = i21; i26 < i4; i26++) {
            this.r[i26] = 0;
        }
    }

    private int k() {
        int[] iArr;
        this.I = h();
        this.J = h();
        this.K = h();
        this.L = h();
        int f = f();
        int i = 0;
        this.l = (f & 128) != 0;
        this.m = (f & 64) != 0;
        int pow = (int) Math.pow(2.0d, (f & 7) + 1);
        this.x = pow;
        if (this.l) {
            int[] a2 = a(pow);
            this.B = a2;
            this.C = a2;
        } else {
            this.C = this.A;
            if (this.y == this.D) {
                this.z = 0;
            }
        }
        if (this.j && (iArr = this.C) != null && iArr.length > 0) {
            int length = iArr.length;
            int i2 = this.D;
            if (length > i2) {
                int i3 = iArr[i2];
                iArr[i2] = 0;
                i = i3;
            }
        }
        if (this.C == null) {
            this.v = 1;
        }
        return i;
    }

    private c j() {
        int k;
        c cVar = null;
        try {
            k = k();
        } catch (Exception | StackOverflowError unused) {
            this.v = 1;
            ho.c(c, "read image error");
        } catch (OutOfMemoryError unused2) {
            this.v = 1;
            ho.c(c, "run out of memory");
            p();
        }
        if (i()) {
            return null;
        }
        l();
        n();
        if (i()) {
            return null;
        }
        o();
        if (i()) {
            return null;
        }
        Bitmap bitmap = this.F;
        if (bitmap != null) {
            int i = this.H + 1;
            this.H = i;
            cVar = new c(i, bitmap, this.t);
        }
        if (this.j) {
            this.C[this.D] = k;
        }
        q();
        return cVar;
    }

    private boolean i() {
        return this.v != 0;
    }

    private int h() {
        return f() | (f() << 8);
    }

    private void g() {
        this.f8076a = h();
        this.b = h();
        this.k = (f() & 128) != 0;
        this.w = (int) Math.pow(2.0d, (r0 & 7) + 1);
        this.y = f();
        f();
    }

    private int f() {
        try {
            return this.e.read();
        } catch (Exception unused) {
            this.v = 1;
            return 0;
        }
    }

    private void e() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            stringBuffer.append((char) f());
        }
        if (!stringBuffer.toString().startsWith("GIF")) {
            this.v = 1;
            return;
        }
        g();
        if (!this.k || i()) {
            return;
        }
        int[] a2 = a(this.w);
        this.A = a2;
        this.z = a2[this.y];
    }

    private void d() {
        if (this.e == null) {
            a(true);
            return;
        }
        e();
        if (i()) {
            a(true);
            b();
        }
    }

    private void b(int i) {
        byte[] bArr = this.r;
        if (bArr == null || bArr.length < i) {
            this.r = new byte[i];
        }
        if (this.n == null) {
            this.n = new short[4096];
        }
        if (this.o == null) {
            this.o = new byte[4096];
        }
        if (this.q == null) {
            this.q = new byte[4097];
        }
    }

    private int[] a(int i) {
        int i2;
        int[] iArr = new int[256];
        int i3 = i * 3;
        byte[] bArr = new byte[i3];
        try {
            i2 = this.e.read(bArr);
        } catch (Exception unused) {
            ho.c(c, "read color table fail");
            i2 = 0;
        }
        if (i2 < i3) {
            this.v = 1;
        } else {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                iArr[i5] = ((bArr[i4 + 1] & 255) << 8) | ((bArr[i4] & 255) << 16) | (-16777216) | (bArr[i4 + 2] & 255);
                i4 += 3;
            }
        }
        return iArr;
    }

    private void a(boolean z) {
        synchronized (this.g) {
            this.i = z;
        }
    }

    private Bitmap a(int[] iArr, int i, int i2, Bitmap bitmap) {
        if (bitmap == null) {
            Bitmap.Config config = ao.b() > 62914560 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
            if (ho.a()) {
                ho.a(c, "create image with config %s", config);
            }
            bitmap = Bitmap.createBitmap(i, i2, config);
        }
        bitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
        return bitmap;
    }

    public a(InputStream inputStream, int i) {
        this.e = inputStream;
        this.d = i;
        d();
    }
}
