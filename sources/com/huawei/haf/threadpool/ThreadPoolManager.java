package com.huawei.haf.threadpool;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public final class ThreadPoolManager extends AbstractExecutorService {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ThreadPoolManager f2158a;
    private static volatile ThreadPoolManager b;
    final HealthThreadPoolExecutor e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public interface TaskStatistics {
        void afterExecute(Runnable runnable, Throwable th);

        void beforeExecute(Thread thread, Runnable runnable);
    }

    private ThreadPoolManager(int i, int i2, String str) {
        this.e = new HealthThreadPoolExecutor(i, i2, str);
    }

    public static ThreadPoolManager d() {
        if (f2158a == null) {
            synchronized (ThreadPoolManager.class) {
                if (f2158a == null) {
                    boolean d = ProcessUtil.d();
                    f2158a = e(d ? 10 : 5, d ? 50 : 20, "haf-def");
                }
            }
        }
        return f2158a;
    }

    public static ThreadPoolManager a(int i, int i2) {
        return new ThreadPoolManager(i, i2, null);
    }

    public static ThreadPoolManager e(int i, int i2, String str) {
        return new ThreadPoolManager(i, i2, str);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable instanceof HealthRunnable) {
            this.e.execute(runnable);
        } else if (runnable != null) {
            this.e.execute(new HealthRunnable(runnable));
        }
    }

    public boolean a(Runnable runnable) {
        if (runnable instanceof HealthRunnable) {
            return this.e.remove(runnable);
        }
        if (runnable != null) {
            return this.e.remove(new HealthRunnable(runnable));
        }
        return false;
    }

    public void d(String str, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (str == null) {
            throw new IllegalArgumentException("tag is null");
        }
        execute(new HealthTagRunnable(str, runnable));
    }

    public int e(String str, Runnable runnable) {
        if (str == null) {
            throw new IllegalArgumentException("tag is null");
        }
        if (runnable != null) {
            return this.e.remove(new HealthTagRunnable(str, runnable)) ? 1 : 0;
        }
        return this.e.a(str);
    }

    public void c(String str, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (str == null) {
            throw new IllegalArgumentException("serialTag is null");
        }
        execute(new HealthSerialRunnable(str, runnable));
    }

    public int b(String str, Runnable runnable) {
        if (str == null) {
            throw new IllegalArgumentException("serialTag is null");
        }
        if (runnable != null) {
            return this.e.remove(new HealthSerialRunnable(str, runnable)) ? 1 : 0;
        }
        return this.e.c(str);
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        if (e()) {
            LogUtil.a("HAF_ThreadPoolManager", "Unsupported shutdown default thread pool");
        } else {
            this.e.shutdown();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        if (e()) {
            LogUtil.a("HAF_ThreadPoolManager", "Unsupported shutdownNow default thread pool");
            return Collections.EMPTY_LIST;
        }
        List<Runnable> shutdownNow = this.e.shutdownNow();
        for (int i = 0; i < shutdownNow.size(); i++) {
            Runnable runnable = shutdownNow.get(i);
            if (runnable instanceof HealthRunnable) {
                shutdownNow.set(i, ((HealthRunnable) runnable).d());
            }
        }
        return shutdownNow;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.e.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.e.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.e.awaitTermination(j, timeUnit);
    }

    public void b(TaskStatistics taskStatistics) {
        this.e.a(taskStatistics);
    }

    public String toString() {
        return this.e.b();
    }

    private boolean e() {
        return this == f2158a || this == b;
    }

    static class HealthRunnable implements Runnable {
        private final Runnable c;

        HealthRunnable(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("runnable == null");
            }
            this.c = runnable;
        }

        Runnable d() {
            return this.c;
        }

        String e() {
            return a(this.c.getClass().getName());
        }

        private String a(String str) {
            int lastIndexOf;
            return (!this.c.getClass().isAnonymousClass() || (lastIndexOf = str.lastIndexOf(".")) <= 0) ? str : str.substring(lastIndexOf + 1);
        }

        @Override // java.lang.Runnable
        public void run() {
            this.c.run();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof HealthRunnable) {
                return this.c.equals(((HealthRunnable) obj).c);
            }
            return false;
        }

        public int hashCode() {
            return this.c.hashCode() + super.hashCode();
        }

        public String toString() {
            return a(this.c.toString());
        }
    }

    static class HealthTagRunnable extends HealthRunnable {
        private static final Runnable b = new Runnable() { // from class: com.huawei.haf.threadpool.ThreadPoolManager.HealthTagRunnable.1
            @Override // java.lang.Runnable
            public void run() {
            }
        };
        private final String c;

        HealthTagRunnable(String str, Runnable runnable) {
            super(runnable);
            if (str == null) {
                throw new IllegalArgumentException("tag == null");
            }
            this.c = str;
        }

        HealthTagRunnable(String str) {
            super(b);
            if (str == null) {
                throw new IllegalArgumentException("tag == null");
            }
            this.c = str;
        }

        @Override // com.huawei.haf.threadpool.ThreadPoolManager.HealthRunnable
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof HealthTagRunnable)) {
                return false;
            }
            HealthTagRunnable healthTagRunnable = (HealthTagRunnable) obj;
            if (!this.c.equals(healthTagRunnable.c)) {
                return false;
            }
            Runnable d = d();
            Runnable runnable = b;
            if (d == runnable || healthTagRunnable.d() == runnable) {
                return true;
            }
            return super.equals(obj);
        }

        @Override // com.huawei.haf.threadpool.ThreadPoolManager.HealthRunnable
        public int hashCode() {
            return this.c.hashCode() + super.hashCode();
        }

        @Override // com.huawei.haf.threadpool.ThreadPoolManager.HealthRunnable
        public String toString() {
            return "tag=(" + this.c + "), " + super.toString();
        }
    }

    static class HealthSerialRunnable extends HealthRunnable {
        private HealthSerialRunnable c;
        private final String d;

        HealthSerialRunnable(String str, Runnable runnable) {
            super(runnable);
            if (str == null) {
                throw new IllegalArgumentException("serialTag == null");
            }
            this.d = str;
        }

        @Override // com.huawei.haf.threadpool.ThreadPoolManager.HealthRunnable
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof HealthSerialRunnable) && this.d.equals(((HealthSerialRunnable) obj).d)) {
                return super.equals(obj);
            }
            return false;
        }

        @Override // com.huawei.haf.threadpool.ThreadPoolManager.HealthRunnable
        public int hashCode() {
            return this.d.hashCode() + super.hashCode();
        }

        @Override // com.huawei.haf.threadpool.ThreadPoolManager.HealthRunnable
        public String toString() {
            return "serialTag=(" + this.d + "), " + super.toString();
        }
    }

    public static final class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger d = new AtomicInteger(0);

        /* renamed from: a, reason: collision with root package name */
        private final ThreadGroup f2159a;
        private final AtomicInteger b;
        private final String c;

        DefaultThreadFactory() {
            this("haf-def");
        }

        public DefaultThreadFactory(String str) {
            this.b = new AtomicInteger(1);
            SecurityManager securityManager = System.getSecurityManager();
            this.f2159a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.c = a(str) + Constants.LINK + d.getAndIncrement() + Constants.LINK;
        }

        private String a(String str) {
            if (TextUtils.isEmpty(str)) {
                return "haf-unk";
            }
            if (str.length() <= 5) {
                return "haf-" + str;
            }
            return str.substring(0, Math.min(9, str.length()));
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f2159a, runnable, this.c + this.b.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }

        public String toString() {
            return this.c + this.f2159a.getName();
        }
    }

    static class HealthWrapExecutionHandler implements RejectedExecutionHandler {
        private final BlockingQueue<HealthRunnable> c;
        private volatile boolean e;

        private HealthWrapExecutionHandler() {
            this.c = new LinkedBlockingQueue();
            this.e = true;
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            this.e = true;
        }

        void c(Runnable runnable, HealthThreadPoolExecutor healthThreadPoolExecutor) {
            if ((runnable == null && this.c.isEmpty()) || healthThreadPoolExecutor.isShutdown()) {
                return;
            }
            if (runnable != null && !this.c.offer(e(runnable))) {
                LogUtil.a("HAF_ThreadPoolManager", "task rejected:", runnable.toString());
            }
            if (this.e) {
                d(healthThreadPoolExecutor);
            }
        }

        private void d(HealthThreadPoolExecutor healthThreadPoolExecutor) {
            synchronized (this) {
                try {
                    this.e = false;
                    HealthRunnable peek = this.c.peek();
                    while (peek != null) {
                        healthThreadPoolExecutor.c(peek);
                        if (this.e) {
                            break;
                        }
                        this.c.poll();
                        peek = this.c.peek();
                    }
                } finally {
                    this.e = true;
                }
            }
        }

        boolean a(Runnable runnable, HealthThreadPoolExecutor healthThreadPoolExecutor) {
            boolean z;
            synchronized (this) {
                HealthRunnable e = e(runnable);
                if (!healthThreadPoolExecutor.b(e)) {
                    z = this.c.remove(e);
                }
            }
            return z;
        }

        int c(String str, HealthThreadPoolExecutor healthThreadPoolExecutor) {
            int i;
            synchronized (this) {
                HealthTagRunnable healthTagRunnable = new HealthTagRunnable(str);
                BlockingQueue<Runnable> queue = healthThreadPoolExecutor.getQueue();
                i = 0;
                while (queue.remove(healthTagRunnable)) {
                    i++;
                }
                while (this.c.remove(healthTagRunnable)) {
                    i++;
                }
            }
            return i;
        }

        int b(HealthThreadPoolExecutor healthThreadPoolExecutor) {
            int size;
            int size2;
            synchronized (this) {
                size = healthThreadPoolExecutor.getQueue().size();
                size2 = this.c.size();
            }
            return size + size2;
        }

        void c(HealthThreadPoolExecutor healthThreadPoolExecutor) {
            synchronized (this) {
                healthThreadPoolExecutor.g();
                this.c.clear();
            }
        }

        List<Runnable> a(HealthThreadPoolExecutor healthThreadPoolExecutor) {
            List<Runnable> h;
            synchronized (this) {
                h = healthThreadPoolExecutor.h();
                h.addAll(this.c);
                this.c.clear();
            }
            return h;
        }

        private HealthRunnable e(Runnable runnable) {
            if (runnable instanceof HealthRunnable) {
                return (HealthRunnable) runnable;
            }
            return new HealthRunnable(runnable);
        }
    }

    static final class HealthSerialExecutionHandler {
        private final HealthThreadPoolExecutor c;

        /* renamed from: a, reason: collision with root package name */
        private final HashMap<String, HealthSerialRunnable> f2160a = new HashMap<>();
        private final AtomicInteger d = new AtomicInteger(0);
        private final ThreadLocal<Boolean> b = new ThreadLocal<>();

        HealthSerialExecutionHandler(HealthThreadPoolExecutor healthThreadPoolExecutor) {
            this.c = healthThreadPoolExecutor;
        }

        int e() {
            return this.d.intValue();
        }

        boolean a(HealthSerialRunnable healthSerialRunnable) {
            synchronized (this.f2160a) {
                HealthSerialRunnable healthSerialRunnable2 = this.f2160a.get(healthSerialRunnable.d);
                if (healthSerialRunnable2 == null) {
                    this.f2160a.put(healthSerialRunnable.d, healthSerialRunnable);
                    return false;
                }
                while (healthSerialRunnable2.c != null) {
                    healthSerialRunnable2 = healthSerialRunnable2.c;
                }
                healthSerialRunnable2.c = healthSerialRunnable;
                this.d.getAndIncrement();
                return true;
            }
        }

        void a(Runnable runnable, Throwable th, HealthWrapExecutionHandler healthWrapExecutionHandler) {
            if (runnable instanceof HealthSerialRunnable) {
                if (th != null) {
                    HealthSerialRunnable c = c(((HealthSerialRunnable) runnable).d);
                    if (c != null) {
                        healthWrapExecutionHandler.c(c, this.c);
                        return;
                    }
                    return;
                }
                Boolean bool = this.b.get();
                if (bool == null || !bool.booleanValue()) {
                    this.b.set(Boolean.TRUE);
                    try {
                        d((HealthSerialRunnable) runnable);
                    } finally {
                        this.b.set(Boolean.FALSE);
                    }
                }
            }
        }

        boolean b(HealthSerialRunnable healthSerialRunnable, HealthWrapExecutionHandler healthWrapExecutionHandler) {
            HealthSerialRunnable e;
            synchronized (this.f2160a) {
                boolean a2 = healthWrapExecutionHandler.a(healthSerialRunnable, this.c);
                HealthSerialRunnable healthSerialRunnable2 = this.f2160a.get(healthSerialRunnable.d);
                if (healthSerialRunnable2 == null) {
                    return a2;
                }
                if (!a2) {
                    while (healthSerialRunnable2.c != null) {
                        if (healthSerialRunnable2.c.equals(healthSerialRunnable)) {
                            healthSerialRunnable2.c = healthSerialRunnable2.c.c;
                            this.d.getAndDecrement();
                            return true;
                        }
                        healthSerialRunnable2 = healthSerialRunnable2.c;
                    }
                    return false;
                }
                if (healthSerialRunnable2.equals(healthSerialRunnable) && (e = e(healthSerialRunnable2.d)) != null) {
                    healthWrapExecutionHandler.c(e, this.c);
                }
                return true;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        int c(String str, HealthWrapExecutionHandler healthWrapExecutionHandler) {
            synchronized (this.f2160a) {
                HealthSerialRunnable healthSerialRunnable = this.f2160a.get(str);
                if (healthSerialRunnable == null) {
                    return 0;
                }
                boolean a2 = healthWrapExecutionHandler.a(healthSerialRunnable, this.c);
                HealthSerialRunnable e = e(str);
                int i = a2;
                while (e != null) {
                    e = e(str);
                    i++;
                }
                return i;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(List<Runnable> list) {
            synchronized (this.f2160a) {
                if (this.f2160a.isEmpty()) {
                    return;
                }
                for (String str : (String[]) this.f2160a.keySet().toArray(new String[this.f2160a.size()])) {
                    HealthSerialRunnable e = e(str);
                    while (e != null) {
                        if (list != null) {
                            list.add(e);
                        }
                        e = e(str);
                    }
                }
            }
        }

        private HealthSerialRunnable e(String str) {
            HealthSerialRunnable remove = this.f2160a.remove(str);
            if (remove == null) {
                return null;
            }
            HealthSerialRunnable healthSerialRunnable = remove.c;
            if (healthSerialRunnable == null) {
                return healthSerialRunnable;
            }
            this.f2160a.put(healthSerialRunnable.d, healthSerialRunnable);
            this.d.getAndDecrement();
            return healthSerialRunnable;
        }

        private HealthSerialRunnable c(String str) {
            HealthSerialRunnable e;
            synchronized (this.f2160a) {
                e = e(str);
            }
            return e;
        }

        private void d(HealthSerialRunnable healthSerialRunnable) {
            HealthSerialRunnable c = c(healthSerialRunnable.d);
            while (c != null) {
                a(Thread.currentThread(), c);
                c = c(c.d);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void a(Thread thread, Runnable runnable) {
            this.c.beforeExecute(thread, runnable);
            try {
                try {
                    try {
                        runnable.run();
                        this.c.afterExecute(runnable, null);
                    } catch (Throwable th) {
                        throw new Error(th);
                    }
                } catch (Error e) {
                    throw e;
                } catch (RuntimeException e2) {
                    throw e2;
                }
            } catch (Throwable th2) {
                this.c.afterExecute(runnable, thread);
                throw th2;
            }
        }
    }

    static final class HealthThreadPoolExecutor extends ThreadPoolExecutor {
        private static final DefaultThreadFactory b = new DefaultThreadFactory();

        /* renamed from: a, reason: collision with root package name */
        private final HealthWrapExecutionHandler f2161a;
        private final AtomicLong c;
        private String d;
        private final HealthSerialExecutionHandler e;
        private final AtomicInteger g;
        private TaskStatistics i;
        private TaskStatistics j;

        private static int c(int i) {
            return (i >> 1) + 1;
        }

        HealthThreadPoolExecutor(int i, int i2, String str) {
            this(i, i2, 30L, str);
        }

        HealthThreadPoolExecutor(int i, int i2, long j, String str) {
            this(i, i2, c(i), j, str);
        }

        HealthThreadPoolExecutor(int i, int i2, int i3, long j, String str) {
            this(i, i2, i3, j, new HealthWrapExecutionHandler());
            setThreadFactory(new DefaultThreadFactory(str));
        }

        private HealthThreadPoolExecutor(int i, int i2, int i3, long j, HealthWrapExecutionHandler healthWrapExecutionHandler) {
            super(i, i2, j, TimeUnit.SECONDS, new ArrayBlockingQueue(Math.max(i3, 1)), b, healthWrapExecutionHandler);
            this.c = new AtomicLong(0L);
            this.g = new AtomicInteger(0);
            this.f2161a = healthWrapExecutionHandler;
            this.e = new HealthSerialExecutionHandler(this);
            allowCoreThreadTimeOut(true);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
            throw new UnsupportedOperationException("Unsupported setRejectedExecutionHandler");
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public long getTaskCount() {
            return getCompletedTaskCount() + getActiveCount() + c();
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public long getCompletedTaskCount() {
            return this.c.get();
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if ((runnable instanceof HealthSerialRunnable) && !isShutdown() && this.e.a((HealthSerialRunnable) runnable)) {
                return;
            }
            this.f2161a.c(runnable, this);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public boolean remove(Runnable runnable) {
            if (runnable instanceof HealthSerialRunnable) {
                return this.e.b((HealthSerialRunnable) runnable, this.f2161a);
            }
            return this.f2161a.a(runnable, this);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
        public void shutdown() {
            this.f2161a.c(this);
            this.e.c((List<Runnable>) null);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
        public List<Runnable> shutdownNow() {
            List<Runnable> a2 = this.f2161a.a(this);
            this.e.c(a2);
            return a2;
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        protected void beforeExecute(Thread thread, Runnable runnable) {
            this.f2161a.c((Runnable) null, this);
            this.g.getAndIncrement();
            TaskStatistics i = i();
            if (i != null) {
                i.beforeExecute(thread, runnable);
            }
            super.beforeExecute(thread, runnable);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        protected void afterExecute(Runnable runnable, Throwable th) {
            super.afterExecute(runnable, th);
            this.g.getAndDecrement();
            this.c.getAndIncrement();
            TaskStatistics f = f();
            if (f != null) {
                f.afterExecute(runnable, th);
            }
            this.e.a(runnable, th, this.f2161a);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        protected void terminated() {
            super.terminated();
            LogUtil.c("HAF_ThreadPoolManager", "health thread pool terminated, ", getThreadFactory().toString());
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append(b());
            sb.append(", state{[");
            sb.append(getMaximumPoolSize());
            sb.append(", ");
            sb.append(getCorePoolSize());
            sb.append(", ");
            sb.append(getLargestPoolSize());
            sb.append("], poolSize=");
            sb.append(getPoolSize());
            sb.append(", activeCount=");
            sb.append(getActiveCount());
            sb.append(", runNum=");
            sb.append(d());
            sb.append(", waitNum=(");
            sb.append(c());
            sb.append(", ");
            sb.append(e());
            sb.append(", ");
            sb.append(a());
            sb.append("), cmpNum=");
            sb.append(getCompletedTaskCount());
            return sb.toString();
        }

        int a(String str) {
            return this.f2161a.c(str, this);
        }

        int c(String str) {
            return this.e.c(str, this.f2161a);
        }

        void c(HealthRunnable healthRunnable) {
            super.execute(healthRunnable);
        }

        boolean b(HealthRunnable healthRunnable) {
            return super.remove(healthRunnable);
        }

        void g() {
            super.shutdown();
        }

        List<Runnable> h() {
            return super.shutdownNow();
        }

        int d() {
            return this.g.intValue();
        }

        int c() {
            return this.f2161a.b(this) + this.e.e();
        }

        int e() {
            return getQueue().size();
        }

        int a() {
            return this.e.e();
        }

        String b() {
            if (TextUtils.isEmpty(this.d)) {
                this.d = getThreadFactory().toString();
            }
            return this.d;
        }

        String e(Runnable runnable) {
            if (runnable instanceof HealthRunnable) {
                return ((HealthRunnable) runnable).e();
            }
            return runnable == null ? "" : runnable.toString();
        }

        void a(TaskStatistics taskStatistics) {
            this.i = taskStatistics;
        }

        private TaskStatistics i() {
            TaskStatistics taskStatistics = this.j;
            TaskStatistics taskStatistics2 = this.i;
            if (taskStatistics != taskStatistics2) {
                this.j = taskStatistics2;
            }
            return this.j;
        }

        private TaskStatistics f() {
            return this.j;
        }
    }
}
