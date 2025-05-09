package com.huawei.hmf.tasks.a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public final class a {
    public static final a b = new a();
    public static final int c;
    public static final int d;
    public static final int e;

    /* renamed from: a, reason: collision with root package name */
    public final Executor f4219a = new b();

    public static class b implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }

        public b() {
        }
    }

    public static Executor b() {
        return b.f4219a;
    }

    public static ExecutorService a() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(d, e, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        c = availableProcessors;
        d = availableProcessors + 1;
        e = (availableProcessors * 2) + 1;
    }
}
