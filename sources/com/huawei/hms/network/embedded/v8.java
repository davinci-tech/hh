package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.ua;
import com.huawei.hms.network.embedded.v7;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketException;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class v8 {

    /* renamed from: a, reason: collision with root package name */
    public final d9 f5545a;
    public final t6 b;
    public final g7 c;
    public final w8 d;
    public final g9 e;
    public boolean f;

    public void m() {
        a(-1L, true, true, null);
    }

    public j7 l() throws IOException {
        return this.e.b();
    }

    public void k() {
        this.f5545a.timeoutEarlyExit();
    }

    public void j() {
        this.c.responseHeadersStart(this.b);
    }

    public void i() {
        this.f5545a.exchangeMessageDone(this, true, false, null);
    }

    public void h() {
        this.e.a().h();
    }

    public ua.f g() throws SocketException {
        this.f5545a.timeoutEarlyExit();
        return this.e.a().a(this);
    }

    public boolean f() {
        return this.f;
    }

    public void e() throws IOException {
        try {
            this.e.d();
        } catch (IOException e) {
            this.c.requestFailed(this.b, e);
            a(e);
            throw e;
        }
    }

    public void d() throws IOException {
        try {
            this.e.c();
        } catch (IOException e) {
            this.c.requestFailed(this.b, e);
            a(e);
            throw e;
        }
    }

    public void c() {
        this.e.cancel();
        this.f5545a.exchangeMessageDone(this, true, true, null);
    }

    public void b(v7 v7Var) {
        this.c.responseHeadersEnd(this.b, v7Var);
    }

    public y8 b() {
        return this.e.a();
    }

    public void a(IOException iOException) {
        this.d.e();
        this.e.a().a(iOException);
    }

    public void a(t7 t7Var) throws IOException {
        try {
            this.c.requestHeadersStart(this.b);
            this.e.a(t7Var);
            this.c.requestHeadersEnd(this.b, t7Var);
        } catch (IOException e) {
            this.c.requestFailed(this.b, e);
            a(e);
            throw e;
        }
    }

    public final class a extends gb {
        public boolean b;
        public long c;
        public long d;
        public boolean e;

        @Override // com.huawei.hms.network.embedded.gb, com.huawei.hms.network.embedded.yb
        public void write(bb bbVar, long j) throws IOException {
            if (this.e) {
                throw new IllegalStateException("closed");
            }
            long j2 = this.c;
            if (j2 == -1 || this.d + j <= j2) {
                try {
                    super.write(bbVar, j);
                    this.d += j;
                    return;
                } catch (IOException e) {
                    throw a(e);
                }
            }
            throw new ProtocolException("expected " + this.c + " bytes but received " + (this.d + j));
        }

        @Override // com.huawei.hms.network.embedded.gb, com.huawei.hms.network.embedded.yb, java.io.Flushable
        public void flush() throws IOException {
            try {
                super.flush();
            } catch (IOException e) {
                throw a(e);
            }
        }

        @Override // com.huawei.hms.network.embedded.gb, com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            if (this.e) {
                return;
            }
            this.e = true;
            long j = this.c;
            if (j != -1 && this.d != j) {
                throw new ProtocolException("unexpected end of stream");
            }
            try {
                super.close();
                a(null);
            } catch (IOException e) {
                throw a(e);
            }
        }

        @Nullable
        private IOException a(@Nullable IOException iOException) {
            if (this.b) {
                return iOException;
            }
            this.b = true;
            return v8.this.a(this.d, false, true, iOException);
        }

        public a(yb ybVar, long j) {
            super(ybVar);
            this.c = j;
        }
    }

    public void a() {
        this.e.cancel();
    }

    public final class b extends hb {
        public final long b;
        public long c;
        public boolean d;
        public boolean e;

        @Override // com.huawei.hms.network.embedded.hb, com.huawei.hms.network.embedded.zb
        public long read(bb bbVar, long j) throws IOException {
            if (this.e) {
                throw new IllegalStateException("closed");
            }
            try {
                long read = b().read(bbVar, j);
                if (read == -1) {
                    a(null);
                    return -1L;
                }
                long j2 = this.c + read;
                long j3 = this.b;
                if (j3 != -1 && j2 > j3) {
                    throw new ProtocolException("expected " + this.b + " bytes but received " + j2);
                }
                this.c = j2;
                if (j2 == j3) {
                    a(null);
                }
                return read;
            } catch (IOException e) {
                throw a(e);
            }
        }

        @Override // com.huawei.hms.network.embedded.hb, com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
            if (this.e) {
                return;
            }
            this.e = true;
            try {
                super.close();
                a(null);
            } catch (IOException e) {
                throw a(e);
            }
        }

        @Nullable
        public IOException a(@Nullable IOException iOException) {
            if (this.d) {
                return iOException;
            }
            this.d = true;
            return v8.this.a(this.c, true, false, iOException);
        }

        public b(zb zbVar, long j) {
            super(zbVar);
            this.b = j;
            if (j == 0) {
                a(null);
            }
        }
    }

    @Nullable
    public IOException a(long j, boolean z, boolean z2, @Nullable IOException iOException) {
        if (iOException != null) {
            a(iOException);
        }
        if (z2) {
            g7 g7Var = this.c;
            t6 t6Var = this.b;
            if (iOException != null) {
                g7Var.requestFailed(t6Var, iOException);
            } else {
                g7Var.requestBodyEnd(t6Var, j);
            }
        }
        if (z) {
            if (iOException != null) {
                this.c.responseFailed(this.b, iOException);
            } else {
                this.c.responseBodyEnd(this.b, j);
            }
        }
        return this.f5545a.exchangeMessageDone(this, z2, z, iOException);
    }

    public yb a(t7 t7Var, boolean z) throws IOException {
        this.f = z;
        long contentLength = t7Var.b().contentLength();
        this.c.requestBodyStart(this.b);
        return new a(this.e.a(t7Var, contentLength), contentLength);
    }

    public w7 a(v7 v7Var) throws IOException {
        try {
            this.c.responseBodyStart(this.b);
            String b2 = v7Var.b("Content-Type");
            long a2 = this.e.a(v7Var);
            return new l9(b2, a2, ob.a(new b(this.e.b(v7Var), a2)));
        } catch (IOException e) {
            this.c.responseFailed(this.b, e);
            a(e);
            throw e;
        }
    }

    @Nullable
    public v7.a a(boolean z) throws IOException {
        try {
            v7.a a2 = this.e.a(z);
            if (a2 != null) {
                c8.f5203a.a(a2, this);
            }
            return a2;
        } catch (IOException e) {
            this.c.responseFailed(this.b, e);
            a(e);
            throw e;
        }
    }

    public v8(d9 d9Var, t6 t6Var, g7 g7Var, w8 w8Var, g9 g9Var) {
        this.f5545a = d9Var;
        this.b = t6Var;
        this.c = g7Var;
        this.d = w8Var;
        this.e = g9Var;
    }
}
