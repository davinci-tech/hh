package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes9.dex */
public final class e1 {
    public static final String b = "RequestThreadPoolMgr";

    /* renamed from: a, reason: collision with root package name */
    public ExecutorService f5224a;

    public static class b {
        public static final e1 INSTANCE = new e1();
    }

    public Future submit(Runnable runnable) {
        try {
            return this.f5224a.submit(runnable);
        } catch (RejectedExecutionException unused) {
            Logger.e(b, "the runnable task cannot be accepted for execution");
            return null;
        }
    }

    public void release() {
        Logger.i(b, "ThreadPool release!");
        ExecutorService executorService = this.f5224a;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.f5224a.shutdown();
    }

    public void execute(Runnable runnable) {
        try {
            this.f5224a.execute(runnable);
        } catch (RejectedExecutionException unused) {
            Logger.e(b, "the runnable task cannot be accepted for execution");
        }
    }

    public static e1 getInstance() {
        return b.INSTANCE;
    }

    public e1() {
        this.f5224a = null;
        Logger.i(b, "ThreadPool init!");
        this.f5224a = Executors.newCachedThreadPool(ExecutorsUtils.createThreadFactory("request"));
    }
}
