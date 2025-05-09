package com.huawei.hms.network.embedded;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class y9 {
    public static final /* synthetic */ boolean m = true;

    /* renamed from: a, reason: collision with root package name */
    public long f5583a = 0;
    public long b;
    public final int c;
    public final v9 d;
    public final Deque<j7> e;
    public boolean f;
    public final b g;
    public final a h;
    public final c i;
    public final c j;

    @Nullable
    public r9 k;

    @Nullable
    public IOException l;

    public ac n() {
        return this.j;
    }

    public void m() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public j7 l() throws IOException {
        j7 j7Var;
        synchronized (this) {
            r9 r9Var = this.k;
            if (r9Var != null) {
                IOException iOException = this.l;
                if (iOException != null) {
                    throw iOException;
                }
                throw new da(r9Var);
            }
            b bVar = this.g;
            if (!bVar.f || !bVar.f5585a.i() || !this.g.b.i()) {
                throw new IllegalStateException("too early; can't read the trailers yet");
            }
            j7Var = this.g.d != null ? this.g.d : f8.c;
        }
        return j7Var;
    }

    public j7 k() throws IOException {
        j7 removeFirst;
        synchronized (this) {
            this.i.f();
            while (this.e.isEmpty() && this.k == null) {
                try {
                    m();
                } catch (Throwable th) {
                    this.i.i();
                    throw th;
                }
            }
            this.i.i();
            if (this.e.isEmpty()) {
                IOException iOException = this.l;
                if (iOException != null) {
                    throw iOException;
                }
                throw new da(this.k);
            }
            removeFirst = this.e.removeFirst();
        }
        return removeFirst;
    }

    public ac j() {
        return this.i;
    }

    public boolean i() {
        synchronized (this) {
            if (this.k != null) {
                return false;
            }
            b bVar = this.g;
            if (bVar.f || bVar.e) {
                a aVar = this.h;
                if (aVar.d || aVar.c) {
                    if (this.f) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public boolean h() {
        return this.d.f5546a == ((this.c & 1) == 1);
    }

    public zb g() {
        return this.g;
    }

    public yb f() {
        synchronized (this) {
            if (!this.f && !h()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.h;
    }

    public int e() {
        return this.c;
    }

    public r9 d() {
        r9 r9Var;
        synchronized (this) {
            r9Var = this.k;
        }
        return r9Var;
    }

    public v9 c() {
        return this.d;
    }

    public void b(r9 r9Var) {
        synchronized (this) {
            if (this.k == null) {
                this.k = r9Var;
                notifyAll();
            }
        }
    }

    public final class b implements zb {
        public static final /* synthetic */ boolean h = true;

        /* renamed from: a, reason: collision with root package name */
        public final bb f5585a = new bb();
        public final bb b = new bb();
        public final long c;
        public j7 d;
        public boolean e;
        public boolean f;

        @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return y9.this.i;
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00a2  */
        @Override // com.huawei.hms.network.embedded.zb
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long read(com.huawei.hms.network.embedded.bb r12, long r13) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 206
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.y9.b.read(com.huawei.hms.network.embedded.bb, long):long");
        }

        @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
            long B;
            synchronized (y9.this) {
                this.e = true;
                B = this.b.B();
                this.b.s();
                y9.this.notifyAll();
            }
            if (B > 0) {
                a(B);
            }
            y9.this.a();
        }

        public void a(db dbVar, long j) throws IOException {
            boolean z;
            boolean z2;
            long j2;
            if (!h && Thread.holdsLock(y9.this)) {
                throw new AssertionError();
            }
            while (j > 0) {
                synchronized (y9.this) {
                    z = this.f;
                    z2 = this.b.B() + j > this.c;
                }
                if (z2) {
                    dbVar.skip(j);
                    y9.this.a(r9.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    dbVar.skip(j);
                    return;
                }
                long read = dbVar.read(this.f5585a, j);
                if (read == -1) {
                    throw new EOFException();
                }
                j -= read;
                synchronized (y9.this) {
                    if (this.e) {
                        j2 = this.f5585a.B();
                        this.f5585a.s();
                    } else {
                        boolean z3 = this.b.B() == 0;
                        this.b.a((zb) this.f5585a);
                        if (z3) {
                            y9.this.notifyAll();
                        }
                        j2 = 0;
                    }
                }
                if (j2 > 0) {
                    a(j2);
                }
            }
        }

        private void a(long j) {
            if (!h && Thread.holdsLock(y9.this)) {
                throw new AssertionError();
            }
            y9.this.d.k(j);
        }

        public b(long j) {
            this.c = j;
        }
    }

    public void b() throws IOException {
        a aVar = this.h;
        if (aVar.c) {
            throw new IOException("stream closed");
        }
        if (aVar.d) {
            throw new IOException("stream finished");
        }
        r9 r9Var = this.k;
        if (r9Var != null) {
            IOException iOException = this.l;
            if (iOException == null) {
                throw new da(r9Var);
            }
        }
    }

    public void a(List<s9> list, boolean z, boolean z2) throws IOException {
        if (!m && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (list == null) {
            throw new NullPointerException("headers == null");
        }
        synchronized (this) {
            this.f = true;
            if (z) {
                this.h.d = true;
            }
        }
        if (!z2) {
            synchronized (this.d) {
                z2 = this.d.s == 0;
            }
        }
        this.d.a(this.c, z, list);
        if (z2) {
            this.d.flush();
        }
    }

    public void a(r9 r9Var, @Nullable IOException iOException) throws IOException {
        if (b(r9Var, iOException)) {
            this.d.b(this.c, r9Var);
        }
    }

    public final class a implements yb {
        public static final long f = 16384;
        public static final /* synthetic */ boolean g = true;

        /* renamed from: a, reason: collision with root package name */
        public final bb f5584a = new bb();
        public j7 b;
        public boolean c;
        public boolean d;

        @Override // com.huawei.hms.network.embedded.yb
        public void write(bb bbVar, long j) throws IOException {
            if (!g && Thread.holdsLock(y9.this)) {
                throw new AssertionError();
            }
            this.f5584a.write(bbVar, j);
            while (this.f5584a.B() >= 16384) {
                a(false);
            }
        }

        @Override // com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return y9.this.j;
        }

        @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
        public void flush() throws IOException {
            if (!g && Thread.holdsLock(y9.this)) {
                throw new AssertionError();
            }
            synchronized (y9.this) {
                y9.this.b();
            }
            while (this.f5584a.B() > 0) {
                a(false);
                y9.this.d.flush();
            }
        }

        @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            if (!g && Thread.holdsLock(y9.this)) {
                throw new AssertionError();
            }
            synchronized (y9.this) {
                if (this.c) {
                    return;
                }
                if (!y9.this.h.d) {
                    boolean z = this.f5584a.B() > 0;
                    if (this.b != null) {
                        while (this.f5584a.B() > 0) {
                            a(false);
                        }
                        y9 y9Var = y9.this;
                        y9Var.d.a(y9Var.c, true, f8.a(this.b));
                    } else if (z) {
                        while (this.f5584a.B() > 0) {
                            a(true);
                        }
                    } else {
                        y9 y9Var2 = y9.this;
                        y9Var2.d.a(y9Var2.c, true, (bb) null, 0L);
                    }
                }
                synchronized (y9.this) {
                    this.c = true;
                }
                y9.this.d.flush();
                y9.this.a();
            }
        }

        private void a(boolean z) throws IOException {
            long min;
            boolean z2;
            synchronized (y9.this) {
                y9.this.j.f();
                while (y9.this.b <= 0 && !this.d && !this.c && y9.this.k == null) {
                    try {
                        y9.this.m();
                    } finally {
                        y9.this.j.i();
                    }
                }
                y9.this.j.i();
                y9.this.b();
                min = Math.min(y9.this.b, this.f5584a.B());
                y9.this.b -= min;
            }
            y9.this.j.f();
            if (z) {
                try {
                    if (min == this.f5584a.B()) {
                        z2 = true;
                        y9.this.d.a(y9.this.c, z2, this.f5584a, min);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            z2 = false;
            y9.this.d.a(y9.this.c, z2, this.f5584a, min);
        }

        public a() {
        }
    }

    public void a(r9 r9Var) {
        if (b(r9Var, null)) {
            this.d.c(this.c, r9Var);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0029 A[Catch: all -> 0x003f, TryCatch #0 {, blocks: (B:10:0x0012, B:14:0x001a, B:16:0x0029, B:17:0x002d, B:18:0x0034, B:24:0x0020), top: B:9:0x0012 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.huawei.hms.network.embedded.j7 r3, boolean r4) {
        /*
            r2 = this;
            boolean r0 = com.huawei.hms.network.embedded.y9.m
            if (r0 != 0) goto L11
            boolean r0 = java.lang.Thread.holdsLock(r2)
            if (r0 != 0) goto Lb
            goto L11
        Lb:
            java.lang.AssertionError r3 = new java.lang.AssertionError
            r3.<init>()
            throw r3
        L11:
            monitor-enter(r2)
            boolean r0 = r2.f     // Catch: java.lang.Throwable -> L3f
            r1 = 1
            if (r0 == 0) goto L20
            if (r4 != 0) goto L1a
            goto L20
        L1a:
            com.huawei.hms.network.embedded.y9$b r0 = r2.g     // Catch: java.lang.Throwable -> L3f
            com.huawei.hms.network.embedded.y9.b.a(r0, r3)     // Catch: java.lang.Throwable -> L3f
            goto L27
        L20:
            r2.f = r1     // Catch: java.lang.Throwable -> L3f
            java.util.Deque<com.huawei.hms.network.embedded.j7> r0 = r2.e     // Catch: java.lang.Throwable -> L3f
            r0.add(r3)     // Catch: java.lang.Throwable -> L3f
        L27:
            if (r4 == 0) goto L2d
            com.huawei.hms.network.embedded.y9$b r3 = r2.g     // Catch: java.lang.Throwable -> L3f
            r3.f = r1     // Catch: java.lang.Throwable -> L3f
        L2d:
            boolean r3 = r2.i()     // Catch: java.lang.Throwable -> L3f
            r2.notifyAll()     // Catch: java.lang.Throwable -> L3f
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3f
            if (r3 != 0) goto L3e
            com.huawei.hms.network.embedded.v9 r3 = r2.d
            int r4 = r2.c
            r3.f(r4)
        L3e:
            return
        L3f:
            r3 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3f
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.y9.a(com.huawei.hms.network.embedded.j7, boolean):void");
    }

    public void a(j7 j7Var) {
        synchronized (this) {
            if (this.h.d) {
                throw new IllegalStateException("already finished");
            }
            if (j7Var.d() == 0) {
                throw new IllegalArgumentException("trailers.size() == 0");
            }
            this.h.b = j7Var;
        }
    }

    public void a(db dbVar, int i) throws IOException {
        if (!m && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        this.g.a(dbVar, i);
    }

    public class c extends za {
        @Override // com.huawei.hms.network.embedded.za
        public void timedOut() {
            y9.this.a(r9.CANCEL);
            y9.this.d.v();
        }

        @Override // com.huawei.hms.network.embedded.za
        public IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void i() throws IOException {
            if (g()) {
                throw newTimeoutException(null);
            }
        }

        public c() {
        }
    }

    public void a(long j) {
        this.b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public void a() throws IOException {
        boolean z;
        boolean i;
        if (!m && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            z = !this.g.f && this.g.e && (this.h.d || this.h.c);
            i = i();
        }
        if (z) {
            a(r9.CANCEL, (IOException) null);
        } else {
            if (i) {
                return;
            }
            this.d.f(this.c);
        }
    }

    private boolean b(r9 r9Var, @Nullable IOException iOException) {
        if (!m && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            if (this.k != null) {
                return false;
            }
            if (this.g.f && this.h.d) {
                return false;
            }
            this.k = r9Var;
            this.l = iOException;
            notifyAll();
            this.d.f(this.c);
            return true;
        }
    }

    public y9(int i, v9 v9Var, boolean z, boolean z2, @Nullable j7 j7Var) {
        ArrayDeque arrayDeque = new ArrayDeque();
        this.e = arrayDeque;
        this.i = new c();
        this.j = new c();
        if (v9Var == null) {
            throw new NullPointerException("connection == null");
        }
        this.c = i;
        this.d = v9Var;
        this.b = v9Var.u.c();
        b bVar = new b(v9Var.t.c());
        this.g = bVar;
        a aVar = new a();
        this.h = aVar;
        bVar.f = z2;
        aVar.d = z;
        if (j7Var != null) {
            arrayDeque.add(j7Var);
        }
        if (h() && j7Var != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        }
        if (!h() && j7Var == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }
}
