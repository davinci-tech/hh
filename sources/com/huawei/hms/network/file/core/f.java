package com.huawei.hms.network.file.core;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.ThreadPoolExcutorEnhance;
import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.core.util.FLogger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class f {
    private static final Object f = new Object();
    private static volatile ThreadPoolExecutor g;

    /* renamed from: a, reason: collision with root package name */
    private GlobalRequestConfig f5629a;
    ThreadPoolExecutor b;
    ExecutorService c;
    ExecutorService d;
    int e;

    public void e() {
        synchronized (this) {
            FLogger.i("ThreadPoolManager", "shutdown executors", new Object[0]);
            ThreadPoolExecutor threadPoolExecutor = this.b;
            if (threadPoolExecutor != null || this.c != null || this.d != null) {
                a(threadPoolExecutor, this.c, this.d);
                this.b = null;
                this.c = null;
                this.d = null;
            }
        }
    }

    public ThreadPoolExecutor d() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (this) {
            if (this.b == null) {
                g();
            }
            threadPoolExecutor = this.b;
        }
        return threadPoolExecutor;
    }

    public ExecutorService c() {
        ExecutorService executorService;
        synchronized (this) {
            if (this.c == null) {
                g();
            }
            executorService = this.c;
        }
        return executorService;
    }

    public int b() {
        return this.e;
    }

    public ExecutorService a() {
        ExecutorService executorService;
        synchronized (this) {
            if (this.d == null) {
                g();
            }
            executorService = this.d;
        }
        return executorService;
    }

    private void g() {
        int c = b.c();
        if (c <= 0) {
            c = Runtime.getRuntime().availableProcessors() * 2;
        }
        this.e = c;
        GlobalRequestConfig globalRequestConfig = this.f5629a;
        if (globalRequestConfig != null && globalRequestConfig.getThreadPoolSize() != -100) {
            c = this.f5629a.getThreadPoolSize();
            this.e = c;
            FLogger.i("ThreadPoolManager", "use taskPoolSize executeCorePoolSize:" + c, new Object[0]);
        }
        int i = c;
        FLogger.i("ThreadPoolManager", "executeCorePoolSize:" + i + ",maxThreadPoolSize:" + this.e, new Object[0]);
        if (this.b == null) {
            ThreadPoolExcutorEnhance threadPoolExcutorEnhance = new ThreadPoolExcutorEnhance(i, this.e, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue(), ExecutorsUtils.createThreadFactory("fileTaskPoolThread"));
            this.b = threadPoolExcutorEnhance;
            threadPoolExcutorEnhance.allowCoreThreadTimeOut(true);
        }
        if (this.c == null) {
            this.c = ExecutorsUtils.newSingleThreadExecutor("fileRespPoolThread");
        }
        if (this.d == null) {
            this.d = ExecutorsUtils.newSingleThreadExecutor("fileExtraPoolThread");
        }
    }

    public static ThreadPoolExecutor f() {
        if (g == null) {
            synchronized (f) {
                if (g == null) {
                    g = new ThreadPoolExcutorEnhance(2, 2, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue(), ExecutorsUtils.createThreadFactory("initPoolThread"));
                }
            }
        }
        g.allowCoreThreadTimeOut(true);
        return g;
    }

    private void a(ExecutorService... executorServiceArr) {
        for (ExecutorService executorService : executorServiceArr) {
            if (executorService != null && !executorService.isShutdown()) {
                executorService.shutdown();
            }
        }
    }

    public f(GlobalRequestConfig globalRequestConfig) {
        this.f5629a = globalRequestConfig;
    }
}
