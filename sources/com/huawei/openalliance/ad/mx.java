package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.App;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes5.dex */
public class mx {

    /* renamed from: a, reason: collision with root package name */
    private static final mx f7280a = new mx();
    private static final ReentrantReadWriteLock b;
    private static final ReentrantReadWriteLock.ReadLock c;
    private static final ReentrantReadWriteLock.WriteLock d;
    private volatile mw e;
    private volatile long f = -1;

    public List<App> a(Context context) {
        ho.b("UninstalledAppCacheManager", "getUninstalledAppFromCache");
        if (context == null || !com.huawei.openalliance.ad.utils.k.a().a(context)) {
            return null;
        }
        ReentrantReadWriteLock.ReadLock readLock = c;
        if (readLock.tryLock()) {
            ho.a("UninstalledAppCacheManager", "get lock");
            try {
                try {
                    if (this.e != null) {
                        c(context);
                        List<App> a2 = this.e.a();
                        readLock.unlock();
                        return a2;
                    }
                    b(context);
                    readLock.unlock();
                } finally {
                    c.unlock();
                }
            } catch (Throwable unused) {
                ho.c("UninstalledAppCacheManager", "get cache failed");
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final Context context) {
        try {
            ho.b("UninstalledAppCacheManager", "asyncUpdateCache");
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.e == null) {
                ho.b("UninstalledAppCacheManager", "cache is null");
                return;
            }
            this.f = fh.b(context).g() * 60 * 1000;
            if (currentTimeMillis - this.e.b() < this.f) {
                ho.b("UninstalledAppCacheManager", "still in query interval");
            } else {
                com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.mx.2
                    @Override // java.lang.Runnable
                    public void run() {
                        mx.this.a(context, currentTimeMillis);
                    }
                });
            }
        } catch (Throwable unused) {
            ho.c("UninstalledAppCacheManager", "sync update occurs exception");
        }
    }

    private void b(final Context context) {
        ho.b("UninstalledAppCacheManager", "getCacheFromSp");
        if (context == null) {
            ho.c("UninstalledAppCacheManager", "context is null");
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.mx.1
            @Override // java.lang.Runnable
            public void run() {
                mx.d.lock();
                try {
                    try {
                        String ci = fh.b(context).ci();
                        if (ci != null) {
                            ho.a("UninstalledAppCacheManager", "convert json to cache");
                            mx.this.e = (mw) com.huawei.openalliance.ad.utils.be.a(ci, mw.class, new Class[0]);
                        }
                        if (mx.this.e == null) {
                            mx.this.e = new mw();
                        }
                        mx.this.c(context);
                    } finally {
                        mx.d.unlock();
                    }
                } catch (Throwable unused) {
                    ho.c("UninstalledAppCacheManager", "get cache from sp failed");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, long j) {
        ho.b("UninstalledAppCacheManager", "try to update cache");
        List<App> b2 = com.huawei.openalliance.ad.utils.k.a().b(context);
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(b2 == null ? 0 : b2.size());
        ho.a("UninstalledAppCacheManager", "get result size:%s", objArr);
        ReentrantReadWriteLock.WriteLock writeLock = d;
        writeLock.lock();
        try {
            try {
                this.e.a(j);
                this.e.a(b2);
                fh.b(context).z(com.huawei.openalliance.ad.utils.be.a(this.e));
                writeLock.unlock();
            } finally {
                d.unlock();
            }
        } catch (Throwable unused) {
            ho.c("UninstalledAppCacheManager", "update cache failed");
        }
    }

    public static mx a() {
        return f7280a;
    }

    static {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        b = reentrantReadWriteLock;
        c = reentrantReadWriteLock.readLock();
        d = reentrantReadWriteLock.writeLock();
    }
}
