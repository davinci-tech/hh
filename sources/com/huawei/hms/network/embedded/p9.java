package com.huawei.hms.network.embedded;

import android.support.v4.media.session.PlaybackStateCompat;
import com.huawei.hms.network.embedded.j7;
import com.huawei.hms.network.embedded.v7;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public final class p9 implements g9 {
    public static final int i = 0;
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 3;
    public static final int m = 4;
    public static final int n = 5;
    public static final int o = 6;
    public static final int p = 262144;
    public final q7 b;
    public final y8 c;
    public final db d;
    public final cb e;
    public int f = 0;
    public long g = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
    public j7 h;

    public boolean e() {
        return this.f == 6;
    }

    @Override // com.huawei.hms.network.embedded.g9
    public void d() throws IOException {
        this.e.flush();
    }

    @Override // com.huawei.hms.network.embedded.g9
    public void cancel() {
        y8 y8Var = this.c;
        if (y8Var != null) {
            y8Var.e();
        }
    }

    public void c(v7 v7Var) throws IOException {
        long a2 = i9.a(v7Var);
        if (a2 == -1) {
            return;
        }
        zb a3 = a(a2);
        f8.b(a3, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        a3.close();
    }

    @Override // com.huawei.hms.network.embedded.g9
    public void c() throws IOException {
        this.e.flush();
    }

    @Override // com.huawei.hms.network.embedded.g9
    public zb b(v7 v7Var) {
        if (!i9.b(v7Var)) {
            return a(0L);
        }
        if ("chunked".equalsIgnoreCase(v7Var.b("Transfer-Encoding"))) {
            return a(v7Var.H().k());
        }
        long a2 = i9.a(v7Var);
        return a2 != -1 ? a(a2) : h();
    }

    @Override // com.huawei.hms.network.embedded.g9
    public j7 b() {
        if (this.f != 6) {
            throw new IllegalStateException("too early; can't read the trailers yet");
        }
        j7 j7Var = this.h;
        return j7Var != null ? j7Var : f8.c;
    }

    @Override // com.huawei.hms.network.embedded.g9
    public void a(t7 t7Var) throws IOException {
        a(t7Var.e(), m9.a(t7Var, this.c.b().b().type()));
    }

    public void a(j7 j7Var, String str) throws IOException {
        if (this.f != 0) {
            throw new IllegalStateException("state: " + this.f);
        }
        this.e.a(str).a("\r\n");
        int d2 = j7Var.d();
        for (int i2 = 0; i2 < d2; i2++) {
            this.e.a(j7Var.a(i2)).a(": ").a(j7Var.b(i2)).a("\r\n");
        }
        this.e.a("\r\n");
        this.f = 1;
    }

    @Override // com.huawei.hms.network.embedded.g9
    public yb a(t7 t7Var, long j2) throws IOException {
        if (t7Var.b() != null && t7Var.b().isDuplex()) {
            throw new ProtocolException("Duplex connections are not supported for HTTP/1");
        }
        if ("chunked".equalsIgnoreCase(t7Var.a("Transfer-Encoding"))) {
            return f();
        }
        if (j2 != -1) {
            return g();
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // com.huawei.hms.network.embedded.g9
    public y8 a() {
        return this.c;
    }

    @Override // com.huawei.hms.network.embedded.g9
    public v7.a a(boolean z) throws IOException {
        int i2 = this.f;
        if (i2 != 1 && i2 != 3) {
            throw new IllegalStateException("state: " + this.f);
        }
        try {
            o9 a2 = o9.a(i());
            v7.a a3 = new v7.a().a(a2.f5401a).a(a2.b).a(a2.c).a(j());
            if (z && a2.b == 100) {
                return null;
            }
            if (a2.b == 100) {
                this.f = 3;
                return a3;
            }
            this.f = 4;
            return a3;
        } catch (EOFException e2) {
            y8 y8Var = this.c;
            throw new IOException("unexpected end of stream on " + (y8Var != null ? y8Var.b().a().l().r() : "unknown"), e2);
        }
    }

    @Override // com.huawei.hms.network.embedded.g9
    public long a(v7 v7Var) {
        if (!i9.b(v7Var)) {
            return 0L;
        }
        if ("chunked".equalsIgnoreCase(v7Var.b("Transfer-Encoding"))) {
            return -1L;
        }
        return i9.a(v7Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public j7 j() throws IOException {
        j7.a aVar = new j7.a();
        while (true) {
            String i2 = i();
            if (i2.length() == 0) {
                return aVar.a();
            }
            c8.f5203a.a(aVar, i2);
        }
    }

    private String i() throws IOException {
        String e2 = this.d.e(this.g);
        this.g -= e2.length();
        return e2;
    }

    private zb h() {
        if (this.f == 4) {
            this.f = 5;
            this.c.h();
            return new g();
        }
        throw new IllegalStateException("state: " + this.f);
    }

    private yb g() {
        if (this.f == 1) {
            this.f = 2;
            return new f();
        }
        throw new IllegalStateException("state: " + this.f);
    }

    private yb f() {
        if (this.f == 1) {
            this.f = 2;
            return new c();
        }
        throw new IllegalStateException("state: " + this.f);
    }

    public final class f implements yb {

        /* renamed from: a, reason: collision with root package name */
        public final ib f5421a;
        public boolean b;

        @Override // com.huawei.hms.network.embedded.yb
        public void write(bb bbVar, long j) throws IOException {
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            f8.a(bbVar.B(), 0L, j);
            p9.this.e.write(bbVar, j);
        }

        @Override // com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return this.f5421a;
        }

        @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
        public void flush() throws IOException {
            if (this.b) {
                return;
            }
            p9.this.e.flush();
        }

        @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            if (this.b) {
                return;
            }
            this.b = true;
            p9.this.a(this.f5421a);
            p9.this.f = 3;
        }

        public f() {
            this.f5421a = new ib(p9.this.e.timeout());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ib ibVar) {
        ac f2 = ibVar.f();
        ibVar.a(ac.d);
        f2.a();
        f2.b();
    }

    public abstract class b implements zb {

        /* renamed from: a, reason: collision with root package name */
        public final ib f5419a;
        public boolean b;

        @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return this.f5419a;
        }

        @Override // com.huawei.hms.network.embedded.zb
        public long read(bb bbVar, long j) throws IOException {
            try {
                return p9.this.d.read(bbVar, j);
            } catch (IOException e) {
                p9.this.c.h();
                b();
                throw e;
            }
        }

        public final void b() {
            if (p9.this.f == 6) {
                return;
            }
            if (p9.this.f != 5) {
                throw new IllegalStateException("state: " + p9.this.f);
            }
            p9.this.a(this.f5419a);
            p9.this.f = 6;
        }

        public b() {
            this.f5419a = new ib(p9.this.d.timeout());
        }
    }

    public final class c implements yb {

        /* renamed from: a, reason: collision with root package name */
        public final ib f5420a;
        public boolean b;

        @Override // com.huawei.hms.network.embedded.yb
        public void write(bb bbVar, long j) throws IOException {
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            p9.this.e.c(j);
            p9.this.e.a("\r\n");
            p9.this.e.write(bbVar, j);
            p9.this.e.a("\r\n");
        }

        @Override // com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return this.f5420a;
        }

        @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
        public void flush() throws IOException {
            synchronized (this) {
                if (this.b) {
                    return;
                }
                p9.this.e.flush();
            }
        }

        @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            synchronized (this) {
                if (this.b) {
                    return;
                }
                this.b = true;
                p9.this.e.a("0\r\n\r\n");
                p9.this.a(this.f5420a);
                p9.this.f = 3;
            }
        }

        public c() {
            this.f5420a = new ib(p9.this.e.timeout());
        }
    }

    private zb a(m7 m7Var) {
        if (this.f == 4) {
            this.f = 5;
            return new d(m7Var);
        }
        throw new IllegalStateException("state: " + this.f);
    }

    public class d extends b {
        public static final long h = -1;
        public final m7 d;
        public long e;
        public boolean f;

        @Override // com.huawei.hms.network.embedded.p9.b, com.huawei.hms.network.embedded.zb
        public long read(bb bbVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            if (!this.f) {
                return -1L;
            }
            long j2 = this.e;
            if (j2 == 0 || j2 == -1) {
                c();
                if (!this.f) {
                    return -1L;
                }
            }
            long read = super.read(bbVar, Math.min(j, this.e));
            if (read != -1) {
                this.e -= read;
                return read;
            }
            p9.this.c.h();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            b();
            throw protocolException;
        }

        @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
            if (this.b) {
                return;
            }
            if (this.f && !f8.a(this, 100, TimeUnit.MILLISECONDS)) {
                p9.this.c.h();
                b();
            }
            this.b = true;
        }

        private void c() throws IOException {
            if (this.e != -1) {
                p9.this.d.n();
            }
            try {
                this.e = p9.this.d.k();
                String trim = p9.this.d.n().trim();
                if (this.e < 0 || !(trim.isEmpty() || trim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.e + trim + "\"");
                }
                if (this.e == 0) {
                    this.f = false;
                    p9 p9Var = p9.this;
                    p9Var.h = p9Var.j();
                    i9.a(p9.this.b.i(), this.d, p9.this.h);
                    b();
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public d(m7 m7Var) {
            super();
            this.e = -1L;
            this.f = true;
            this.d = m7Var;
        }
    }

    public class g extends b {
        public boolean d;

        @Override // com.huawei.hms.network.embedded.p9.b, com.huawei.hms.network.embedded.zb
        public long read(bb bbVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            if (this.d) {
                return -1L;
            }
            long read = super.read(bbVar, j);
            if (read != -1) {
                return read;
            }
            this.d = true;
            b();
            return -1L;
        }

        @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
            if (this.b) {
                return;
            }
            if (!this.d) {
                b();
            }
            this.b = true;
        }

        public g() {
            super();
        }
    }

    private zb a(long j2) {
        if (this.f == 4) {
            this.f = 5;
            return new e(j2);
        }
        throw new IllegalStateException("state: " + this.f);
    }

    public class e extends b {
        public long d;

        @Override // com.huawei.hms.network.embedded.p9.b, com.huawei.hms.network.embedded.zb
        public long read(bb bbVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            long j2 = this.d;
            if (j2 == 0) {
                return -1L;
            }
            long read = super.read(bbVar, Math.min(j2, j));
            if (read == -1) {
                p9.this.c.h();
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                b();
                throw protocolException;
            }
            long j3 = this.d - read;
            this.d = j3;
            if (j3 == 0) {
                b();
            }
            return read;
        }

        @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
            if (this.b) {
                return;
            }
            if (this.d != 0 && !f8.a(this, 100, TimeUnit.MILLISECONDS)) {
                p9.this.c.h();
                b();
            }
            this.b = true;
        }

        public e(long j) {
            super();
            this.d = j;
            if (j == 0) {
                b();
            }
        }
    }

    public p9(q7 q7Var, y8 y8Var, db dbVar, cb cbVar) {
        this.b = q7Var;
        this.c = y8Var;
        this.d = dbVar;
        this.e = cbVar;
    }
}
