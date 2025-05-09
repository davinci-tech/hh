package defpackage;

import defpackage.vbq;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes7.dex */
public final class vbn extends vbq {

    /* renamed from: a, reason: collision with root package name */
    private int f17648a;
    private byte d;

    public vbn(byte[] bArr) {
        this(bArr, true);
    }

    public vbn(byte[] bArr, boolean z) {
        this(z ? Arrays.copyOf(bArr, bArr.length) : bArr, 0, bArr.length);
    }

    public vbn(byte[] bArr, int i, int i2) {
        this(new vbq.c(bArr, i, i2));
    }

    public vbn(ByteArrayInputStream byteArrayInputStream) {
        super(byteArrayInputStream);
        this.d = this.e;
        this.f17648a = this.c;
    }

    public void d() {
        this.d = this.e;
        this.f17648a = this.c;
        this.b.mark(0);
    }

    public void j() {
        try {
            this.b.reset();
        } catch (IOException unused) {
        }
        this.e = this.d;
        this.c = this.f17648a;
    }

    @Override // defpackage.vbq
    public void b() {
        try {
            this.b.skip(this.b.available());
        } catch (IOException unused) {
        }
        super.b();
    }

    @Override // defpackage.vbq
    public byte[] a(int i) {
        int f = f();
        if (i < 0) {
            i = f;
        } else if (i > f) {
            throw new IllegalArgumentException("requested " + i + " bytes exceeds available " + f + " bytes.");
        }
        return super.a(i);
    }

    public byte[] i() {
        return a(-1);
    }

    public void b(String str) {
        int a2 = a();
        if (a2 <= 0) {
            return;
        }
        throw new IllegalArgumentException(str + " not finished! " + a2 + " bits left.");
    }

    public boolean e() {
        return f() > 0;
    }

    public boolean g(int i) {
        return f() >= i;
    }

    public int a() {
        return (f() * 8) + this.c + 1;
    }

    private int f() {
        try {
            return this.b.available();
        } catch (IOException unused) {
            return -1;
        }
    }
}
