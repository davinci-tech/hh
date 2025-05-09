package com.huawei.haf.common.dfx.block;

import com.huawei.haf.threadpool.ThreadPoolCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.haf.threadpool.ThreadPoolTaskStatistics;

/* loaded from: classes.dex */
public final class ThreadPoolMonitor {
    private ThreadPoolMonitor() {
    }

    public static void a(ThreadPoolCallback threadPoolCallback) {
        e(ThreadPoolManager.d(), threadPoolCallback);
    }

    public static void e(ThreadPoolManager threadPoolManager, ThreadPoolCallback threadPoolCallback) {
        if (threadPoolCallback == null) {
            threadPoolCallback = new DefaultThreadPoolHandler("HAF_PoolMonitor");
        }
        threadPoolManager.b(new ThreadPoolTaskStatistics(threadPoolManager, threadPoolCallback));
    }

    public static void d() {
        c(ThreadPoolManager.d());
    }

    public static void c(ThreadPoolManager threadPoolManager) {
        threadPoolManager.b(null);
    }
}
