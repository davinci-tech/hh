package com.huawei.hms.network.embedded;

import com.huawei.operation.utils.Constants;
import java.io.IOException;
import java.util.zip.Deflater;

/* loaded from: classes9.dex */
public final class fb implements yb {

    /* renamed from: a, reason: collision with root package name */
    public final cb f5253a;
    public final Deflater b;
    public boolean c;

    @Override // com.huawei.hms.network.embedded.yb
    public void write(bb bbVar, long j) throws IOException {
        cc.a(bbVar.b, 0L, j);
        while (j > 0) {
            vb vbVar = bbVar.f5191a;
            int min = (int) Math.min(j, vbVar.c - vbVar.b);
            this.b.setInput(vbVar.f5550a, vbVar.b, min);
            a(false);
            long j2 = min;
            bbVar.b -= j2;
            vbVar.b += min;
            if (vbVar.b == vbVar.c) {
                bbVar.f5191a = vbVar.b();
                wb.a(vbVar);
            }
            j -= j2;
        }
    }

    public String toString() {
        return "DeflaterSink(" + this.f5253a + Constants.RIGHT_BRACKET_ONLY;
    }

    @Override // com.huawei.hms.network.embedded.yb
    public ac timeout() {
        return this.f5253a.timeout();
    }

    @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
    public void flush() throws IOException {
        a(true);
        this.f5253a.flush();
    }

    public void e() throws IOException {
        this.b.finish();
        a(false);
    }

    @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        if (this.c) {
            return;
        }
        try {
            e();
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
            this.f5253a.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.c = true;
        if (th != null) {
            cc.a(th);
        }
    }

    private void a(boolean z) throws IOException {
        vb e;
        bb a2 = this.f5253a.a();
        while (true) {
            e = a2.e(1);
            Deflater deflater = this.b;
            byte[] bArr = e.f5550a;
            int i = e.c;
            int i2 = 8192 - i;
            int deflate = z ? deflater.deflate(bArr, i, i2, 2) : deflater.deflate(bArr, i, i2);
            if (deflate > 0) {
                e.c += deflate;
                a2.b += deflate;
                this.f5253a.d();
            } else if (this.b.needsInput()) {
                break;
            }
        }
        if (e.b == e.c) {
            a2.f5191a = e.b();
            wb.a(e);
        }
    }

    public fb(yb ybVar, Deflater deflater) {
        this(ob.a(ybVar), deflater);
    }

    public fb(cb cbVar, Deflater deflater) {
        if (cbVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (deflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f5253a = cbVar;
        this.b = deflater;
    }
}
