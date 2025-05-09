package defpackage;

import net.lingala.zip4j.model.ZipHeader;

/* loaded from: classes7.dex */
public class uss extends ZipHeader {
    private long d = -1;
    private long e = -1;
    private long c = -1;
    private int b = -1;

    public long a() {
        return this.d;
    }

    public void c(long j) {
        this.d = j;
    }

    public long d() {
        return this.e;
    }

    public void d(long j) {
        this.e = j;
    }

    public long e() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public int c() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }
}
