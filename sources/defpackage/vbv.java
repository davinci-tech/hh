package defpackage;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public class vbv implements ThreadFactory {
    public static final ThreadGroup c;
    public static final ThreadGroup d;

    /* renamed from: a, reason: collision with root package name */
    private final ThreadGroup f17654a;
    private final String b;
    private final AtomicInteger e = new AtomicInteger(1);

    protected boolean e() {
        return false;
    }

    static {
        ThreadGroup threadGroup = new ThreadGroup("Californium");
        d = threadGroup;
        ThreadGroup threadGroup2 = new ThreadGroup("Scandium");
        c = threadGroup2;
        threadGroup.setDaemon(false);
        threadGroup2.setDaemon(false);
    }

    public vbv(String str, ThreadGroup threadGroup) {
        this.f17654a = threadGroup == null ? d : threadGroup;
        this.b = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f17654a, runnable, this.b + this.e.getAndIncrement(), 0L);
        thread.setDaemon(e());
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        return thread;
    }
}
