package com.huawei.hms.network.embedded;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public final class s7 implements t6 {

    /* renamed from: a, reason: collision with root package name */
    public final q7 f5476a;
    public d9 b;
    public final t7 c;
    public final boolean d;
    public boolean e;

    @Override // com.huawei.hms.network.embedded.t6
    public ac timeout() {
        return this.b.timeout();
    }

    @Override // com.huawei.hms.network.embedded.t6
    public t7 request() {
        return this.c;
    }

    @Override // com.huawei.hms.network.embedded.t6
    public boolean isExecuted() {
        boolean z;
        synchronized (this) {
            z = this.e;
        }
        return z;
    }

    @Override // com.huawei.hms.network.embedded.t6
    public boolean isCanceled() {
        return this.b.isCanceled();
    }

    public final class a extends d8 {
        public static final /* synthetic */ boolean f = true;
        public final u6 b;
        public boolean c;
        public volatile AtomicInteger d;

        public void i() {
            this.c = true;
        }

        public t7 h() {
            return s7.this.c;
        }

        public String g() {
            return s7.this.c.k().h();
        }

        public s7 f() {
            return s7.this;
        }

        public boolean e() {
            return this.c;
        }

        public q7 d() {
            return s7.this.f5476a;
        }

        public AtomicInteger c() {
            return this.d;
        }

        @Override // com.huawei.hms.network.embedded.d8
        public void b() {
            s7.this.b.timeoutEnter();
            boolean z = false;
            try {
                try {
                    try {
                        this.b.onResponse(s7.this, this.c ? s7.this.a() : s7.this.b());
                    } catch (IOException e) {
                        e = e;
                        z = true;
                        if (z) {
                            ma.f().a(4, "Callback failure for " + s7.this.d(), e);
                        } else {
                            this.b.onFailure(s7.this, e);
                        }
                        s7.this.f5476a.j().b(this);
                    } catch (Throwable th) {
                        th = th;
                        z = true;
                        s7.this.cancel();
                        if (!z) {
                            IOException iOException = new IOException("canceled due to " + th);
                            iOException.addSuppressed(th);
                            this.b.onFailure(s7.this, iOException);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    s7.this.f5476a.j().b(this);
                    throw th2;
                }
            } catch (IOException e2) {
                e = e2;
            } catch (Throwable th3) {
                th = th3;
            }
            s7.this.f5476a.j().b(this);
        }

        public void a(ExecutorService executorService) {
            if (!f && Thread.holdsLock(s7.this.f5476a.j())) {
                throw new AssertionError();
            }
            try {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e);
                    s7.this.b.noMoreExchanges(interruptedIOException);
                    this.b.onFailure(s7.this, interruptedIOException);
                    s7.this.f5476a.j().b(this);
                }
            } catch (Throwable th) {
                s7.this.f5476a.j().b(this);
                throw th;
            }
        }

        public void a(a aVar) {
            this.d = aVar.d;
        }

        public a(u6 u6Var) {
            super("OkHttp %s", s7.this.c());
            this.c = false;
            this.d = new AtomicInteger(0);
            this.b = u6Var;
        }
    }

    @Override // com.huawei.hms.network.embedded.t6
    public v7 execute() throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IllegalStateException("Already Executed");
            }
            this.e = true;
        }
        this.b.timeoutEnter();
        this.b.callStart();
        try {
            this.f5476a.j().a(this);
            return b();
        } finally {
            this.f5476a.j().b(this);
        }
    }

    @Override // com.huawei.hms.network.embedded.t6
    public void enqueue(u6 u6Var) {
        synchronized (this) {
            if (this.e) {
                throw new IllegalStateException("Already Executed");
            }
            this.e = true;
        }
        this.b.callStart();
        this.f5476a.j().a(new a(u6Var));
    }

    public String d() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.d ? "web socket" : NotificationCompat.CATEGORY_CALL);
        sb.append(" to ");
        sb.append(c());
        return sb.toString();
    }

    @Override // com.huawei.hms.network.embedded.t6
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public s7 m635clone() {
        return a(this.f5476a, this.c, this.d);
    }

    @Override // com.huawei.hms.network.embedded.t6
    public void cancel() {
        this.b.cancel();
    }

    public String c() {
        return this.c.k().r();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.network.embedded.v7 b() throws java.io.IOException {
        /*
            r11 = this;
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.huawei.hms.network.embedded.q7 r0 = r11.f5476a
            java.util.List r0 = r0.q()
            r1.addAll(r0)
            com.huawei.hms.network.embedded.n9 r0 = new com.huawei.hms.network.embedded.n9
            com.huawei.hms.network.embedded.q7 r2 = r11.f5476a
            r0.<init>(r2)
            r1.add(r0)
            com.huawei.hms.network.embedded.e9 r0 = new com.huawei.hms.network.embedded.e9
            com.huawei.hms.network.embedded.q7 r2 = r11.f5476a
            com.huawei.hms.network.embedded.c7 r2 = r2.i()
            r0.<init>(r2)
            r1.add(r0)
            com.huawei.hms.network.embedded.i8 r0 = new com.huawei.hms.network.embedded.i8
            com.huawei.hms.network.embedded.q7 r2 = r11.f5476a
            com.huawei.hms.network.embedded.n8 r2 = r2.r()
            r0.<init>(r2)
            r1.add(r0)
            com.huawei.hms.network.embedded.t8 r0 = new com.huawei.hms.network.embedded.t8
            com.huawei.hms.network.embedded.q7 r2 = r11.f5476a
            r0.<init>(r2)
            r1.add(r0)
            boolean r0 = r11.d
            if (r0 != 0) goto L4b
            com.huawei.hms.network.embedded.q7 r0 = r11.f5476a
            java.util.List r0 = r0.s()
            r1.addAll(r0)
        L4b:
            com.huawei.hms.network.embedded.f9 r0 = new com.huawei.hms.network.embedded.f9
            boolean r2 = r11.d
            r0.<init>(r2)
            r1.add(r0)
            com.huawei.hms.network.embedded.k9 r10 = new com.huawei.hms.network.embedded.k9
            com.huawei.hms.network.embedded.d9 r2 = r11.b
            r3 = 0
            r4 = 0
            com.huawei.hms.network.embedded.t7 r5 = r11.c
            com.huawei.hms.network.embedded.q7 r0 = r11.f5476a
            int r7 = r0.e()
            com.huawei.hms.network.embedded.q7 r0 = r11.f5476a
            int r8 = r0.z()
            com.huawei.hms.network.embedded.q7 r0 = r11.f5476a
            int r9 = r0.D()
            r0 = r10
            r6 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r0 = 0
            com.huawei.hms.network.embedded.t7 r1 = r11.c     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            com.huawei.hms.network.embedded.v7 r1 = r10.a(r1)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            com.huawei.hms.network.embedded.d9 r2 = r11.b     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            boolean r2 = r2.isCanceled()     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            if (r2 != 0) goto L89
            com.huawei.hms.network.embedded.d9 r2 = r11.b
            r2.noMoreExchanges(r0)
            return r1
        L89:
            com.huawei.hms.network.embedded.f8.a(r1)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.lang.String r2 = "Canceled"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            throw r1     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
        L94:
            r1 = move-exception
            r2 = 0
            goto La1
        L97:
            r1 = move-exception
            com.huawei.hms.network.embedded.d9 r2 = r11.b     // Catch: java.lang.Throwable -> L9f
            java.io.IOException r1 = r2.noMoreExchanges(r1)     // Catch: java.lang.Throwable -> L9f
            throw r1     // Catch: java.lang.Throwable -> L9f
        L9f:
            r1 = move-exception
            r2 = 1
        La1:
            if (r2 != 0) goto La8
            com.huawei.hms.network.embedded.d9 r2 = r11.b
            r2.noMoreExchanges(r0)
        La8:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.s7.b():com.huawei.hms.network.embedded.v7");
    }

    public v7 a() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new n9(this.f5476a));
        arrayList.add(new x8(this.f5476a));
        return new k9(arrayList, this.b, null, 0, this.c, this, this.f5476a.e(), this.f5476a.z(), this.f5476a.D()).a(this.c);
    }

    public static s7 a(q7 q7Var, t7 t7Var, boolean z) {
        s7 s7Var = new s7(q7Var, t7Var, z);
        s7Var.b = new d9(q7Var, s7Var);
        return s7Var;
    }

    public s7(q7 q7Var, t7 t7Var, boolean z) {
        this.f5476a = q7Var;
        this.c = t7Var;
        this.d = z;
    }
}
