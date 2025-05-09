package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class r0 {
    public static final String f = "TaskManager";
    public static final String g = "lookupPool";
    public static final String h = "lazyPool";
    public static volatile r0 i = null;
    public static final int j = 10;

    /* renamed from: a, reason: collision with root package name */
    public ExecutorService f5445a;
    public ExecutorService b;
    public final Object c = new Object();
    public ConcurrentHashMap<String, Future> d = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Future> e = new ConcurrentHashMap<>();

    public m0 b(String str) {
        Future future;
        m0 m0Var;
        ExecutorService executorService;
        Future putIfAbsent;
        synchronized (this.c) {
            future = this.d.get(str);
            if (future == null && (putIfAbsent = this.d.putIfAbsent(str, (future = (executorService = this.b).submit(new i0(str, executorService))))) != null) {
                future = putIfAbsent;
            }
        }
        try {
            m0Var = (m0) future.get(y.a(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            Logger.w(f, "query failed DNS_TIMEOUT", e);
            m0Var = null;
        }
        this.d.remove(str);
        return m0Var;
    }

    public void a(Runnable runnable) {
        String str;
        try {
            this.f5445a.execute(runnable);
        } catch (RejectedExecutionException unused) {
            str = "the runnable task cannot be accepted for execution";
            Logger.v(f, str);
        } catch (Throwable unused2) {
            str = "the runnable task cannot be accepted for throwable";
            Logger.v(f, str);
        }
    }

    public static class a implements Callable {

        /* renamed from: a, reason: collision with root package name */
        public final String f5446a;
        public final h0 b;

        @Override // java.util.concurrent.Callable
        public List<InetAddress> call() {
            return y.a(this.b.lookup(this.f5446a).d());
        }

        public a(String str, h0 h0Var) {
            this.f5446a = str;
            this.b = h0Var;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.util.List] */
    public List<InetAddress> a(String str, h0 h0Var) throws UnknownHostException {
        Future future;
        ArrayList arrayList = new ArrayList();
        synchronized (this.c) {
            Future future2 = this.e.get(str);
            if (future2 == null) {
                Logger.i(f, "future == null");
                future2 = this.b.submit(new a(str, h0Var));
                future = this.e.putIfAbsent(str, future2);
                if (future != null) {
                    Logger.v(f, "localDnslookup future = " + future);
                }
            }
            future = future2;
            Logger.v(f, "localDnslookup future = " + future);
        }
        try {
            arrayList = (List) future.get(y.a(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            Logger.w(f, "query failed CANCEL_TIMEOUT", e);
        }
        this.e.remove(str);
        if (arrayList != null && !arrayList.isEmpty()) {
            return arrayList;
        }
        Logger.w(f, "dns failed from local, domain is :" + str);
        throw new UnknownHostException("dns failed from local, domain is :" + str);
    }

    public m0 a(String str) throws UnknownHostException {
        m0 b = b(str);
        if (!y.b(b)) {
            return b;
        }
        Logger.w(f, "dns failed from DnsCallable, domain is :" + str);
        throw new UnknownHostException("Broken system behaviour for dns lookup of " + str);
    }

    public static r0 a() {
        if (i == null) {
            synchronized (r0.class) {
                if (i == null) {
                    i = new r0();
                }
            }
        }
        return i;
    }

    public r0() {
        Logger.i(f, "DNS ThreadPool init!");
        this.f5445a = ExecutorsUtils.newFixedThreadPool(10, h);
        this.b = ExecutorsUtils.newCachedThreadPool(g);
    }
}
