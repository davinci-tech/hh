package com.amap.api.col.p0003sl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class gj {
    private static volatile gj c;

    /* renamed from: a, reason: collision with root package name */
    private BlockingQueue<Runnable> f1069a = new LinkedBlockingQueue();
    private ExecutorService b;

    public static gj a() {
        if (c == null) {
            synchronized (gj.class) {
                if (c == null) {
                    c = new gj();
                }
            }
        }
        return c;
    }

    public static void b() {
        if (c != null) {
            synchronized (gj.class) {
                if (c != null) {
                    c.b.shutdownNow();
                    c.b = null;
                    c = null;
                }
            }
        }
    }

    private gj() {
        this.b = null;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        this.b = new ThreadPoolExecutor(availableProcessors, availableProcessors * 2, 1L, TimeUnit.SECONDS, this.f1069a, new ThreadPoolExecutor.AbortPolicy());
    }

    public final void a(Runnable runnable) {
        ExecutorService executorService = this.b;
        if (executorService != null) {
            executorService.execute(runnable);
        }
    }
}
