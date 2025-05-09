package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* loaded from: classes9.dex */
public final class jb implements yb {

    /* renamed from: a, reason: collision with root package name */
    public final cb f5329a;
    public final Deflater b;
    public final fb c;
    public boolean d;
    public final CRC32 e = new CRC32();

    @Override // com.huawei.hms.network.embedded.yb
    public void write(bb bbVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return;
        }
        a(bbVar, j);
        this.c.write(bbVar, j);
    }

    @Override // com.huawei.hms.network.embedded.yb
    public ac timeout() {
        return this.f5329a.timeout();
    }

    @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
    public void flush() throws IOException {
        this.c.flush();
    }

    public final Deflater e() {
        return this.b;
    }

    @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        if (this.d) {
            return;
        }
        try {
            this.c.e();
            f();
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.b.end();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        try {
            this.f5329a.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.d = true;
        if (th != null) {
            cc.a(th);
        }
    }

    private void g() {
        bb a2 = this.f5329a.a();
        a2.writeShort(8075);
        a2.writeByte(8);
        a2.writeByte(0);
        a2.writeInt(0);
        a2.writeByte(0);
        a2.writeByte(0);
    }

    private void f() throws IOException {
        this.f5329a.a((int) this.e.getValue());
        this.f5329a.a((int) this.b.getBytesRead());
    }

    private void a(bb bbVar, long j) {
        vb vbVar = bbVar.f5191a;
        while (j > 0) {
            int min = (int) Math.min(j, vbVar.c - vbVar.b);
            this.e.update(vbVar.f5550a, vbVar.b, min);
            j -= min;
            vbVar = vbVar.f;
        }
    }

    public jb(yb ybVar) {
        if (ybVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        Deflater deflater = new Deflater(-1, true);
        this.b = deflater;
        cb a2 = ob.a(ybVar);
        this.f5329a = a2;
        this.c = new fb(a2, deflater);
        g();
    }
}
