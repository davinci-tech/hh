package defpackage;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.SparseArray;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import defpackage.mci;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class mci {

    /* renamed from: a, reason: collision with root package name */
    private static final Handler f14880a;
    private static final SparseArray<e> b;
    public static final HandlerThread c;
    public static final mcs d;
    private static final LifecycleObserver e;
    private static final Handler f;
    private static Looper i;

    static {
        HandlerThread handlerThread = new HandlerThread("BackgroundTaskUtils");
        c = handlerThread;
        d = new mcs(3, 12, 10L, TimeUnit.SECONDS);
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        i = looper;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        f = new Handler(looper);
        f14880a = new Handler(Looper.getMainLooper());
        e = new LifecycleObserver() { // from class: com.huawei.picture.security.base.utils.BackgroundTaskUtils$1
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            public void onDestroy(LifecycleOwner lifecycleOwner) {
                lifecycleOwner.getLifecycle().removeObserver(this);
                mci.b(lifecycleOwner.hashCode());
            }
        };
        b = new SparseArray<>();
    }

    private mci() {
    }

    public static void b(Runnable runnable) {
        e(null, runnable);
    }

    public static void e(LifecycleOwner lifecycleOwner, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (lifecycleOwner != null) {
            runnable = a(lifecycleOwner.hashCode(), runnable);
        }
        f.removeCallbacks(runnable);
    }

    public static void c(Runnable runnable, long j) {
        c(null, runnable, j);
    }

    public static void c(final LifecycleOwner lifecycleOwner, final Runnable runnable, long j) {
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(e);
            Runnable runnable2 = new Runnable() { // from class: mch
                @Override // java.lang.Runnable
                public final void run() {
                    mci.c(runnable, lifecycleOwner);
                }
            };
            d(lifecycleOwner.hashCode(), runnable, runnable2);
            f14880a.postDelayed(runnable2, j);
            return;
        }
        f14880a.postDelayed(runnable, j);
    }

    static /* synthetic */ void c(Runnable runnable, LifecycleOwner lifecycleOwner) {
        runnable.run();
        c(lifecycleOwner.hashCode(), runnable);
    }

    public static void a(Runnable runnable) {
        c((LifecycleOwner) null, runnable);
    }

    public static void c(LifecycleOwner lifecycleOwner, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (lifecycleOwner != null) {
            runnable = c(lifecycleOwner.hashCode(), runnable);
        }
        f14880a.removeCallbacks(runnable);
    }

    public static Future<?> c(Runnable runnable) {
        return b(0, runnable);
    }

    public static Future<?> b(int i2, Runnable runnable) {
        return d((LifecycleOwner) null, i2, runnable);
    }

    public static Future<?> d(final LifecycleOwner lifecycleOwner, int i2, final Runnable runnable) {
        Future<?> d2;
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(e);
            d2 = d.d(i2, new Runnable() { // from class: mcg
                @Override // java.lang.Runnable
                public final void run() {
                    mci.d(runnable, lifecycleOwner);
                }
            });
            a(lifecycleOwner.hashCode(), runnable, d2);
        } else {
            d2 = d.d(i2, runnable);
        }
        if (d2.isCancelled()) {
            mcj.d("BackgroundTaskUtils", "submit task,  Future is cancelled");
        }
        return d2;
    }

    static /* synthetic */ void d(Runnable runnable, LifecycleOwner lifecycleOwner) {
        runnable.run();
        d(lifecycleOwner.hashCode(), runnable);
    }

    public static void d(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        d.remove(runnable);
    }

    private static e e(int i2) {
        e eVar;
        synchronized (mci.class) {
            SparseArray<e> sparseArray = b;
            eVar = sparseArray.get(i2);
            if (eVar == null) {
                eVar = new e();
            }
            sparseArray.put(i2, eVar);
        }
        return eVar;
    }

    private static void a(int i2, Runnable runnable, Future<?> future) {
        synchronized (mci.class) {
            e(i2).e(runnable, future);
        }
    }

    private static void d(int i2, Runnable runnable, Runnable runnable2) {
        synchronized (mci.class) {
            e(i2).c(runnable, runnable2);
        }
    }

    private static Future<?> d(int i2, Runnable runnable) {
        synchronized (mci.class) {
            e eVar = b.get(i2);
            if (eVar == null) {
                return null;
            }
            return eVar.c(runnable);
        }
    }

    private static Runnable a(int i2, Runnable runnable) {
        synchronized (mci.class) {
            e eVar = b.get(i2);
            if (eVar == null) {
                return null;
            }
            return eVar.e(runnable);
        }
    }

    private static Runnable c(int i2, Runnable runnable) {
        synchronized (mci.class) {
            e eVar = b.get(i2);
            if (eVar == null) {
                return null;
            }
            return eVar.d(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i2) {
        synchronized (mci.class) {
            SparseArray<e> sparseArray = b;
            e eVar = sparseArray.get(i2);
            if (eVar != null) {
                eVar.b();
                sparseArray.put(i2, null);
            }
        }
    }

    /* loaded from: classes6.dex */
    static class e {

        /* renamed from: a, reason: collision with root package name */
        private final HashMap<Runnable, Runnable> f14881a;
        private final HashMap<Runnable, Future<?>> b;
        private final HashMap<Runnable, Runnable> e;

        private e() {
            this.b = new HashMap<>();
            this.e = new HashMap<>();
            this.f14881a = new HashMap<>();
        }

        public void e(Runnable runnable, Future<?> future) {
            synchronized (this) {
                this.b.put(runnable, future);
            }
        }

        public void c(Runnable runnable, Runnable runnable2) {
            synchronized (this) {
                this.f14881a.put(runnable, runnable2);
            }
        }

        public Future<?> c(Runnable runnable) {
            Future<?> remove;
            synchronized (this) {
                remove = this.b.remove(runnable);
            }
            return remove;
        }

        public Runnable e(Runnable runnable) {
            Runnable remove;
            synchronized (this) {
                remove = this.e.remove(runnable);
            }
            return remove;
        }

        public Runnable d(Runnable runnable) {
            Runnable remove;
            synchronized (this) {
                remove = this.f14881a.remove(runnable);
            }
            return remove;
        }

        public void b() {
            synchronized (this) {
                Iterator<Map.Entry<Runnable, Future<?>>> it = this.b.entrySet().iterator();
                while (it.hasNext()) {
                    Future<?> value = it.next().getValue();
                    if (value != null && !value.isDone()) {
                        value.cancel(true);
                        if (value instanceof Runnable) {
                            mci.d((Runnable) value);
                        }
                    }
                }
                this.b.clear();
                Iterator<Map.Entry<Runnable, Runnable>> it2 = this.e.entrySet().iterator();
                while (it2.hasNext()) {
                    mci.b(it2.next().getValue());
                }
                this.e.clear();
                Iterator<Map.Entry<Runnable, Runnable>> it3 = this.f14881a.entrySet().iterator();
                while (it3.hasNext()) {
                    mci.a(it3.next().getValue());
                }
                this.f14881a.clear();
            }
        }
    }
}
