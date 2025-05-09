package defpackage;

import defpackage.krt;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public class krr extends ThreadPoolExecutor {
    private Map<String, Long> b;
    private String c;
    private long d;

    static class d implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private String f14560a;
        final AtomicInteger b = new AtomicInteger(1);

        public d(String str) {
            this.f14560a = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, this.f14560a + this.b.getAndIncrement());
        }
    }

    public krr(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, String str) {
        super(i, i2, j, timeUnit, blockingQueue);
        this.c = str;
        setThreadFactory(new d(str));
        this.d = System.currentTimeMillis();
        this.b = new ConcurrentHashMap();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void beforeExecute(Thread thread, Runnable runnable) {
        this.b.put(String.valueOf(runnable.hashCode()), Long.valueOf(System.currentTimeMillis()));
        if (System.currentTimeMillis() - this.d >= 5000) {
            b();
            e();
            this.d = System.currentTimeMillis();
        }
        super.beforeExecute(thread, runnable);
    }

    private void b() {
        new krt.e().c(getActiveCount()).d(getCompletedTaskCount()).e(getTaskCount()).b(getCorePoolSize()).a(getLargestPoolSize()).d(getMaximumPoolSize()).e(getQueue().size());
    }

    private void e() {
        StringBuilder sb = new StringBuilder("thread dump:");
        sb.append("poolName:" + this.c + " activeCount:" + getActiveCount() + " completedTaskCount:" + getCompletedTaskCount() + " taskCount:" + getTaskCount() + " corePoolSize:" + getCorePoolSize() + " largestPoolSize:" + getLargestPoolSize() + " maximumPoolSize:" + getMaximumPoolSize() + " queueSize:" + getQueue().size() + " ");
        ksy.b("ThreadPool", sb.toString(), true);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public void shutdown() {
        ksy.b("ThreadPool", "shutdown", true);
        e();
        super.shutdown();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        ksy.b("ThreadPool", "shutdownNow", true);
        e();
        return super.shutdownNow();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, Throwable th) {
        c(runnable);
        e();
        super.afterExecute(runnable, th);
    }

    private void c(Runnable runnable) {
        Long remove = this.b.remove(String.valueOf(runnable.hashCode()));
        long longValue = remove == null ? 0L : remove.longValue();
        ksy.b("ThreadPool", "afterExecute, time costs:" + (System.currentTimeMillis() - longValue) + "ms", true);
    }
}
