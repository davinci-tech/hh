package com.huawei.hms.network.embedded;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* loaded from: classes9.dex */
public final class kb implements zb {
    public static final byte f = 1;
    public static final byte g = 2;
    public static final byte h = 3;
    public static final byte i = 4;
    public static final byte j = 0;
    public static final byte k = 1;
    public static final byte l = 2;
    public static final byte m = 3;
    public final db b;
    public final Inflater c;
    public final nb d;

    /* renamed from: a, reason: collision with root package name */
    public int f5350a = 0;
    public final CRC32 e = new CRC32();

    @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
    public ac timeout() {
        return this.b.timeout();
    }

    @Override // com.huawei.hms.network.embedded.zb
    public long read(bb bbVar, long j2) throws IOException {
        if (j2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j2);
        }
        if (j2 == 0) {
            return 0L;
        }
        if (this.f5350a == 0) {
            b();
            this.f5350a = 1;
        }
        if (this.f5350a == 1) {
            long j3 = bbVar.b;
            long read = this.d.read(bbVar, j2);
            if (read != -1) {
                a(bbVar, j3, read);
                return read;
            }
            this.f5350a = 2;
        }
        if (this.f5350a == 2) {
            c();
            this.f5350a = 3;
            if (!this.b.i()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
    public void close() throws IOException {
        this.d.close();
    }

    private void c() throws IOException {
        a("CRC", this.b.e(), (int) this.e.getValue());
        a("ISIZE", this.b.e(), (int) this.c.getBytesWritten());
    }

    private void b() throws IOException {
        this.b.i(10L);
        byte j2 = this.b.a().j(3L);
        boolean z = ((j2 >> 1) & 1) == 1;
        if (z) {
            a(this.b.a(), 0L, 10L);
        }
        a("ID1ID2", 8075, this.b.readShort());
        this.b.skip(8L);
        if (((j2 >> 2) & 1) == 1) {
            this.b.i(2L);
            if (z) {
                a(this.b.a(), 0L, 2L);
            }
            long j3 = this.b.a().j();
            this.b.i(j3);
            if (z) {
                a(this.b.a(), 0L, j3);
            }
            this.b.skip(j3);
        }
        if (((j2 >> 3) & 1) == 1) {
            long a2 = this.b.a((byte) 0);
            if (a2 == -1) {
                throw new EOFException();
            }
            if (z) {
                a(this.b.a(), 0L, a2 + 1);
            }
            this.b.skip(a2 + 1);
        }
        if (((j2 >> 4) & 1) == 1) {
            long a3 = this.b.a((byte) 0);
            if (a3 == -1) {
                throw new EOFException();
            }
            if (z) {
                a(this.b.a(), 0L, a3 + 1);
            }
            this.b.skip(a3 + 1);
        }
        if (z) {
            a("FHCRC", this.b.j(), (short) this.e.getValue());
            this.e.reset();
        }
    }

    private void a(String str, int i2, int i3) throws IOException {
        if (i3 != i2) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i3), Integer.valueOf(i2)));
        }
    }

    private void a(bb bbVar, long j2, long j3) {
        vb vbVar = bbVar.f5191a;
        while (true) {
            long j4 = vbVar.c - vbVar.b;
            if (j2 < j4) {
                break;
            }
            j2 -= j4;
            vbVar = vbVar.f;
        }
        while (j3 > 0) {
            int min = (int) Math.min(vbVar.c - r6, j3);
            this.e.update(vbVar.f5550a, (int) (vbVar.b + j2), min);
            j3 -= min;
            vbVar = vbVar.f;
            j2 = 0;
        }
    }

    public kb(zb zbVar) {
        if (zbVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        Inflater inflater = new Inflater(true);
        this.c = inflater;
        db a2 = ob.a(zbVar);
        this.b = a2;
        this.d = new nb(a2, inflater);
    }
}
