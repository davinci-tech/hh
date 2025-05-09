package com.huawei.watchface.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.SparseArray;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.huawei.watchface.fg;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public final class BackgroundTaskUtils {

    /* renamed from: a, reason: collision with root package name */
    public static final HandlerThread f11187a;
    public static final fg b;
    private static Looper c;
    private static final Handler d;
    private static final Handler e;
    private static final LifecycleObserver f;
    private static final SparseArray<a> g;

    static {
        HandlerThread handlerThread = new HandlerThread("BackgroundTaskUtils");
        f11187a = handlerThread;
        b = new fg(5, 20, 10L, TimeUnit.SECONDS);
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        c = looper;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        d = new Handler(looper);
        e = new Handler(Looper.getMainLooper());
        f = new LifecycleObserver() { // from class: com.huawei.watchface.utils.BackgroundTaskUtils.1
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            public void onDestroy(LifecycleOwner lifecycleOwner) {
                lifecycleOwner.getLifecycle().removeObserver(this);
                BackgroundTaskUtils.c(lifecycleOwner.hashCode());
            }
        };
        g = new SparseArray<>();
    }

    private BackgroundTaskUtils() {
    }

    public static void a(Runnable runnable) {
        a((LifecycleOwner) null, runnable);
    }

    public static void a(final LifecycleOwner lifecycleOwner, final Runnable runnable) {
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(f);
            Runnable runnable2 = new Runnable() { // from class: com.huawei.watchface.utils.BackgroundTaskUtils$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    BackgroundTaskUtils.e(runnable, lifecycleOwner);
                }
            };
            a(lifecycleOwner.hashCode(), runnable, runnable2);
            d.post(runnable2);
            return;
        }
        d.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(Runnable runnable, LifecycleOwner lifecycleOwner) {
        runnable.run();
        c(lifecycleOwner.hashCode(), runnable);
    }

    public static void a(Runnable runnable, int i) {
        a((LifecycleOwner) null, runnable, i);
    }

    public static void a(final LifecycleOwner lifecycleOwner, final Runnable runnable, int i) {
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(f);
            Runnable runnable2 = new Runnable() { // from class: com.huawei.watchface.utils.BackgroundTaskUtils$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    BackgroundTaskUtils.d(runnable, lifecycleOwner);
                }
            };
            a(lifecycleOwner.hashCode(), runnable, runnable2);
            d.postDelayed(runnable2, i);
            return;
        }
        d.postDelayed(runnable, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(Runnable runnable, LifecycleOwner lifecycleOwner) {
        runnable.run();
        c(lifecycleOwner.hashCode(), runnable);
    }

    public static void b(Runnable runnable) {
        b((LifecycleOwner) null, runnable);
    }

    public static void b(LifecycleOwner lifecycleOwner, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (lifecycleOwner != null) {
            runnable = c(lifecycleOwner.hashCode(), runnable);
        }
        d.removeCallbacks(runnable);
    }

    public static void postInMainThread(Runnable runnable) {
        c((LifecycleOwner) null, runnable);
    }

    public static void c(final LifecycleOwner lifecycleOwner, final Runnable runnable) {
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(f);
            Runnable runnable2 = new Runnable() { // from class: com.huawei.watchface.utils.BackgroundTaskUtils$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BackgroundTaskUtils.c(runnable, lifecycleOwner);
                }
            };
            b(lifecycleOwner.hashCode(), runnable, runnable2);
            e.post(runnable2);
            return;
        }
        e.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(Runnable runnable, LifecycleOwner lifecycleOwner) {
        runnable.run();
        d(lifecycleOwner.hashCode(), runnable);
    }

    public static void postDelayedInMainThread(Runnable runnable, long j) {
        a((LifecycleOwner) null, runnable, j);
    }

    public static void a(final LifecycleOwner lifecycleOwner, final Runnable runnable, long j) {
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(f);
            Runnable runnable2 = new Runnable() { // from class: com.huawei.watchface.utils.BackgroundTaskUtils$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BackgroundTaskUtils.b(runnable, lifecycleOwner);
                }
            };
            b(lifecycleOwner.hashCode(), runnable, runnable2);
            e.postDelayed(runnable2, j);
            return;
        }
        e.postDelayed(runnable, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Runnable runnable, LifecycleOwner lifecycleOwner) {
        runnable.run();
        d(lifecycleOwner.hashCode(), runnable);
    }

    public static void removeTaskFromMainWorker(Runnable runnable) {
        d((LifecycleOwner) null, runnable);
    }

    public static void d(LifecycleOwner lifecycleOwner, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (lifecycleOwner != null) {
            runnable = d(lifecycleOwner.hashCode(), runnable);
        }
        e.removeCallbacks(runnable);
    }

    public static Future<?> submit(Runnable runnable) {
        return a(0, runnable);
    }

    public static Future<?> a(int i, Runnable runnable) {
        return a((LifecycleOwner) null, i, runnable);
    }

    public static Future<?> a(final LifecycleOwner lifecycleOwner, int i, final Runnable runnable) {
        Future<?> b2;
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(f);
            b2 = b.b(i, new Runnable() { // from class: com.huawei.watchface.utils.BackgroundTaskUtils$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    BackgroundTaskUtils.a(runnable, lifecycleOwner);
                }
            });
            a(lifecycleOwner.hashCode(), runnable, b2);
        } else {
            b2 = b.b(i, runnable);
        }
        if (b2.isCancelled()) {
            HwLog.w("BackgroundTaskUtils", "submit task,  Future is cancelled");
        }
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Runnable runnable, LifecycleOwner lifecycleOwner) {
        runnable.run();
        b(lifecycleOwner.hashCode(), runnable);
    }

    public static void removeThreadPoolTask(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        b.remove(runnable);
    }

    public static boolean a() {
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }

    private static a b(int i) {
        a aVar;
        synchronized (BackgroundTaskUtils.class) {
            SparseArray<a> sparseArray = g;
            aVar = sparseArray.get(i);
            if (aVar == null) {
                aVar = new a();
            }
            sparseArray.put(i, aVar);
        }
        return aVar;
    }

    private static void a(int i, Runnable runnable, Future<?> future) {
        synchronized (BackgroundTaskUtils.class) {
            b(i).a(runnable, future);
        }
    }

    private static void a(int i, Runnable runnable, Runnable runnable2) {
        synchronized (BackgroundTaskUtils.class) {
            b(i).a(runnable, runnable2);
        }
    }

    private static void b(int i, Runnable runnable, Runnable runnable2) {
        synchronized (BackgroundTaskUtils.class) {
            b(i).b(runnable, runnable2);
        }
    }

    private static Future<?> b(int i, Runnable runnable) {
        synchronized (BackgroundTaskUtils.class) {
            a aVar = g.get(i);
            if (aVar == null) {
                return null;
            }
            return aVar.a(runnable);
        }
    }

    private static Runnable c(int i, Runnable runnable) {
        synchronized (BackgroundTaskUtils.class) {
            a aVar = g.get(i);
            if (aVar == null) {
                return null;
            }
            return aVar.b(runnable);
        }
    }

    private static Runnable d(int i, Runnable runnable) {
        synchronized (BackgroundTaskUtils.class) {
            a aVar = g.get(i);
            if (aVar == null) {
                return null;
            }
            return aVar.c(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(int i) {
        synchronized (BackgroundTaskUtils.class) {
            SparseArray<a> sparseArray = g;
            a aVar = sparseArray.get(i);
            if (aVar != null) {
                aVar.a();
                sparseArray.put(i, null);
            }
        }
    }

    /* loaded from: classes7.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private HashMap<Runnable, Future<?>> f11188a;
        private HashMap<Runnable, Runnable> b;
        private HashMap<Runnable, Runnable> c;

        private a() {
            this.f11188a = new HashMap<>();
            this.b = new HashMap<>();
            this.c = new HashMap<>();
        }

        public void a(Runnable runnable, Future<?> future) {
            synchronized (this) {
                this.f11188a.put(runnable, future);
            }
        }

        public void a(Runnable runnable, Runnable runnable2) {
            synchronized (this) {
                this.b.put(runnable, runnable2);
            }
        }

        public void b(Runnable runnable, Runnable runnable2) {
            synchronized (this) {
                this.c.put(runnable, runnable2);
            }
        }

        public Future<?> a(Runnable runnable) {
            Future<?> remove;
            synchronized (this) {
                remove = this.f11188a.remove(runnable);
            }
            return remove;
        }

        public Runnable b(Runnable runnable) {
            Runnable remove;
            synchronized (this) {
                remove = this.b.remove(runnable);
            }
            return remove;
        }

        public Runnable c(Runnable runnable) {
            Runnable remove;
            synchronized (this) {
                remove = this.c.remove(runnable);
            }
            return remove;
        }

        public void a() {
            synchronized (this) {
                this.f11188a.entrySet().forEach(new Consumer() { // from class: com.huawei.watchface.utils.BackgroundTaskUtils$a$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        BackgroundTaskUtils.a.c((Map.Entry) obj);
                    }
                });
                this.f11188a.clear();
                this.b.entrySet().forEach(new Consumer() { // from class: com.huawei.watchface.utils.BackgroundTaskUtils$a$$ExternalSyntheticLambda1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        BackgroundTaskUtils.a.b((Map.Entry) obj);
                    }
                });
                this.b.clear();
                this.c.entrySet().forEach(new Consumer() { // from class: com.huawei.watchface.utils.BackgroundTaskUtils$a$$ExternalSyntheticLambda2
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        BackgroundTaskUtils.a.a((Map.Entry) obj);
                    }
                });
                this.c.clear();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void c(Map.Entry entry) {
            Future future = (Future) entry.getValue();
            if (future == null || future.isDone()) {
                return;
            }
            future.cancel(true);
            if (future instanceof Runnable) {
                BackgroundTaskUtils.removeThreadPoolTask((Runnable) future);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void b(Map.Entry entry) {
            BackgroundTaskUtils.b((Runnable) entry.getValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a(Map.Entry entry) {
            BackgroundTaskUtils.removeTaskFromMainWorker((Runnable) entry.getValue());
        }
    }
}
