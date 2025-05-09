package defpackage;

import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bjo {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f407a;
    private int b;
    private long c;
    private int e;

    public int d() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public byte[] e() {
        byte[] bArr = this.f407a;
        if (bArr != null) {
            return bArr;
        }
        LogUtil.a("getHiChainData", "mHiChainData is null.");
        return new byte[0];
    }

    public void c(byte[] bArr) {
        this.f407a = bArr;
    }

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public int b() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }
}
