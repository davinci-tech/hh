package com.huawei.hms.network.embedded;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public class w3 extends x3 {
    public static final long g;
    public static final long h;

    @Nullable
    public static w3 i;
    public boolean d;

    @Nullable
    public w3 e;
    public long f;

    public void timedOut() {
    }

    public final boolean exit() {
        if (!this.d) {
            return false;
        }
        this.d = false;
        return a(this);
    }

    public final void enter() {
        if (this.d) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long timeoutNanos = timeoutNanos();
        boolean hasDeadline = hasDeadline();
        if (timeoutNanos != 0 || hasDeadline) {
            this.d = true;
            a(this, timeoutNanos, hasDeadline);
        }
    }

    public static boolean a(w3 w3Var) {
        synchronized (w3.class) {
            w3 w3Var2 = i;
            while (w3Var2 != null) {
                w3 w3Var3 = w3Var2.e;
                if (w3Var3 == w3Var) {
                    w3Var2.e = w3Var.e;
                    w3Var.e = null;
                    return false;
                }
                w3Var2 = w3Var3;
            }
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005a A[Catch: all -> 0x0067, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0016, B:10:0x0022, B:11:0x002e, B:12:0x003a, B:13:0x0040, B:15:0x0044, B:17:0x004d, B:20:0x0050, B:22:0x005a, B:29:0x0034, B:30:0x0061, B:31:0x0066), top: B:3:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.huawei.hms.network.embedded.w3 r5, long r6, boolean r8) {
        /*
            java.lang.Class<com.huawei.hms.network.embedded.w3> r0 = com.huawei.hms.network.embedded.w3.class
            monitor-enter(r0)
            com.huawei.hms.network.embedded.w3 r1 = com.huawei.hms.network.embedded.w3.i     // Catch: java.lang.Throwable -> L67
            if (r1 != 0) goto L16
            com.huawei.hms.network.embedded.w3 r1 = new com.huawei.hms.network.embedded.w3     // Catch: java.lang.Throwable -> L67
            r1.<init>()     // Catch: java.lang.Throwable -> L67
            com.huawei.hms.network.embedded.w3.i = r1     // Catch: java.lang.Throwable -> L67
            com.huawei.hms.network.embedded.w3$a r1 = new com.huawei.hms.network.embedded.w3$a     // Catch: java.lang.Throwable -> L67
            r1.<init>()     // Catch: java.lang.Throwable -> L67
            r1.start()     // Catch: java.lang.Throwable -> L67
        L16:
            long r1 = java.lang.System.nanoTime()     // Catch: java.lang.Throwable -> L67
            r3 = 0
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 == 0) goto L2c
            if (r8 == 0) goto L2c
            long r3 = r5.deadlineNanoTime()     // Catch: java.lang.Throwable -> L67
            long r3 = r3 - r1
            long r6 = java.lang.Math.min(r6, r3)     // Catch: java.lang.Throwable -> L67
            goto L2e
        L2c:
            if (r3 == 0) goto L32
        L2e:
            long r6 = r6 + r1
            r5.f = r6     // Catch: java.lang.Throwable -> L67
            goto L3a
        L32:
            if (r8 == 0) goto L61
            long r6 = r5.deadlineNanoTime()     // Catch: java.lang.Throwable -> L67
            r5.f = r6     // Catch: java.lang.Throwable -> L67
        L3a:
            long r6 = r5.a(r1)     // Catch: java.lang.Throwable -> L67
            com.huawei.hms.network.embedded.w3 r8 = com.huawei.hms.network.embedded.w3.i     // Catch: java.lang.Throwable -> L67
        L40:
            com.huawei.hms.network.embedded.w3 r3 = r8.e     // Catch: java.lang.Throwable -> L67
            if (r3 == 0) goto L50
            long r3 = r3.a(r1)     // Catch: java.lang.Throwable -> L67
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 >= 0) goto L4d
            goto L50
        L4d:
            com.huawei.hms.network.embedded.w3 r8 = r8.e     // Catch: java.lang.Throwable -> L67
            goto L40
        L50:
            com.huawei.hms.network.embedded.w3 r6 = r8.e     // Catch: java.lang.Throwable -> L67
            r5.e = r6     // Catch: java.lang.Throwable -> L67
            r8.e = r5     // Catch: java.lang.Throwable -> L67
            com.huawei.hms.network.embedded.w3 r5 = com.huawei.hms.network.embedded.w3.i     // Catch: java.lang.Throwable -> L67
            if (r8 != r5) goto L5f
            java.lang.Class<com.huawei.hms.network.embedded.w3> r5 = com.huawei.hms.network.embedded.w3.class
            r5.notifyAll()     // Catch: java.lang.Throwable -> L67
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
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.w3.a(com.huawei.hms.network.embedded.w3, long, boolean):void");
    }

    @Nullable
    public static w3 a() throws InterruptedException {
        w3 w3Var = i.e;
        long nanoTime = System.nanoTime();
        if (w3Var == null) {
            w3.class.wait(g);
            if (i.e != null || System.nanoTime() - nanoTime < h) {
                return null;
            }
            return i;
        }
        long a2 = w3Var.a(nanoTime);
        if (a2 > 0) {
            long j = a2 / 1000000;
            w3.class.wait(j, (int) (a2 - (1000000 * j)));
            return null;
        }
        i.e = w3Var.e;
        w3Var.e = null;
        return w3Var;
    }

    public static final class a extends Thread {

        /* renamed from: a, reason: collision with root package name */
        public static final String f5555a = "Watchdog";

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
                java.lang.Class<com.huawei.hms.network.embedded.w3> r0 = com.huawei.hms.network.embedded.w3.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L1c
                com.huawei.hms.network.embedded.w3 r1 = com.huawei.hms.network.embedded.w3.a()     // Catch: java.lang.Throwable -> L19
                if (r1 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L0
            Lb:
                com.huawei.hms.network.embedded.w3 r2 = com.huawei.hms.network.embedded.w3.i     // Catch: java.lang.Throwable -> L19
                if (r1 != r2) goto L14
                r1 = 0
                com.huawei.hms.network.embedded.w3.i = r1     // Catch: java.lang.Throwable -> L19
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                return
            L14:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                r1.timedOut()     // Catch: java.lang.InterruptedException -> L1c
                goto L0
            L19:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                throw r1     // Catch: java.lang.InterruptedException -> L1c
            L1c:
                r0 = move-exception
                java.lang.String r1 = "Watchdog"
                java.lang.String r0 = r0.getMessage()
                com.huawei.hms.framework.common.Logger.i(r1, r0)
                goto L0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.w3.a.run():void");
        }

        public a() {
            super(f5555a);
            setDaemon(true);
        }
    }

    private long a(long j) {
        return this.f - j;
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        g = millis;
        h = TimeUnit.MILLISECONDS.toNanos(millis);
    }
}
