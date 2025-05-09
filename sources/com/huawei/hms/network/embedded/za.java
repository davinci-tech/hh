package com.huawei.hms.network.embedded;

import com.huawei.operation.utils.Constants;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public class za extends ac {
    public static final int h = 65536;
    public static final long i;
    public static final long j;

    @Nullable
    public static za k;
    public boolean e;

    @Nullable
    public za f;
    public long g;

    public void timedOut() {
    }

    public IOException newTimeoutException(@Nullable IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final boolean g() {
        if (!this.e) {
            return false;
        }
        this.e = false;
        return a(this);
    }

    public final void f() {
        if (this.e) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long e = e();
        boolean d = d();
        if (e != 0 || d) {
            this.e = true;
            a(this, e, d);
        }
    }

    public final void a(boolean z) throws IOException {
        if (g() && z) {
            throw newTimeoutException(null);
        }
    }

    public final IOException a(IOException iOException) throws IOException {
        return !g() ? iOException : newTimeoutException(iOException);
    }

    public final zb a(zb zbVar) {
        return new b(zbVar);
    }

    public class a implements yb {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ yb f5594a;

        @Override // com.huawei.hms.network.embedded.yb
        public void write(bb bbVar, long j) throws IOException {
            cc.a(bbVar.b, 0L, j);
            while (true) {
                long j2 = 0;
                if (j <= 0) {
                    return;
                }
                vb vbVar = bbVar.f5191a;
                while (true) {
                    if (j2 >= 65536) {
                        break;
                    }
                    j2 += vbVar.c - vbVar.b;
                    if (j2 >= j) {
                        j2 = j;
                        break;
                    }
                    vbVar = vbVar.f;
                }
                za.this.f();
                try {
                    try {
                        this.f5594a.write(bbVar, j2);
                        j -= j2;
                        za.this.a(true);
                    } catch (IOException e) {
                        throw za.this.a(e);
                    }
                } catch (Throwable th) {
                    za.this.a(false);
                    throw th;
                }
            }
        }

        public String toString() {
            return "AsyncTimeout.sink(" + this.f5594a + Constants.RIGHT_BRACKET_ONLY;
        }

        @Override // com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return za.this;
        }

        @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
        public void flush() throws IOException {
            za.this.f();
            try {
                try {
                    this.f5594a.flush();
                    za.this.a(true);
                } catch (IOException e) {
                    throw za.this.a(e);
                }
            } catch (Throwable th) {
                za.this.a(false);
                throw th;
            }
        }

        @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            za.this.f();
            try {
                try {
                    this.f5594a.close();
                    za.this.a(true);
                } catch (IOException e) {
                    throw za.this.a(e);
                }
            } catch (Throwable th) {
                za.this.a(false);
                throw th;
            }
        }

        public a(yb ybVar) {
            this.f5594a = ybVar;
        }
    }

    public final yb a(yb ybVar) {
        return new a(ybVar);
    }

    public class b implements zb {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ zb f5595a;

        public String toString() {
            return "AsyncTimeout.source(" + this.f5595a + Constants.RIGHT_BRACKET_ONLY;
        }

        @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return za.this;
        }

        @Override // com.huawei.hms.network.embedded.zb
        public long read(bb bbVar, long j) throws IOException {
            za.this.f();
            try {
                try {
                    long read = this.f5595a.read(bbVar, j);
                    za.this.a(true);
                    return read;
                } catch (IOException e) {
                    throw za.this.a(e);
                }
            } catch (Throwable th) {
                za.this.a(false);
                throw th;
            }
        }

        @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
            za.this.f();
            try {
                try {
                    this.f5595a.close();
                    za.this.a(true);
                } catch (IOException e) {
                    throw za.this.a(e);
                }
            } catch (Throwable th) {
                za.this.a(false);
                throw th;
            }
        }

        public b(zb zbVar) {
            this.f5595a = zbVar;
        }
    }

    @Nullable
    public static za h() throws InterruptedException {
        za zaVar = k.f;
        long nanoTime = System.nanoTime();
        if (zaVar == null) {
            za.class.wait(i);
            if (k.f != null || System.nanoTime() - nanoTime < j) {
                return null;
            }
            return k;
        }
        long a2 = zaVar.a(nanoTime);
        if (a2 > 0) {
            long j2 = a2 / 1000000;
            za.class.wait(j2, (int) (a2 - (1000000 * j2)));
            return null;
        }
        k.f = zaVar.f;
        zaVar.f = null;
        return zaVar;
    }

    public static boolean a(za zaVar) {
        synchronized (za.class) {
            za zaVar2 = k;
            while (zaVar2 != null) {
                za zaVar3 = zaVar2.f;
                if (zaVar3 == zaVar) {
                    zaVar2.f = zaVar.f;
                    zaVar.f = null;
                    return false;
                }
                zaVar2 = zaVar3;
            }
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005a A[Catch: all -> 0x0067, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0016, B:10:0x0022, B:11:0x002e, B:12:0x003a, B:13:0x0040, B:15:0x0044, B:17:0x004d, B:20:0x0050, B:22:0x005a, B:29:0x0034, B:30:0x0061, B:31:0x0066), top: B:3:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.huawei.hms.network.embedded.za r5, long r6, boolean r8) {
        /*
            java.lang.Class<com.huawei.hms.network.embedded.za> r0 = com.huawei.hms.network.embedded.za.class
            monitor-enter(r0)
            com.huawei.hms.network.embedded.za r1 = com.huawei.hms.network.embedded.za.k     // Catch: java.lang.Throwable -> L67
            if (r1 != 0) goto L16
            com.huawei.hms.network.embedded.za r1 = new com.huawei.hms.network.embedded.za     // Catch: java.lang.Throwable -> L67
            r1.<init>()     // Catch: java.lang.Throwable -> L67
            com.huawei.hms.network.embedded.za.k = r1     // Catch: java.lang.Throwable -> L67
            com.huawei.hms.network.embedded.za$c r1 = new com.huawei.hms.network.embedded.za$c     // Catch: java.lang.Throwable -> L67
            r1.<init>()     // Catch: java.lang.Throwable -> L67
            r1.start()     // Catch: java.lang.Throwable -> L67
        L16:
            long r1 = java.lang.System.nanoTime()     // Catch: java.lang.Throwable -> L67
            r3 = 0
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 == 0) goto L2c
            if (r8 == 0) goto L2c
            long r3 = r5.c()     // Catch: java.lang.Throwable -> L67
            long r3 = r3 - r1
            long r6 = java.lang.Math.min(r6, r3)     // Catch: java.lang.Throwable -> L67
            goto L2e
        L2c:
            if (r3 == 0) goto L32
        L2e:
            long r6 = r6 + r1
            r5.g = r6     // Catch: java.lang.Throwable -> L67
            goto L3a
        L32:
            if (r8 == 0) goto L61
            long r6 = r5.c()     // Catch: java.lang.Throwable -> L67
            r5.g = r6     // Catch: java.lang.Throwable -> L67
        L3a:
            long r6 = r5.a(r1)     // Catch: java.lang.Throwable -> L67
            com.huawei.hms.network.embedded.za r8 = com.huawei.hms.network.embedded.za.k     // Catch: java.lang.Throwable -> L67
        L40:
            com.huawei.hms.network.embedded.za r3 = r8.f     // Catch: java.lang.Throwable -> L67
            if (r3 == 0) goto L50
            long r3 = r3.a(r1)     // Catch: java.lang.Throwable -> L67
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 >= 0) goto L4d
            goto L50
        L4d:
            com.huawei.hms.network.embedded.za r8 = r8.f     // Catch: java.lang.Throwable -> L67
            goto L40
        L50:
            com.huawei.hms.network.embedded.za r6 = r8.f     // Catch: java.lang.Throwable -> L67
            r5.f = r6     // Catch: java.lang.Throwable -> L67
            r8.f = r5     // Catch: java.lang.Throwable -> L67
            com.huawei.hms.network.embedded.za r5 = com.huawei.hms.network.embedded.za.k     // Catch: java.lang.Throwable -> L67
            if (r8 != r5) goto L5f
            java.lang.Class<com.huawei.hms.network.embedded.za> r5 = com.huawei.hms.network.embedded.za.class
            r5.notify()     // Catch: java.lang.Throwable -> L67
        L5f:
            monitor-exit(r0)
            return
        L61:
            java.lang.AssertionError r5 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L67
            r5.<init>()     // Catch: java.lang.Throwable -> L67
            throw r5     // Catch: java.lang.Throwable -> L67
        L67:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.za.a(com.huawei.hms.network.embedded.za, long, boolean):void");
    }

    public static final class c extends Thread {
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0015, code lost:
        
            r1.timedOut();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r3 = this;
            L0:
                java.lang.Class<com.huawei.hms.network.embedded.za> r0 = com.huawei.hms.network.embedded.za.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L0
                com.huawei.hms.network.embedded.za r1 = com.huawei.hms.network.embedded.za.h()     // Catch: java.lang.Throwable -> L19
                if (r1 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L0
            Lb:
                com.huawei.hms.network.embedded.za r2 = com.huawei.hms.network.embedded.za.k     // Catch: java.lang.Throwable -> L19
                if (r1 != r2) goto L14
                r1 = 0
                com.huawei.hms.network.embedded.za.k = r1     // Catch: java.lang.Throwable -> L19
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                return
            L14:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                r1.timedOut()     // Catch: java.lang.InterruptedException -> L0
                goto L0
            L19:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                throw r1     // Catch: java.lang.InterruptedException -> L0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.za.c.run():void");
        }

        public c() {
            super("Okio Watchdog");
            setDaemon(true);
        }
    }

    private long a(long j2) {
        return this.g - j2;
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        i = millis;
        j = TimeUnit.MILLISECONDS.toNanos(millis);
    }
}
