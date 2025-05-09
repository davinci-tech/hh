package defpackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public final class svt {

    /* renamed from: a, reason: collision with root package name */
    private static volatile svt f17256a;
    private static final byte[] d = new byte[0];
    private final ExecutorService c;

    private svt() {
        stq.b("ThreadPoolManager", "ThreadPool init");
        this.c = Executors.newSingleThreadExecutor();
    }

    public static svt e() {
        if (f17256a == null) {
            synchronized (d) {
                if (f17256a == null) {
                    f17256a = new svt();
                }
            }
        }
        return f17256a;
    }

    public ExecutorService b() {
        return this.c;
    }

    public void a(Runnable runnable) {
        this.c.execute(runnable);
    }
}
