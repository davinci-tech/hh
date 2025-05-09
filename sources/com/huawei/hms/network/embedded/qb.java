package com.huawei.hms.network.embedded;

import java.io.IOException;

/* loaded from: classes9.dex */
public final class qb implements zb {

    /* renamed from: a, reason: collision with root package name */
    public final db f5443a;
    public final bb b;
    public vb c;
    public int d;
    public boolean e;
    public long f;

    @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
    public ac timeout() {
        return this.f5443a.timeout();
    }

    @Override // com.huawei.hms.network.embedded.zb
    public long read(bb bbVar, long j) throws IOException {
        vb vbVar;
        vb vbVar2;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.e) {
            throw new IllegalStateException("closed");
        }
        vb vbVar3 = this.c;
        if (vbVar3 != null && (vbVar3 != (vbVar2 = this.b.f5191a) || this.d != vbVar2.b)) {
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        if (j == 0) {
            return 0L;
        }
        if (!this.f5443a.h(this.f + 1)) {
            return -1L;
        }
        if (this.c == null && (vbVar = this.b.f5191a) != null) {
            this.c = vbVar;
            this.d = vbVar.b;
        }
        long min = Math.min(j, this.b.b - this.f);
        this.b.a(bbVar, this.f, min);
        this.f += min;
        return min;
    }

    @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
    public void close() throws IOException {
        this.e = true;
    }

    public qb(db dbVar) {
        this.f5443a = dbVar;
        bb a2 = dbVar.a();
        this.b = a2;
        vb vbVar = a2.f5191a;
        this.c = vbVar;
        this.d = vbVar != null ? vbVar.b : -1;
    }
}
