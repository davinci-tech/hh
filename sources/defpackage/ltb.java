package defpackage;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public class ltb implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    private final ThreadGroup f14864a;
    private final int b;
    private final String d;
    private final AtomicInteger e;

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f14864a, runnable, this.d + this.e.getAndIncrement(), 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        int priority = thread.getPriority();
        int i = this.b;
        if (priority != i) {
            thread.setPriority(i);
        }
        return thread;
    }

    public ltb(String str, int i) {
        this.e = new AtomicInteger(1);
        this.b = i;
        SecurityManager securityManager = System.getSecurityManager();
        this.f14864a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.d = "PPS-" + str + "-pool-thread-";
    }

    public ltb(String str) {
        this(str, 5);
    }
}
