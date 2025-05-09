package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.t9;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes9.dex */
public final class z9 implements Closeable {
    public static final Logger g = Logger.getLogger(u9.class.getName());

    /* renamed from: a, reason: collision with root package name */
    public final cb f5593a;
    public final boolean b;
    public final bb c;
    public int d;
    public boolean e;
    public final t9.b f;

    public int t() {
        return this.d;
    }

    public void s() throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            if (this.b) {
                Logger logger = g;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(f8.a(">> CONNECTION %s", u9.f5523a.d()));
                }
                this.f5593a.write(u9.f5523a.m());
                this.f5593a.flush();
            }
        }
    }

    public void flush() throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            this.f5593a.flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this) {
            this.e = true;
            this.f5593a.close();
        }
    }

    public void b(ca caVar) throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            int i = 0;
            a(0, caVar.d() * 6, (byte) 4, (byte) 0);
            while (i < 10) {
                if (caVar.e(i)) {
                    this.f5593a.writeShort(i == 4 ? 3 : i == 7 ? 4 : i);
                    this.f5593a.writeInt(caVar.a(i));
                }
                i++;
            }
            this.f5593a.flush();
        }
    }

    public void a(boolean z, int i, List<s9> list) throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            this.f.a(list);
            long B = this.c.B();
            int min = (int) Math.min(this.d, B);
            long j = min;
            byte b = B == j ? (byte) 4 : (byte) 0;
            if (z) {
                b = (byte) (b | 1);
            }
            a(i, min, (byte) 1, b);
            this.f5593a.write(this.c, j);
            if (B > j) {
                b(i, B - j);
            }
        }
    }

    public void a(boolean z, int i, bb bbVar, int i2) throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            a(i, z ? (byte) 1 : (byte) 0, bbVar, i2);
        }
    }

    public void a(boolean z, int i, int i2) throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            a(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
            this.f5593a.writeInt(i);
            this.f5593a.writeInt(i2);
            this.f5593a.flush();
        }
    }

    public void a(ca caVar) throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            this.d = caVar.c(this.d);
            if (caVar.b() != -1) {
                this.f.a(caVar.b());
            }
            a(0, 0, (byte) 4, (byte) 1);
            this.f5593a.flush();
        }
    }

    public void a(int i, r9 r9Var, byte[] bArr) throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            if (r9Var.f5460a == -1) {
                throw u9.a("errorCode.httpCode == -1", new Object[0]);
            }
            a(0, bArr.length + 8, (byte) 7, (byte) 0);
            this.f5593a.writeInt(i);
            this.f5593a.writeInt(r9Var.f5460a);
            if (bArr.length > 0) {
                this.f5593a.write(bArr);
            }
            this.f5593a.flush();
        }
    }

    public void a(int i, r9 r9Var) throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            if (r9Var.f5460a == -1) {
                throw new IllegalArgumentException();
            }
            a(i, 4, (byte) 3, (byte) 0);
            this.f5593a.writeInt(r9Var.f5460a);
            this.f5593a.flush();
        }
    }

    public void a(int i, long j) throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            if (j == 0 || j > 2147483647L) {
                throw u9.a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            }
            a(i, 4, (byte) 8, (byte) 0);
            this.f5593a.writeInt((int) j);
            this.f5593a.flush();
        }
    }

    public void a(int i, int i2, List<s9> list) throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IOException("closed");
            }
            this.f.a(list);
            long B = this.c.B();
            int min = (int) Math.min(this.d - 4, B);
            long j = min;
            a(i, min + 4, (byte) 5, B == j ? (byte) 4 : (byte) 0);
            this.f5593a.writeInt(i2 & Integer.MAX_VALUE);
            this.f5593a.write(this.c, j);
            if (B > j) {
                b(i, B - j);
            }
        }
    }

    public void a(int i, int i2, byte b, byte b2) throws IOException {
        Logger logger = g;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(u9.a(false, i, i2, b, b2));
        }
        int i3 = this.d;
        if (i2 > i3) {
            throw u9.a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2));
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            throw u9.a("reserved bit set: %s", Integer.valueOf(i));
        }
        a(this.f5593a, i2);
        this.f5593a.writeByte(b & 255);
        this.f5593a.writeByte(b2 & 255);
        this.f5593a.writeInt(i & Integer.MAX_VALUE);
    }

    public void a(int i, byte b, bb bbVar, int i2) throws IOException {
        a(i, i2, (byte) 0, b);
        if (i2 > 0) {
            this.f5593a.write(bbVar, i2);
        }
    }

    private void b(int i, long j) throws IOException {
        while (j > 0) {
            int min = (int) Math.min(this.d, j);
            long j2 = min;
            j -= j2;
            a(i, min, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.f5593a.write(this.c, j2);
        }
    }

    public static void a(cb cbVar, int i) throws IOException {
        cbVar.writeByte((i >>> 16) & 255);
        cbVar.writeByte((i >>> 8) & 255);
        cbVar.writeByte(i & 255);
    }

    public z9(cb cbVar, boolean z) {
        this.f5593a = cbVar;
        this.b = z;
        bb bbVar = new bb();
        this.c = bbVar;
        this.f = new t9.b(bbVar);
        this.d = 16384;
    }
}
