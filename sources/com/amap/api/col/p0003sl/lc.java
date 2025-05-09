package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.lb;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public abstract class lc {

    /* renamed from: a, reason: collision with root package name */
    protected ThreadPoolExecutor f1319a;
    private ConcurrentHashMap<lb, Future<?>> c = new ConcurrentHashMap<>();
    protected lb.a b = new lb.a() { // from class: com.amap.api.col.3sl.lc.1
        @Override // com.amap.api.col.3sl.lb.a
        public final void a(lb lbVar) {
            lc.this.a(lbVar, false);
        }

        @Override // com.amap.api.col.3sl.lb.a
        public final void b(lb lbVar) {
            lc.this.a(lbVar, true);
        }
    };

    private boolean b(lb lbVar) {
        boolean z;
        synchronized (this) {
            try {
                z = this.c.containsKey(lbVar);
            } catch (Throwable th) {
                iv.c(th, "TPool", "contain");
                th.printStackTrace();
                z = false;
            }
        }
        return z;
    }

    private void a(lb lbVar, Future<?> future) {
        synchronized (this) {
            try {
                this.c.put(lbVar, future);
            } catch (Throwable th) {
                iv.c(th, "TPool", "addQueue");
                th.printStackTrace();
            }
        }
    }

    protected final void a(lb lbVar, boolean z) {
        synchronized (this) {
            try {
                Future<?> remove = this.c.remove(lbVar);
                if (z && remove != null) {
                    remove.cancel(true);
                }
            } catch (Throwable th) {
                iv.c(th, "TPool", "removeQueue");
                th.printStackTrace();
            }
        }
    }

    public final void a(lb lbVar) {
        ThreadPoolExecutor threadPoolExecutor;
        if (b(lbVar) || (threadPoolExecutor = this.f1319a) == null || threadPoolExecutor.isShutdown()) {
            return;
        }
        lbVar.f = this.b;
        try {
            Future<?> submit = this.f1319a.submit(lbVar);
            if (submit == null) {
                return;
            }
            a(lbVar, submit);
        } catch (RejectedExecutionException e) {
            iv.c(e, "TPool", "addTask");
        }
    }

    public final Executor d() {
        return this.f1319a;
    }

    public final void a(long j, TimeUnit timeUnit) {
        try {
            ThreadPoolExecutor threadPoolExecutor = this.f1319a;
            if (threadPoolExecutor != null) {
                threadPoolExecutor.awaitTermination(j, timeUnit);
            }
        } catch (InterruptedException unused) {
        }
    }

    public final void e() {
        try {
            Iterator<Map.Entry<lb, Future<?>>> it = this.c.entrySet().iterator();
            while (it.hasNext()) {
                Future<?> future = this.c.get(it.next().getKey());
                if (future != null) {
                    try {
                        future.cancel(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.c.clear();
        } catch (Throwable th) {
            iv.c(th, "TPool", "destroy");
            th.printStackTrace();
        }
        ThreadPoolExecutor threadPoolExecutor = this.f1319a;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
    }
}
