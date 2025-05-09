package com.huawei.openalliance.ad.utils;

import android.os.Looper;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.openalliance.ad.ho;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public abstract class m {

    /* renamed from: a, reason: collision with root package name */
    private static Map<a, ExecutorService> f7711a;

    public enum a {
        IO,
        NETWORK,
        NETWORK_SEQUENCE,
        CALCULATION,
        SEQUENCE,
        DOWNLOAD,
        EVENT,
        SYNC_CALL,
        SPLASH_NET,
        DISK_CACHE,
        DEVICE,
        WEBVIEW_PRELOAD,
        COLLECT_SEQUENCE,
        SOCKET,
        PARALLEL
    }

    public static void k(Runnable runnable) {
        a(runnable, a.SOCKET, false);
    }

    public static void j(Runnable runnable) {
        a(runnable, a.COLLECT_SEQUENCE, false);
    }

    public static void i(Runnable runnable) {
        a(runnable, a.DEVICE, false);
    }

    public static void h(Runnable runnable) {
        a(runnable, a.EVENT, false);
    }

    public static void g(Runnable runnable) {
        a(runnable, a.SEQUENCE, false);
    }

    public static void f(Runnable runnable) {
        a(runnable, a.CALCULATION, false);
    }

    public static void e(Runnable runnable) {
        a(runnable, a.WEBVIEW_PRELOAD, false);
    }

    public static void d(Runnable runnable) {
        a(runnable, a.DOWNLOAD, false);
    }

    public static void c(Runnable runnable) {
        a(runnable, a.NETWORK_SEQUENCE, false);
    }

    private static boolean b() {
        Looper mainLooper = Looper.getMainLooper();
        return mainLooper != null && Thread.currentThread() == mainLooper.getThread();
    }

    public static void b(Runnable runnable) {
        a(runnable, a.NETWORK, false);
    }

    public static void a(Runnable runnable, a aVar, boolean z) {
        if (runnable == null) {
            return;
        }
        if (z && !b()) {
            new df(runnable).run();
            return;
        }
        ExecutorService executorService = f7711a.get(aVar);
        if (executorService != null) {
            executorService.execute(new df(runnable));
        } else {
            ho.c("AsyncExec", "no executor for type: %s", aVar);
        }
    }

    public static void a(Runnable runnable) {
        a(runnable, a.IO, false);
    }

    private static void a() {
        synchronized (m.class) {
            if (f7711a == null) {
                HashMap hashMap = new HashMap();
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("PARALLEL", 10));
                ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("IO"));
                threadPoolExecutor2.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(3, 5, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("Net"));
                threadPoolExecutor3.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor4 = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(10), new n("NetSeq"), new ThreadPoolExecutor.DiscardOldestPolicy());
                threadPoolExecutor4.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor5 = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(10), new n("Download"), new ThreadPoolExecutor.DiscardOldestPolicy());
                threadPoolExecutor5.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor6 = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(10), new n("WebViewDownload"), new ThreadPoolExecutor.DiscardOldestPolicy());
                threadPoolExecutor5.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor7 = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("Cal"));
                threadPoolExecutor7.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor8 = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("Seq"));
                threadPoolExecutor8.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor9 = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n(Event.TAG));
                threadPoolExecutor9.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor10 = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("SyncCall", 10));
                threadPoolExecutor10.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor11 = new ThreadPoolExecutor(0, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("SplashNet", 10));
                threadPoolExecutor11.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor12 = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("DiskCache"));
                threadPoolExecutor8.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor13 = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("Device"));
                threadPoolExecutor13.allowCoreThreadTimeOut(true);
                ThreadPoolExecutor threadPoolExecutor14 = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("ClctSeq"));
                ThreadPoolExecutor threadPoolExecutor15 = new ThreadPoolExecutor(0, 8, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new n("Media"));
                threadPoolExecutor15.allowCoreThreadTimeOut(true);
                hashMap.put(a.PARALLEL, threadPoolExecutor);
                hashMap.put(a.IO, threadPoolExecutor2);
                hashMap.put(a.NETWORK, threadPoolExecutor3);
                hashMap.put(a.DOWNLOAD, threadPoolExecutor5);
                hashMap.put(a.WEBVIEW_PRELOAD, threadPoolExecutor6);
                hashMap.put(a.CALCULATION, threadPoolExecutor7);
                hashMap.put(a.SEQUENCE, threadPoolExecutor8);
                hashMap.put(a.EVENT, threadPoolExecutor9);
                hashMap.put(a.SYNC_CALL, threadPoolExecutor10);
                hashMap.put(a.SPLASH_NET, threadPoolExecutor11);
                hashMap.put(a.DISK_CACHE, threadPoolExecutor12);
                hashMap.put(a.DEVICE, threadPoolExecutor13);
                hashMap.put(a.COLLECT_SEQUENCE, threadPoolExecutor14);
                hashMap.put(a.SOCKET, threadPoolExecutor15);
                hashMap.put(a.NETWORK_SEQUENCE, threadPoolExecutor4);
                f7711a = hashMap;
            }
        }
    }

    static <V> Future<V> a(Callable<V> callable, a aVar) {
        return f7711a.get(aVar).submit(callable);
    }

    public static <T> List<Future<T>> a(Collection<? extends Callable<T>> collection, long j) {
        try {
            ExecutorService executorService = f7711a.get(a.PARALLEL);
            if (executorService != null) {
                return executorService.invokeAll(collection, j, TimeUnit.MILLISECONDS);
            }
            return null;
        } catch (Throwable unused) {
            ho.c("AsyncExec", "parallel tasks fail");
            return null;
        }
    }

    static {
        a();
    }
}
