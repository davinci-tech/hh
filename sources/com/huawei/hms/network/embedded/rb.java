package com.huawei.hms.network.embedded;

import java.io.IOException;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class rb {

    /* renamed from: a, reason: collision with root package name */
    public final long f5462a;
    public boolean c;
    public boolean d;

    @Nullable
    public yb g;
    public final bb b = new bb();
    public final yb e = new a();
    public final zb f = new b();

    public final class a implements yb {

        /* renamed from: a, reason: collision with root package name */
        public final sb f5463a = new sb();

        @Override // com.huawei.hms.network.embedded.yb
        public void write(bb bbVar, long j) throws IOException {
            yb ybVar;
            synchronized (rb.this.b) {
                if (!rb.this.c) {
                    while (true) {
                        if (j <= 0) {
                            ybVar = null;
                            break;
                        }
                        if (rb.this.g != null) {
                            ybVar = rb.this.g;
                            break;
                        }
                        if (rb.this.d) {
                            throw new IOException("source is closed");
                        }
                        long B = rb.this.f5462a - rb.this.b.B();
                        if (B == 0) {
                            this.f5463a.a(rb.this.b);
                        } else {
                            long min = Math.min(B, j);
                            rb.this.b.write(bbVar, min);
                            j -= min;
                            rb.this.b.notifyAll();
                        }
                    }
                } else {
                    throw new IllegalStateException("closed");
                }
            }
            if (ybVar != null) {
                this.f5463a.a(ybVar.timeout());
                try {
                    ybVar.write(bbVar, j);
                } finally {
                    this.f5463a.f();
                }
            }
        }

        @Override // com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return this.f5463a;
        }

        @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
        public void flush() throws IOException {
            yb ybVar;
            synchronized (rb.this.b) {
                if (rb.this.c) {
                    throw new IllegalStateException("closed");
                }
                if (rb.this.g != null) {
                    ybVar = rb.this.g;
                } else {
                    if (rb.this.d && rb.this.b.B() > 0) {
                        throw new IOException("source is closed");
                    }
                    ybVar = null;
                }
            }
            if (ybVar != null) {
                this.f5463a.a(ybVar.timeout());
                try {
                    ybVar.flush();
                } finally {
                    this.f5463a.f();
                }
            }
        }

        @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            yb ybVar;
            synchronized (rb.this.b) {
                if (rb.this.c) {
                    return;
                }
                if (rb.this.g != null) {
                    ybVar = rb.this.g;
                } else {
                    if (rb.this.d && rb.this.b.B() > 0) {
                        throw new IOException("source is closed");
                    }
                    rb.this.c = true;
                    rb.this.b.notifyAll();
                    ybVar = null;
                }
                if (ybVar != null) {
                    this.f5463a.a(ybVar.timeout());
                    try {
                        ybVar.close();
                    } finally {
                        this.f5463a.f();
                    }
                }
            }
        }

        public a() {
        }
    }

    public final class b implements zb {

        /* renamed from: a, reason: collision with root package name */
        public final ac f5464a = new ac();

        @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return this.f5464a;
        }

        @Override // com.huawei.hms.network.embedded.zb
        public long read(bb bbVar, long j) throws IOException {
            synchronized (rb.this.b) {
                if (rb.this.d) {
                    throw new IllegalStateException("closed");
                }
                while (rb.this.b.B() == 0) {
                    if (rb.this.c) {
                        return -1L;
                    }
                    this.f5464a.a(rb.this.b);
                }
                long read = rb.this.b.read(bbVar, j);
                rb.this.b.notifyAll();
                return read;
            }
        }

        @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
            synchronized (rb.this.b) {
                rb.this.d = true;
                rb.this.b.notifyAll();
            }
        }

        public b() {
        }
    }

    public final zb b() {
        return this.f;
    }

    public void a(yb ybVar) throws IOException {
        boolean z;
        bb bbVar;
        while (true) {
            synchronized (this.b) {
                if (this.g != null) {
                    throw new IllegalStateException("sink already folded");
                }
                if (this.b.i()) {
                    this.d = true;
                    this.g = ybVar;
                    return;
                } else {
                    z = this.c;
                    bbVar = new bb();
                    bb bbVar2 = this.b;
                    bbVar.write(bbVar2, bbVar2.b);
                    this.b.notifyAll();
                }
            }
            try {
                ybVar.write(bbVar, bbVar.b);
                if (z) {
                    ybVar.close();
                } else {
                    ybVar.flush();
                }
            } catch (Throwable th) {
                synchronized (this.b) {
                    this.d = true;
                    this.b.notifyAll();
                    throw th;
                }
            }
        }
    }

    public final yb a() {
        return this.e;
    }

    public rb(long j) {
        if (j >= 1) {
            this.f5462a = j;
        } else {
            throw new IllegalArgumentException("maxBufferSize < 1: " + j);
        }
    }
}
