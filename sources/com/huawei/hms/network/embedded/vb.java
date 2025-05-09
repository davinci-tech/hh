package com.huawei.hms.network.embedded;

import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class vb {
    public static final int h = 8192;
    public static final int i = 1024;

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f5550a;
    public int b;
    public int c;
    public boolean d;
    public boolean e;
    public vb f;
    public vb g;

    public final vb d() {
        return new vb((byte[]) this.f5550a.clone(), this.b, this.c, false, true);
    }

    public final vb c() {
        this.d = true;
        return new vb(this.f5550a, this.b, this.c, true, false);
    }

    @Nullable
    public final vb b() {
        vb vbVar = this.f;
        vb vbVar2 = vbVar != this ? vbVar : null;
        vb vbVar3 = this.g;
        vbVar3.f = vbVar;
        this.f.g = vbVar3;
        this.f = null;
        this.g = null;
        return vbVar2;
    }

    public final void a(vb vbVar, int i2) {
        if (!vbVar.e) {
            throw new IllegalArgumentException();
        }
        int i3 = vbVar.c;
        int i4 = i3 + i2;
        if (i4 > 8192) {
            if (vbVar.d) {
                throw new IllegalArgumentException();
            }
            int i5 = vbVar.b;
            if (i4 - i5 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = vbVar.f5550a;
            System.arraycopy(bArr, i5, bArr, 0, i3 - i5);
            vbVar.c -= vbVar.b;
            vbVar.b = 0;
        }
        System.arraycopy(this.f5550a, this.b, vbVar.f5550a, vbVar.c, i2);
        vbVar.c += i2;
        this.b += i2;
    }

    public final void a() {
        vb vbVar = this.g;
        if (vbVar == this) {
            throw new IllegalStateException();
        }
        if (vbVar.e) {
            int i2 = this.c - this.b;
            if (i2 > (8192 - vbVar.c) + (vbVar.d ? 0 : vbVar.b)) {
                return;
            }
            a(vbVar, i2);
            b();
            wb.a(this);
        }
    }

    public final vb a(vb vbVar) {
        vbVar.g = this;
        vbVar.f = this.f;
        this.f.g = vbVar;
        this.f = vbVar;
        return vbVar;
    }

    public final vb a(int i2) {
        vb a2;
        if (i2 <= 0 || i2 > this.c - this.b) {
            throw new IllegalArgumentException();
        }
        if (i2 >= 1024) {
            a2 = c();
        } else {
            a2 = wb.a();
            System.arraycopy(this.f5550a, this.b, a2.f5550a, 0, i2);
        }
        a2.c = a2.b + i2;
        this.b += i2;
        this.g.a(a2);
        return a2;
    }

    public vb(byte[] bArr, int i2, int i3, boolean z, boolean z2) {
        this.f5550a = bArr;
        this.b = i2;
        this.c = i3;
        this.d = z;
        this.e = z2;
    }

    public vb() {
        this.f5550a = new byte[8192];
        this.e = true;
        this.d = false;
    }
}
