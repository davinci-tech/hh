package com.huawei.haf.threadpool;

import com.huawei.haf.common.dfx.DfxMonitorTask;
import com.huawei.haf.common.dfx.block.ThreadMonitorTask;
import com.huawei.haf.threadpool.ThreadPoolManager;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.LogUtil;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public final class ThreadPoolTaskStatistics implements ThreadPoolManager.TaskStatistics {
    private final ThreadPoolManager.HealthThreadPoolExecutor d;
    private String f;
    private final ThreadPoolCallback h;
    private final ThreadPoolStateMonitor j;

    /* renamed from: a, reason: collision with root package name */
    private final AtomicLong f2163a = new AtomicLong(0);
    private final AtomicLong c = new AtomicLong(0);
    private final AtomicLong e = new AtomicLong(0);
    private final ThreadLocal<ThreadMonitorTask> b = new ThreadLocal<>();
    private final ThreadLocal<Long> g = new ThreadLocal<>();

    /* JADX WARN: Multi-variable type inference failed */
    public ThreadPoolTaskStatistics(ThreadPoolManager threadPoolManager, ThreadPoolCallback threadPoolCallback) {
        this.d = threadPoolManager.e;
        this.h = threadPoolCallback;
        this.j = threadPoolCallback != null ? new ThreadPoolStateMonitor() : null;
    }

    @Override // com.huawei.haf.threadpool.ThreadPoolManager.TaskStatistics
    public void beforeExecute(Thread thread, Runnable runnable) {
        if (this.h == null) {
            return;
        }
        this.j.a();
        if (!this.h.isMonitorTask()) {
            this.g.set(Long.valueOf(System.currentTimeMillis()));
            return;
        }
        ThreadMonitorTask threadMonitorTask = this.b.get();
        if (threadMonitorTask == null) {
            threadMonitorTask = this.h.createBlockMonitorTask(thread);
            this.b.set(threadMonitorTask);
        }
        threadMonitorTask.e(d(runnable));
    }

    @Override // com.huawei.haf.threadpool.ThreadPoolManager.TaskStatistics
    public void afterExecute(Runnable runnable, Throwable th) {
        if (e(th) || th == null) {
            return;
        }
        LogUtil.a("HAF_TaskStatistics", d(runnable), ", throwable=", LogUtil.a(th));
    }

    private boolean e(Throwable th) {
        if (this.h == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.h.isMonitorTask()) {
            c(currentTimeMillis - this.g.get().longValue());
            return false;
        }
        ThreadMonitorTask threadMonitorTask = this.b.get();
        if (threadMonitorTask == null) {
            return false;
        }
        threadMonitorTask.e(th, currentTimeMillis);
        c(currentTimeMillis - threadMonitorTask.a());
        return true;
    }

    private void c(long j) {
        this.f2163a.incrementAndGet();
        this.c.addAndGet(j);
        if (j > this.e.get()) {
            this.e.set(j);
        }
    }

    private String d(Runnable runnable) {
        if (this.f == null) {
            this.f = this.d.b() + ", runnable=";
        }
        return this.f + this.d.e(runnable);
    }

    class ThreadPoolStateMonitor implements DfxMonitorTask {

        /* renamed from: a, reason: collision with root package name */
        private long f2164a;
        private long b;
        private volatile boolean d;
        private long e;
        private long f;

        private ThreadPoolStateMonitor() {
            this.f2164a = System.currentTimeMillis();
        }

        void a() {
            if (this.d) {
                return;
            }
            this.d = true;
            DfxMonitorCenter.d(this);
        }

        @Override // com.huawei.haf.common.dfx.DfxMonitorTask
        public long monitorDelayTime() {
            return ThreadPoolTaskStatistics.this.h.minStatisticsTimeInterval();
        }

        @Override // com.huawei.haf.common.dfx.DfxMonitorTask
        public void onMonitor() {
            long completedTaskCount = ThreadPoolTaskStatistics.this.d.getCompletedTaskCount();
            if (completedTaskCount == this.b && ThreadPoolTaskStatistics.this.d.d() == 0) {
                this.d = false;
            } else {
                b(completedTaskCount);
                DfxMonitorCenter.d(this);
            }
        }

        private void b(long j) {
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = currentTimeMillis - this.f2164a;
            int corePoolSize = ThreadPoolTaskStatistics.this.d.getCorePoolSize();
            int activeCount = ThreadPoolTaskStatistics.this.d.getActiveCount();
            if ((activeCount < corePoolSize || activeCount == ThreadPoolTaskStatistics.this.d.getMaximumPoolSize()) && j2 < ThreadPoolTaskStatistics.this.h.statisticsTimeInterval()) {
                return;
            }
            this.f2164a = currentTimeMillis;
            this.b = j;
            ThreadPoolStateInfo threadPoolStateInfo = new ThreadPoolStateInfo();
            threadPoolStateInfo.a(ThreadPoolTaskStatistics.this.d, activeCount);
            threadPoolStateInfo.d(j2, ThreadPoolTaskStatistics.this.f2163a.get(), ThreadPoolTaskStatistics.this.c.get(), ThreadPoolTaskStatistics.this.e.get());
            e(threadPoolStateInfo);
            ThreadPoolTaskStatistics.this.h.threadPoolStateInfo(ThreadPoolTaskStatistics.this.d.b(), threadPoolStateInfo, currentTimeMillis);
        }

        private void e(ThreadPoolStateInfo threadPoolStateInfo) {
            ThreadPoolTaskStatistics.this.f2163a.getAndSet(0L);
            this.e += ThreadPoolTaskStatistics.this.c.getAndSet(0L);
            long andSet = ThreadPoolTaskStatistics.this.e.getAndSet(0L);
            if (andSet > this.f) {
                this.f = andSet;
            }
            threadPoolStateInfo.e(this.b, this.e, this.f);
        }
    }
}
