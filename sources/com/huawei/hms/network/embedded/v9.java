package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.x9;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class v9 implements Closeable {
    public static final int A = 1;
    public static final int B = 2;
    public static final int C = 3;
    public static final long D = 1000000000;
    public static final ExecutorService E = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), f8.a("OkHttp Http2Connection", true));
    public static final /* synthetic */ boolean F = true;
    public static final int z = 16777216;

    /* renamed from: a, reason: collision with root package name */
    public final boolean f5546a;
    public final j b;
    public final String d;
    public int e;
    public int f;
    public boolean g;
    public final ScheduledExecutorService h;
    public final ExecutorService i;
    public final ba j;
    public long s;
    public final ca u;
    public final Socket v;
    public final z9 w;
    public final l x;
    public final Set<Integer> y;
    public final Map<Integer, y9> c = new LinkedHashMap();
    public long k = 0;
    public long l = 0;
    public long m = 0;
    public long n = 0;
    public long o = 0;
    public long p = 0;
    public long q = 0;
    public long r = 0;
    public ca t = new ca();

    public boolean e(int i2) {
        return i2 != 0 && (i2 & 1) == 0;
    }

    public void y() throws InterruptedException {
        x();
        s();
    }

    public void x() {
        synchronized (this) {
            this.o++;
        }
        a(false, 3, 1330343787);
    }

    public void w() throws IOException {
        a(true);
    }

    public void v() {
        synchronized (this) {
            long j2 = this.n;
            long j3 = this.m;
            if (j2 < j3) {
                return;
            }
            this.m = j3 + 1;
            this.q = System.nanoTime() + 1000000000;
            try {
                this.h.execute(new c("OkHttp %s ping", this.d));
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    public int u() {
        int size;
        synchronized (this) {
            size = this.c.size();
        }
        return size;
    }

    public int t() {
        int b2;
        synchronized (this) {
            b2 = this.u.b(Integer.MAX_VALUE);
        }
        return b2;
    }

    public void s() throws InterruptedException {
        synchronized (this) {
            while (this.p < this.o) {
                wait();
            }
        }
    }

    public void k(long j2) {
        synchronized (this) {
            long j3 = this.r + j2;
            this.r = j3;
            if (j3 >= this.t.c() / 2) {
                a(0, this.r);
                this.r = 0L;
            }
        }
    }

    public boolean j(long j2) {
        synchronized (this) {
            if (this.g) {
                return false;
            }
            if (this.n < this.m) {
                if (j2 >= this.q) {
                    return false;
                }
            }
            return true;
        }
    }

    public void flush() throws IOException {
        this.w.flush();
    }

    public y9 f(int i2) {
        y9 remove;
        synchronized (this) {
            remove = this.c.remove(Integer.valueOf(i2));
            notifyAll();
        }
        return remove;
    }

    public y9 d(int i2) {
        y9 y9Var;
        synchronized (this) {
            y9Var = this.c.get(Integer.valueOf(i2));
        }
        return y9Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a(r9.NO_ERROR, r9.CANCEL, (IOException) null);
    }

    public void c(int i2, r9 r9Var) {
        try {
            this.h.execute(new a("OkHttp %s stream %d", new Object[]{this.d, Integer.valueOf(i2)}, i2, r9Var));
        } catch (RejectedExecutionException unused) {
        }
    }

    public void b(int i2, r9 r9Var) throws IOException {
        this.w.a(i2, r9Var);
    }

    public y9 b(int i2, List<s9> list, boolean z2) throws IOException {
        if (this.f5546a) {
            throw new IllegalStateException("Client cannot push requests.");
        }
        return c(i2, list, z2);
    }

    public void a(boolean z2, int i2, int i3) {
        try {
            this.w.a(z2, i2, i3);
        } catch (IOException e2) {
            a(e2);
        }
    }

    public void a(boolean z2) throws IOException {
        if (z2) {
            this.w.s();
            this.w.b(this.t);
            if (this.t.c() != 65535) {
                this.w.a(0, r5 - 65535);
            }
        }
        new Thread(this.x).start();
    }

    public void a(r9 r9Var, r9 r9Var2, @Nullable IOException iOException) {
        y9[] y9VarArr;
        if (!F && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        try {
            a(r9Var);
        } catch (IOException unused) {
        }
        synchronized (this) {
            if (this.c.isEmpty()) {
                y9VarArr = null;
            } else {
                y9VarArr = (y9[]) this.c.values().toArray(new y9[this.c.size()]);
                this.c.clear();
            }
        }
        if (y9VarArr != null) {
            for (y9 y9Var : y9VarArr) {
                try {
                    y9Var.a(r9Var2, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.w.close();
        } catch (IOException unused3) {
        }
        try {
            this.v.close();
        } catch (IOException unused4) {
        }
        this.h.shutdown();
        this.i.shutdown();
    }

    public void a(r9 r9Var) throws IOException {
        synchronized (this.w) {
            synchronized (this) {
                if (this.g) {
                    return;
                }
                this.g = true;
                this.w.a(this.e, r9Var, f8.f5251a);
            }
        }
    }

    public void a(ca caVar) throws IOException {
        synchronized (this.w) {
            synchronized (this) {
                if (this.g) {
                    throw new q9();
                }
                this.t.a(caVar);
            }
            this.w.b(caVar);
        }
    }

    public void a(int i2, boolean z2, List<s9> list) throws IOException {
        this.w.a(z2, i2, list);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0030, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r4), r8.w.t());
        r6 = r2;
        r8.s -= r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(int r9, boolean r10, com.huawei.hms.network.embedded.bb r11, long r12) throws java.io.IOException {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            com.huawei.hms.network.embedded.z9 r12 = r8.w
            r12.a(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L67
            monitor-enter(r8)
        L12:
            long r4 = r8.s     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 > 0) goto L30
            java.util.Map<java.lang.Integer, com.huawei.hms.network.embedded.y9> r2 = r8.c     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            if (r2 == 0) goto L28
            r8.wait()     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            goto L12
        L28:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            throw r9     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
        L30:
            long r4 = java.lang.Math.min(r12, r4)     // Catch: java.lang.Throwable -> L56
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L56
            com.huawei.hms.network.embedded.z9 r4 = r8.w     // Catch: java.lang.Throwable -> L56
            int r4 = r4.t()     // Catch: java.lang.Throwable -> L56
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L56
            long r4 = r8.s     // Catch: java.lang.Throwable -> L56
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L56
            long r4 = r4 - r6
            r8.s = r4     // Catch: java.lang.Throwable -> L56
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            long r12 = r12 - r6
            com.huawei.hms.network.embedded.z9 r4 = r8.w
            if (r10 == 0) goto L51
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L51
            r5 = 1
            goto L52
        L51:
            r5 = r3
        L52:
            r4.a(r5, r9, r11, r2)
            goto Ld
        L56:
            r9 = move-exception
            goto L65
        L58:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L56
            r9.interrupt()     // Catch: java.lang.Throwable -> L56
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L56
            r9.<init>()     // Catch: java.lang.Throwable -> L56
            throw r9     // Catch: java.lang.Throwable -> L56
        L65:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            throw r9
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.v9.a(int, boolean, com.huawei.hms.network.embedded.bb, long):void");
    }

    public void a(int i2, List<s9> list, boolean z2) {
        try {
            a(new e("OkHttp %s Push Headers[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, list, z2));
        } catch (RejectedExecutionException unused) {
        }
    }

    public void a(int i2, List<s9> list) {
        synchronized (this) {
            if (this.y.contains(Integer.valueOf(i2))) {
                c(i2, r9.PROTOCOL_ERROR);
                return;
            }
            this.y.add(Integer.valueOf(i2));
            try {
                a(new d("OkHttp %s Push Request[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, list));
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    public void a(int i2, r9 r9Var) {
        a(new g("OkHttp %s Push Reset[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, r9Var));
    }

    public void a(int i2, db dbVar, int i3, boolean z2) throws IOException {
        bb bbVar = new bb();
        long j2 = i3;
        dbVar.i(j2);
        dbVar.read(bbVar, j2);
        if (bbVar.B() == j2) {
            a(new f("OkHttp %s Push Data[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, bbVar, i3, z2));
            return;
        }
        throw new IOException(bbVar.B() + " != " + i3);
    }

    public void a(int i2, long j2) {
        try {
            this.h.execute(new b("OkHttp Window Update %s stream %d", new Object[]{this.d, Integer.valueOf(i2)}, i2, j2));
        } catch (RejectedExecutionException unused) {
        }
    }

    public y9 a(List<s9> list, boolean z2) throws IOException {
        return c(0, list, z2);
    }

    public class l extends d8 implements x9.b {
        public final x9 b;

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a() {
        }

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a(int i, int i2, int i3, boolean z) {
        }

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a(int i, String str, eb ebVar, String str2, int i2, long j) {
        }

        public void b(boolean z, ca caVar) {
            y9[] y9VarArr;
            long j;
            synchronized (v9.this.w) {
                synchronized (v9.this) {
                    int c2 = v9.this.u.c();
                    if (z) {
                        v9.this.u.a();
                    }
                    v9.this.u.a(caVar);
                    int c3 = v9.this.u.c();
                    y9VarArr = null;
                    if (c3 == -1 || c3 == c2) {
                        j = 0;
                    } else {
                        j = c3 - c2;
                        if (!v9.this.c.isEmpty()) {
                            y9VarArr = (y9[]) v9.this.c.values().toArray(new y9[v9.this.c.size()]);
                        }
                    }
                }
                try {
                    v9.this.w.a(v9.this.u);
                } catch (IOException e) {
                    v9.this.a(e);
                }
            }
            if (y9VarArr != null) {
                for (y9 y9Var : y9VarArr) {
                    synchronized (y9Var) {
                        y9Var.a(j);
                    }
                }
            }
            v9.E.execute(new c("OkHttp %s settings", v9.this.d));
        }

        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            Throwable th;
            NullPointerException e;
            r9 r9Var;
            r9 r9Var2;
            r9 r9Var3 = r9.INTERNAL_ERROR;
            IOException e2 = null;
            try {
                try {
                    this.b.a(this);
                    while (this.b.a(false, (x9.b) this)) {
                    }
                    r9Var = r9.NO_ERROR;
                    try {
                        r9Var2 = r9.CANCEL;
                    } catch (IOException e3) {
                        e2 = e3;
                        r9Var = r9.PROTOCOL_ERROR;
                        r9Var2 = r9.PROTOCOL_ERROR;
                        v9.this.a(r9Var, r9Var2, e2);
                        f8.a(this.b);
                    } catch (NullPointerException e4) {
                        e = e4;
                        ma.f().a(5, e.getMessage(), e);
                        v9.this.a(r9Var, r9Var3, new IOException(e));
                        f8.a(this.b);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    v9.this.a(r9Var3, r9Var3, (IOException) null);
                    f8.a(this.b);
                    throw th;
                }
            } catch (IOException e5) {
                e2 = e5;
            } catch (NullPointerException e6) {
                e = e6;
                r9Var = r9Var3;
            } catch (Throwable th3) {
                th = th3;
                v9.this.a(r9Var3, r9Var3, (IOException) null);
                f8.a(this.b);
                throw th;
            }
            v9.this.a(r9Var, r9Var2, e2);
            f8.a(this.b);
        }

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a(boolean z, ca caVar) {
            try {
                v9.this.h.execute(new b("OkHttp %s ACK Settings", new Object[]{v9.this.d}, z, caVar));
            } catch (RejectedExecutionException unused) {
            }
        }

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a(boolean z, int i, db dbVar, int i2) throws IOException {
            if (v9.this.e(i)) {
                v9.this.a(i, dbVar, i2, z);
                return;
            }
            y9 d = v9.this.d(i);
            if (d == null) {
                v9.this.c(i, r9.PROTOCOL_ERROR);
                long j = i2;
                v9.this.k(j);
                dbVar.skip(j);
                return;
            }
            d.a(dbVar, i2);
            if (z) {
                d.a(f8.c, true);
            }
        }

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a(boolean z, int i, int i2, List<s9> list) {
            if (v9.this.e(i)) {
                v9.this.a(i, list, z);
                return;
            }
            synchronized (v9.this) {
                y9 d = v9.this.d(i);
                if (d != null) {
                    d.a(f8.b(list), z);
                    return;
                }
                if (v9.this.g) {
                    return;
                }
                if (i <= v9.this.e) {
                    return;
                }
                if (i % 2 == v9.this.f % 2) {
                    return;
                }
                y9 y9Var = new y9(i, v9.this, false, z, f8.b(list));
                v9.this.e = i;
                v9.this.c.put(Integer.valueOf(i), y9Var);
                v9.E.execute(new a("OkHttp %s stream %d", new Object[]{v9.this.d, Integer.valueOf(i)}, y9Var));
            }
        }

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a(boolean z, int i, int i2) {
            if (!z) {
                try {
                    v9.this.h.execute(v9.this.new k(true, i, i2));
                    return;
                } catch (RejectedExecutionException unused) {
                    return;
                }
            }
            synchronized (v9.this) {
                try {
                    if (i == 1) {
                        v9.b(v9.this);
                    } else if (i == 2) {
                        v9.g(v9.this);
                    } else if (i == 3) {
                        v9.h(v9.this);
                        v9.this.notifyAll();
                    }
                } finally {
                }
            }
        }

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a(int i, r9 r9Var, eb ebVar) {
            y9[] y9VarArr;
            ebVar.j();
            synchronized (v9.this) {
                y9VarArr = (y9[]) v9.this.c.values().toArray(new y9[v9.this.c.size()]);
                v9.this.g = true;
            }
            for (y9 y9Var : y9VarArr) {
                if (y9Var.e() > i && y9Var.h()) {
                    y9Var.b(r9.REFUSED_STREAM);
                    v9.this.f(y9Var.e());
                }
            }
        }

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a(int i, r9 r9Var) {
            if (v9.this.e(i)) {
                v9.this.a(i, r9Var);
                return;
            }
            y9 f = v9.this.f(i);
            if (f != null) {
                f.b(r9Var);
            }
        }

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a(int i, long j) {
            v9 v9Var = v9.this;
            if (i == 0) {
                synchronized (v9Var) {
                    v9.this.s += j;
                    v9.this.notifyAll();
                }
                return;
            }
            y9 d = v9Var.d(i);
            if (d != null) {
                synchronized (d) {
                    d.a(j);
                }
            }
        }

        @Override // com.huawei.hms.network.embedded.x9.b
        public void a(int i, int i2, List<s9> list) {
            v9.this.a(i2, list);
        }

        public class a extends d8 {
            public final /* synthetic */ y9 b;

            @Override // com.huawei.hms.network.embedded.d8
            public void b() {
                try {
                    v9.this.b.a(this.b);
                } catch (IOException e) {
                    ma.f().a(4, "Http2Connection.Listener failure for " + v9.this.d, e);
                    try {
                        this.b.a(r9.PROTOCOL_ERROR, e);
                    } catch (IOException unused) {
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str, Object[] objArr, y9 y9Var) {
                super(str, objArr);
                this.b = y9Var;
            }
        }

        public class b extends d8 {
            public final /* synthetic */ boolean b;
            public final /* synthetic */ ca c;

            @Override // com.huawei.hms.network.embedded.d8
            public void b() {
                l.this.b(this.b, this.c);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(String str, Object[] objArr, boolean z, ca caVar) {
                super(str, objArr);
                this.b = z;
                this.c = caVar;
            }
        }

        public class c extends d8 {
            @Override // com.huawei.hms.network.embedded.d8
            public void b() {
                v9 v9Var = v9.this;
                v9Var.b.a(v9Var);
            }

            public c(String str, Object... objArr) {
                super(str, objArr);
            }
        }

        public l(x9 x9Var) {
            super("OkHttp %s", v9.this.d);
            this.b = x9Var;
        }
    }

    public static /* synthetic */ long h(v9 v9Var) {
        long j2 = v9Var.p;
        v9Var.p = 1 + j2;
        return j2;
    }

    public static /* synthetic */ long g(v9 v9Var) {
        long j2 = v9Var.n;
        v9Var.n = 1 + j2;
        return j2;
    }

    public static /* synthetic */ long d(v9 v9Var) {
        long j2 = v9Var.k;
        v9Var.k = 1 + j2;
        return j2;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0041 A[Catch: all -> 0x0073, TryCatch #0 {, blocks: (B:6:0x0006, B:8:0x000d, B:9:0x0012, B:11:0x0016, B:13:0x0029, B:15:0x0031, B:19:0x003b, B:21:0x0041, B:22:0x004a, B:36:0x006d, B:37:0x0072), top: B:5:0x0006, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.network.embedded.y9 c(int r11, java.util.List<com.huawei.hms.network.embedded.s9> r12, boolean r13) throws java.io.IOException {
        /*
            r10 = this;
            r6 = r13 ^ 1
            com.huawei.hms.network.embedded.z9 r7 = r10.w
            monitor-enter(r7)
            monitor-enter(r10)     // Catch: java.lang.Throwable -> L76
            int r0 = r10.f     // Catch: java.lang.Throwable -> L73
            r1 = 1073741823(0x3fffffff, float:1.9999999)
            if (r0 <= r1) goto L12
            com.huawei.hms.network.embedded.r9 r0 = com.huawei.hms.network.embedded.r9.REFUSED_STREAM     // Catch: java.lang.Throwable -> L73
            r10.a(r0)     // Catch: java.lang.Throwable -> L73
        L12:
            boolean r0 = r10.g     // Catch: java.lang.Throwable -> L73
            if (r0 != 0) goto L6d
            int r8 = r10.f     // Catch: java.lang.Throwable -> L73
            int r0 = r8 + 2
            r10.f = r0     // Catch: java.lang.Throwable -> L73
            com.huawei.hms.network.embedded.y9 r9 = new com.huawei.hms.network.embedded.y9     // Catch: java.lang.Throwable -> L73
            r4 = 0
            r5 = 0
            r0 = r9
            r1 = r8
            r2 = r10
            r3 = r6
            r0.<init>(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L73
            if (r13 == 0) goto L3a
            long r0 = r10.s     // Catch: java.lang.Throwable -> L73
            r2 = 0
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 == 0) goto L3a
            long r0 = r9.b     // Catch: java.lang.Throwable -> L73
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 != 0) goto L38
            goto L3a
        L38:
            r13 = 0
            goto L3b
        L3a:
            r13 = 1
        L3b:
            boolean r0 = r9.i()     // Catch: java.lang.Throwable -> L73
            if (r0 == 0) goto L4a
            java.util.Map<java.lang.Integer, com.huawei.hms.network.embedded.y9> r0 = r10.c     // Catch: java.lang.Throwable -> L73
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L73
            r0.put(r1, r9)     // Catch: java.lang.Throwable -> L73
        L4a:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L73
            if (r11 != 0) goto L53
            com.huawei.hms.network.embedded.z9 r11 = r10.w     // Catch: java.lang.Throwable -> L76
            r11.a(r6, r8, r12)     // Catch: java.lang.Throwable -> L76
            goto L5c
        L53:
            boolean r0 = r10.f5546a     // Catch: java.lang.Throwable -> L76
            if (r0 != 0) goto L65
            com.huawei.hms.network.embedded.z9 r0 = r10.w     // Catch: java.lang.Throwable -> L76
            r0.a(r11, r8, r12)     // Catch: java.lang.Throwable -> L76
        L5c:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L76
            if (r13 == 0) goto L64
            com.huawei.hms.network.embedded.z9 r11 = r10.w
            r11.flush()
        L64:
            return r9
        L65:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L76
            java.lang.String r12 = "client streams shouldn't have associated stream IDs"
            r11.<init>(r12)     // Catch: java.lang.Throwable -> L76
            throw r11     // Catch: java.lang.Throwable -> L76
        L6d:
            com.huawei.hms.network.embedded.q9 r11 = new com.huawei.hms.network.embedded.q9     // Catch: java.lang.Throwable -> L73
            r11.<init>()     // Catch: java.lang.Throwable -> L73
            throw r11     // Catch: java.lang.Throwable -> L73
        L73:
            r11 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L73
            throw r11     // Catch: java.lang.Throwable -> L76
        L76:
            r11 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L76
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.v9.c(int, java.util.List, boolean):com.huawei.hms.network.embedded.y9");
    }

    public static class h {

        /* renamed from: a, reason: collision with root package name */
        public Socket f5547a;
        public String b;
        public db c;
        public cb d;
        public j e = j.f5548a;
        public ba f = ba.f5190a;
        public boolean g;
        public int h;

        public v9 a() {
            return new v9(this);
        }

        public h a(Socket socket, String str, db dbVar, cb cbVar) {
            this.f5547a = socket;
            this.b = str;
            this.c = dbVar;
            this.d = cbVar;
            return this;
        }

        public h a(Socket socket) throws IOException {
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            return a(socket, remoteSocketAddress instanceof InetSocketAddress ? ((InetSocketAddress) remoteSocketAddress).getHostName() : remoteSocketAddress.toString(), ob.a(ob.b(socket)), ob.a(ob.a(socket)));
        }

        public h a(j jVar) {
            this.e = jVar;
            return this;
        }

        public h a(ba baVar) {
            this.f = baVar;
            return this;
        }

        public h a(int i) {
            this.h = i;
            return this;
        }

        public h(boolean z) {
            this.g = z;
        }
    }

    public static /* synthetic */ long b(v9 v9Var) {
        long j2 = v9Var.l;
        v9Var.l = 1 + j2;
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(@Nullable IOException iOException) {
        r9 r9Var = r9.PROTOCOL_ERROR;
        a(r9Var, r9Var, iOException);
    }

    public static abstract class j {

        /* renamed from: a, reason: collision with root package name */
        public static final j f5548a = new a();

        public void a(v9 v9Var) {
        }

        public abstract void a(y9 y9Var) throws IOException;

        public class a extends j {
            @Override // com.huawei.hms.network.embedded.v9.j
            public void a(y9 y9Var) throws IOException {
                y9Var.a(r9.REFUSED_STREAM, (IOException) null);
            }
        }
    }

    private void a(d8 d8Var) {
        synchronized (this) {
            if (!this.g) {
                this.i.execute(d8Var);
            }
        }
    }

    public class a extends d8 {
        public final /* synthetic */ int b;
        public final /* synthetic */ r9 c;

        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            try {
                v9.this.b(this.b, this.c);
            } catch (IOException e) {
                v9.this.a(e);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Object[] objArr, int i, r9 r9Var) {
            super(str, objArr);
            this.b = i;
            this.c = r9Var;
        }
    }

    public class b extends d8 {
        public final /* synthetic */ int b;
        public final /* synthetic */ long c;

        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            try {
                v9.this.w.a(this.b, this.c);
            } catch (IOException e) {
                v9.this.a(e);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, Object[] objArr, int i, long j) {
            super(str, objArr);
            this.b = i;
            this.c = j;
        }
    }

    public class c extends d8 {
        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            v9.this.a(false, 2, 0);
        }

        public c(String str, Object... objArr) {
            super(str, objArr);
        }
    }

    public class d extends d8 {
        public final /* synthetic */ int b;
        public final /* synthetic */ List c;

        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            if (v9.this.j.a(this.b, this.c)) {
                try {
                    v9.this.w.a(this.b, r9.CANCEL);
                    synchronized (v9.this) {
                        v9.this.y.remove(Integer.valueOf(this.b));
                    }
                } catch (IOException unused) {
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(String str, Object[] objArr, int i, List list) {
            super(str, objArr);
            this.b = i;
            this.c = list;
        }
    }

    public class e extends d8 {
        public final /* synthetic */ int b;
        public final /* synthetic */ List c;
        public final /* synthetic */ boolean d;

        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            boolean a2 = v9.this.j.a(this.b, this.c, this.d);
            if (a2) {
                try {
                    v9.this.w.a(this.b, r9.CANCEL);
                } catch (IOException unused) {
                    return;
                }
            }
            if (a2 || this.d) {
                synchronized (v9.this) {
                    v9.this.y.remove(Integer.valueOf(this.b));
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(String str, Object[] objArr, int i, List list, boolean z) {
            super(str, objArr);
            this.b = i;
            this.c = list;
            this.d = z;
        }
    }

    public class f extends d8 {
        public final /* synthetic */ int b;
        public final /* synthetic */ bb c;
        public final /* synthetic */ int d;
        public final /* synthetic */ boolean e;

        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            try {
                boolean a2 = v9.this.j.a(this.b, this.c, this.d, this.e);
                if (a2) {
                    v9.this.w.a(this.b, r9.CANCEL);
                }
                if (a2 || this.e) {
                    synchronized (v9.this) {
                        v9.this.y.remove(Integer.valueOf(this.b));
                    }
                }
            } catch (IOException unused) {
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(String str, Object[] objArr, int i, bb bbVar, int i2, boolean z) {
            super(str, objArr);
            this.b = i;
            this.c = bbVar;
            this.d = i2;
            this.e = z;
        }
    }

    public class g extends d8 {
        public final /* synthetic */ int b;
        public final /* synthetic */ r9 c;

        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            v9.this.j.a(this.b, this.c);
            synchronized (v9.this) {
                v9.this.y.remove(Integer.valueOf(this.b));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String str, Object[] objArr, int i, r9 r9Var) {
            super(str, objArr);
            this.b = i;
            this.c = r9Var;
        }
    }

    public final class i extends d8 {
        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            boolean z;
            synchronized (v9.this) {
                if (v9.this.l < v9.this.k) {
                    z = true;
                } else {
                    v9.d(v9.this);
                    z = false;
                }
            }
            v9 v9Var = v9.this;
            if (z) {
                v9Var.a((IOException) null);
            } else {
                v9Var.a(false, 1, 0);
            }
        }

        public i() {
            super("OkHttp %s ping", v9.this.d);
        }
    }

    public final class k extends d8 {
        public final boolean b;
        public final int c;
        public final int d;

        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            v9.this.a(this.b, this.c, this.d);
        }

        public k(boolean z, int i, int i2) {
            super("OkHttp %s ping %08x%08x", v9.this.d, Integer.valueOf(i), Integer.valueOf(i2));
            this.b = z;
            this.c = i;
            this.d = i2;
        }
    }

    public v9(h hVar) {
        ca caVar = new ca();
        this.u = caVar;
        this.y = new LinkedHashSet();
        this.j = hVar.f;
        boolean z2 = hVar.g;
        this.f5546a = z2;
        this.b = hVar.e;
        this.f = z2 ? 1 : 2;
        if (hVar.g) {
            this.f += 2;
        }
        if (hVar.g) {
            this.t.a(7, 16777216);
        }
        String str = hVar.b;
        this.d = str;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, f8.a(f8.a("OkHttp %s Writer", str), false));
        this.h = scheduledThreadPoolExecutor;
        if (hVar.h != 0) {
            i iVar = new i();
            long j2 = hVar.h;
            scheduledThreadPoolExecutor.scheduleAtFixedRate(iVar, j2, j2, TimeUnit.MILLISECONDS);
        }
        this.i = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), f8.a(f8.a("OkHttp %s Push Observer", str), true));
        caVar.a(7, 65535);
        caVar.a(5, 16384);
        this.s = caVar.c();
        this.v = hVar.f5547a;
        this.w = new z9(hVar.d, z2);
        this.x = new l(new x9(hVar.c, z2));
    }
}
