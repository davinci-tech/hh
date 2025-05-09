package defpackage;

import net.lingala.zip4j.model.ZipHeader;

/* loaded from: classes7.dex */
public class usp extends ZipHeader {

    /* renamed from: a, reason: collision with root package name */
    private long f17533a;
    private int b;
    private byte[] d;

    public long b() {
        return this.f17533a;
    }

    public void b(long j) {
        this.f17533a = j;
    }

    public int e() {
        return this.b;
    }

    public void d(int i) {
        this.b = i;
    }

    public byte[] c() {
        return this.d;
    }

    public void a(byte[] bArr) {
        this.d = bArr;
    }
}
