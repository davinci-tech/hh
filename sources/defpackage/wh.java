package defpackage;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class wh implements ThreadFactory {
    private final ThreadGroup b;
    private final String c;
    private final AtomicInteger d;
    private final int e;

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.b, runnable, this.c + this.d.getAndIncrement(), 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        int priority = thread.getPriority();
        int i = this.e;
        if (priority != i) {
            thread.setPriority(i);
        }
        return thread;
    }

    public wh(String str, int i) {
        this.d = new AtomicInteger(1);
        this.e = i;
        SecurityManager securityManager = System.getSecurityManager();
        this.b = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.c = "PPS-" + str + "-threadpool-";
    }

    public wh(String str) {
        this(str, 5);
    }
}
