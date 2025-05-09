package com.huawei.hms.network.embedded;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: classes9.dex */
public final class nb implements zb {

    /* renamed from: a, reason: collision with root package name */
    public final db f5393a;
    public final Inflater b;
    public int c;
    public boolean d;

    @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
    public ac timeout() {
        return this.f5393a.timeout();
    }

    @Override // com.huawei.hms.network.embedded.zb
    public long read(bb bbVar, long j) throws IOException {
        boolean b;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return 0L;
        }
        do {
            b = b();
            try {
                vb e = bbVar.e(1);
                int inflate = this.b.inflate(e.f5550a, e.c, (int) Math.min(j, 8192 - e.c));
                if (inflate > 0) {
                    e.c += inflate;
                    long j2 = inflate;
                    bbVar.b += j2;
                    return j2;
                }
                if (!this.b.finished() && !this.b.needsDictionary()) {
                }
                c();
                if (e.b != e.c) {
                    return -1L;
                }
                bbVar.f5191a = e.b();
                wb.a(e);
                return -1L;
            } catch (DataFormatException e2) {
                throw new IOException(e2);
            }
        } while (!b);
        throw new EOFException("source exhausted prematurely");
    }

    @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
    public void close() throws IOException {
        if (this.d) {
            return;
        }
        this.b.end();
        this.d = true;
        this.f5393a.close();
    }

    public final boolean b() throws IOException {
        if (!this.b.needsInput()) {
            return false;
        }
        c();
        if (this.b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        }
        if (this.f5393a.i()) {
            return true;
        }
        vb vbVar = this.f5393a.a().f5191a;
        int i = vbVar.c;
        int i2 = vbVar.b;
        this.c = i - i2;
        this.b.setInput(vbVar.f5550a, i2, this.c);
        return false;
    }

    private void c() throws IOException {
        int i = this.c;
        if (i == 0) {
            return;
        }
        int remaining = i - this.b.getRemaining();
        this.c -= remaining;
        this.f5393a.skip(remaining);
    }

    public nb(zb zbVar, Inflater inflater) {
        this(ob.a(zbVar), inflater);
    }

    public nb(db dbVar, Inflater inflater) {
        if (dbVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f5393a = dbVar;
        this.b = inflater;
    }
}
