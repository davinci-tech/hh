package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: classes9.dex */
public final class xb extends eb {
    public final transient byte[][] g;
    public final transient int[] h;

    @Override // com.huawei.hms.network.embedded.eb
    public String toString() {
        return o().toString();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public String n() {
        return o().n();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public byte[] m() {
        int[] iArr = this.h;
        byte[][] bArr = this.g;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.h;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.g[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    @Override // com.huawei.hms.network.embedded.eb
    public eb l() {
        return o().l();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public eb k() {
        return o().k();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public int j() {
        return this.h[this.g.length - 1];
    }

    @Override // com.huawei.hms.network.embedded.eb
    public int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int length = this.g.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr = this.g[i2];
            int[] iArr = this.h;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            for (int i7 = i5; i7 < (i6 - i4) + i5; i7++) {
                i3 = (i3 * 31) + bArr[i7];
            }
            i2++;
            i4 = i6;
        }
        this.b = i3;
        return i3;
    }

    @Override // com.huawei.hms.network.embedded.eb
    public eb h() {
        return o().h();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public eb g() {
        return o().g();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public eb f() {
        return o().f();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof eb) {
            eb ebVar = (eb) obj;
            if (ebVar.j() == j() && a(0, ebVar, 0, j())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.hms.network.embedded.eb
    public byte[] e() {
        return m();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public String d() {
        return o().d();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public eb d(eb ebVar) {
        return o().d(ebVar);
    }

    @Override // com.huawei.hms.network.embedded.eb
    public String c() {
        return o().c();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public eb c(eb ebVar) {
        return o().c(ebVar);
    }

    @Override // com.huawei.hms.network.embedded.eb
    public String b() {
        return o().b();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public eb b(int i) {
        return o().b(i);
    }

    @Override // com.huawei.hms.network.embedded.eb
    public int b(byte[] bArr, int i) {
        return o().b(bArr, i);
    }

    @Override // com.huawei.hms.network.embedded.eb
    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > j() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int c = c(i);
        while (i3 > 0) {
            int i4 = c == 0 ? 0 : this.h[c - 1];
            int min = Math.min(i3, ((this.h[c] - i4) + i4) - i);
            int[] iArr = this.h;
            byte[][] bArr2 = this.g;
            if (!cc.a(bArr2[c], (i - i4) + iArr[bArr2.length + c], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            c++;
        }
        return true;
    }

    @Override // com.huawei.hms.network.embedded.eb
    public boolean a(int i, eb ebVar, int i2, int i3) {
        if (i < 0 || i > j() - i3) {
            return false;
        }
        int c = c(i);
        while (i3 > 0) {
            int i4 = c == 0 ? 0 : this.h[c - 1];
            int min = Math.min(i3, ((this.h[c] - i4) + i4) - i);
            int[] iArr = this.h;
            byte[][] bArr = this.g;
            if (!ebVar.a(i2, bArr[c], (i - i4) + iArr[bArr.length + c], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            c++;
        }
        return true;
    }

    @Override // com.huawei.hms.network.embedded.eb
    public void a(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        int length = this.g.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.h;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            outputStream.write(this.g[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    @Override // com.huawei.hms.network.embedded.eb
    public void a(bb bbVar) {
        int length = this.g.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.h;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            vb vbVar = new vb(this.g[i], i3, (i3 + i4) - i2, true, false);
            vb vbVar2 = bbVar.f5191a;
            if (vbVar2 == null) {
                vbVar.g = vbVar;
                vbVar.f = vbVar;
                bbVar.f5191a = vbVar;
            } else {
                vbVar2.g.a(vbVar);
            }
            i++;
            i2 = i4;
        }
        bbVar.b += i2;
    }

    @Override // com.huawei.hms.network.embedded.eb
    public ByteBuffer a() {
        return ByteBuffer.wrap(m()).asReadOnlyBuffer();
    }

    @Override // com.huawei.hms.network.embedded.eb
    public String a(Charset charset) {
        return o().a(charset);
    }

    @Override // com.huawei.hms.network.embedded.eb
    public eb a(int i, int i2) {
        return o().a(i, i2);
    }

    @Override // com.huawei.hms.network.embedded.eb
    public int a(byte[] bArr, int i) {
        return o().a(bArr, i);
    }

    @Override // com.huawei.hms.network.embedded.eb
    public byte a(int i) {
        cc.a(this.h[this.g.length - 1], i, 1L);
        int c = c(i);
        int i2 = c == 0 ? 0 : this.h[c - 1];
        int[] iArr = this.h;
        byte[][] bArr = this.g;
        return bArr[c][(i - i2) + iArr[bArr.length + c]];
    }

    private Object p() {
        return o();
    }

    private eb o() {
        return new eb(m());
    }

    private int c(int i) {
        int binarySearch = Arrays.binarySearch(this.h, 0, this.g.length, i + 1);
        return binarySearch >= 0 ? binarySearch : ~binarySearch;
    }

    public xb(bb bbVar, int i) {
        super(null);
        cc.a(bbVar.b, 0L, i);
        vb vbVar = bbVar.f5191a;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            int i5 = vbVar.c;
            int i6 = vbVar.b;
            if (i5 == i6) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += i5 - i6;
            i4++;
            vbVar = vbVar.f;
        }
        this.g = new byte[i4][];
        this.h = new int[i4 * 2];
        vb vbVar2 = bbVar.f5191a;
        int i7 = 0;
        while (i2 < i) {
            this.g[i7] = vbVar2.f5550a;
            i2 += vbVar2.c - vbVar2.b;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.h;
            iArr[i7] = i2;
            iArr[this.g.length + i7] = vbVar2.b;
            vbVar2.d = true;
            i7++;
            vbVar2 = vbVar2.f;
        }
    }
}
