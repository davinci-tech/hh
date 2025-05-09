package com.huawei.hms.network;

import android.content.Context;
import com.huawei.hms.framework.common.ActivityUtil;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes4.dex */
public abstract class NetworkKit {

    /* renamed from: a, reason: collision with root package name */
    private static final String f5106a = "NetworkKit";
    private static final int b = 1000;
    private static String c;
    private static volatile NetworkKit d;
    private static RemoteInitializer e;
    private static Context f;
    private static volatile Future g;
    private static final Lock h = new ReentrantLock();
    private static ExecutorService i = ExecutorsUtils.newSingleThreadExecutor("NK");

    public static abstract class Callback {
        public abstract void onResult(boolean z);
    }

    public abstract void addQuicHint(boolean z, String... strArr);

    public abstract void evictAllConnections();

    public abstract String getOption(String str);

    public abstract void initConnectionPool(int i2, long j, TimeUnit timeUnit);

    protected abstract void initKit(Context context, String str);

    public abstract void setOptions(String str);

    public static Future init(Context context, Callback callback, String str) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        Logger.i(f5106a, "init  Version:" + Version.getVersion() + "  BuildTime:" + Version.getBuildTime() + " cp:" + context.getPackageName());
        Lock lock = h;
        lock.lock();
        try {
            ContextHolder.setAppContext(context.getApplicationContext());
            f = ContextHolder.getAppContext();
            ActivityUtil.getInstance().register();
            Future a2 = a(callback, str);
            lock.unlock();
            return a2;
        } catch (Throwable th) {
            h.unlock();
            throw th;
        }
    }

    public static Future init(Context context, Callback callback) {
        return init(context, callback, "");
    }

    static RemoteInitializer getRemoteInitializer() {
        return e;
    }

    public static NetworkKit getInstance() {
        NetworkKitProvider enableProvider;
        synchronized (NetworkKit.class) {
            if (d != null) {
                return d;
            }
            if (a()) {
                Logger.i(f5106a, "dynamic load");
                if (e == null) {
                    e = new RemoteInitializer();
                }
                Future init = getRemoteInitializer().init(getContext());
                if (init != null) {
                    try {
                        init.get(1000L, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException | ExecutionException | TimeoutException e2) {
                        Logger.e(f5106a, "create exception = " + e2.getClass().getSimpleName());
                    }
                }
                enableProvider = NetworkKitProvider.getEnableProvider(true);
            } else {
                Logger.i(f5106a, "not dynamic load");
                enableProvider = NetworkKitProvider.loadLocalProvider();
            }
            d = enableProvider.createNetworkKit();
            d.initKit(f, c);
            return d;
        }
    }

    static Context getContext() {
        Context context = f;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("you must call init(final Context context, final Callback callback) or init(final Context context, final Callback callback, final String options) before all Method");
    }

    private static boolean a() {
        return com.huawei.hms.network.api.a.a(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Callback callback) {
        boolean z = d != null;
        if (z) {
            Logger.i(f5106a, "init success");
        } else {
            Logger.e(f5106a, "you must implementation network-embedded sdk, and ensure the context is kitself or appself");
        }
        if (callback != null) {
            callback.onResult(z);
        }
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Callback f5107a;

        @Override // java.lang.Runnable
        public void run() {
            NetworkKit.getInstance();
            NetworkKit.a(this.f5107a);
        }

        a(Callback callback) {
            this.f5107a = callback;
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Callback f5108a;

        @Override // java.lang.Runnable
        public void run() {
            NetworkKit.a(this.f5108a);
        }

        b(Callback callback) {
            this.f5108a = callback;
        }
    }

    private static Future a(Callback callback, String str) {
        try {
            if (g == null) {
                c = str;
                g = i.submit(new a(callback));
            } else {
                i.execute(new b(callback));
            }
        } catch (RejectedExecutionException unused) {
            Logger.e(f5106a, "init networkkit error, please try later");
        }
        return g;
    }
}
