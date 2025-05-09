package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public final class iv extends is implements Thread.UncaughtExceptionHandler {
    private static ExecutorService e;
    private static WeakReference<Context> g;
    private Context d;
    private static Set<Integer> f = Collections.synchronizedSet(new HashSet());
    private static final ThreadFactory h = new ThreadFactory() { // from class: com.amap.api.col.3sl.iv.2

        /* renamed from: a, reason: collision with root package name */
        private final AtomicInteger f1201a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "pama#" + this.f1201a.getAndIncrement()) { // from class: com.amap.api.col.3sl.iv.2.1
                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    try {
                        super.run();
                    } catch (Throwable unused) {
                    }
                }
            };
        }
    };

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            g = new WeakReference<>(context.getApplicationContext());
        } catch (Throwable unused) {
        }
    }

    public static iv a(Context context, hz hzVar) throws hm {
        synchronized (iv.class) {
            if (hzVar == null) {
                throw new hm("sdk info is null");
            }
            if (hzVar.a() == null || "".equals(hzVar.a())) {
                throw new hm("sdk name is invalid");
            }
            try {
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (!f.add(Integer.valueOf(hzVar.hashCode()))) {
                return (iv) is.f1196a;
            }
            if (is.f1196a == null) {
                is.f1196a = new iv(context);
            } else {
                is.f1196a.c = false;
            }
            is.f1196a.a(hzVar, is.f1196a.c);
            return (iv) is.f1196a;
        }
    }

    public static void b() {
        synchronized (iv.class) {
            try {
                ExecutorService executorService = e;
                if (executorService != null) {
                    executorService.shutdown();
                }
                jq.a();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (is.f1196a != null && Thread.getDefaultUncaughtExceptionHandler() == is.f1196a && is.f1196a.b != null) {
                    Thread.setDefaultUncaughtExceptionHandler(is.f1196a.b);
                }
                is.f1196a = null;
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    @Override // com.amap.api.col.p0003sl.is
    protected final void a(hz hzVar, String str, String str2) {
        iw.a(hzVar, this.d, str2, str);
    }

    @Override // com.amap.api.col.p0003sl.is
    protected final void a(Throwable th, int i, String str, String str2) {
        iw.a(this.d, th, i, str, str2);
    }

    public static void a(Context context, hz hzVar, String str, String str2, String str3) {
        iw.a(context, hzVar, str, 0, str2, str3);
    }

    public static void b(Context context, hz hzVar, String str, String str2, String str3) {
        iw.a(context, hzVar, str, 1, str2, str3);
    }

    @Override // com.amap.api.col.p0003sl.is
    protected final void a() {
        it.a(this.d);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        if (th == null) {
            return;
        }
        a(th, 0, null, null);
        if (this.b != null) {
            try {
                Thread.setDefaultUncaughtExceptionHandler(this.b);
            } catch (Throwable unused) {
            }
            this.b.uncaughtException(thread, th);
        }
    }

    @Override // com.amap.api.col.p0003sl.is
    protected final void a(final hz hzVar, final boolean z) {
        try {
            la.a().a(new lb() { // from class: com.amap.api.col.3sl.iv.1
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    try {
                        synchronized (Looper.getMainLooper()) {
                            it.a(hzVar);
                        }
                        if (z) {
                            iw.a(iv.this.d);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void b(Throwable th, String str, String str2) {
        if (th == null) {
            return;
        }
        try {
            a(th, 1, str, str2);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private iv(Context context) {
        this.d = context;
        try {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
                return;
            }
            String obj = this.b.toString();
            if (!obj.startsWith("com.amap.apis.utils.core.dynamiccore") && (obj.indexOf("com.amap.api") != -1 || obj.indexOf("com.loc") != -1)) {
                this.c = false;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void c() {
        WeakReference<Context> weakReference = g;
        if (weakReference != null && weakReference.get() != null) {
            it.a(g.get());
        } else if (is.f1196a != null) {
            is.f1196a.a();
        }
    }

    public static void c(Throwable th, String str, String str2) {
        try {
            if (is.f1196a != null) {
                is.f1196a.a(th, 1, str, str2);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(hz hzVar, String str, String str2, String str3, String str4) {
        a(hzVar, str, str2, str3, "", str4);
    }

    public static void a(hz hzVar, String str, String str2, String str3, String str4, String str5) {
        try {
            if (is.f1196a != null) {
                is.f1196a.a(hzVar, "path:" + str + ",type:" + str2 + ",gsid:" + str3 + ",csid:" + str4 + ",code:" + str5, "networkError");
            }
        } catch (Throwable unused) {
        }
    }

    public static void b(hz hzVar, String str, String str2) {
        try {
            if (is.f1196a != null) {
                is.f1196a.a(hzVar, str, str2);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(hz hzVar, String str, hm hmVar) {
        if (hmVar != null) {
            a(hzVar, str, hmVar.c(), hmVar.d(), hmVar.e(), hmVar.b());
        }
    }

    @Deprecated
    public static ExecutorService d() {
        ExecutorService executorService;
        synchronized (iv.class) {
            try {
                ExecutorService executorService2 = e;
                if (executorService2 == null || executorService2.isShutdown()) {
                    e = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(256), h);
                }
            } catch (Throwable unused) {
            }
            executorService = e;
        }
        return executorService;
    }

    public static iv e() {
        iv ivVar;
        synchronized (iv.class) {
            ivVar = (iv) is.f1196a;
        }
        return ivVar;
    }
}
