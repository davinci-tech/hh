package com.huawei.hms.stats;

import android.os.Handler;
import android.os.Looper;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import com.huawei.hms.support.log.HMSLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class a {
    private static final a f = new a();

    /* renamed from: a, reason: collision with root package name */
    private final Object f5941a = new Object();
    private boolean b = false;
    private final List<Runnable> c = new ArrayList();
    private final Handler d = new Handler(Looper.getMainLooper());
    private final Runnable e = new RunnableC0152a();

    /* renamed from: com.huawei.hms.stats.a$a, reason: collision with other inner class name */
    class RunnableC0152a implements Runnable {
        RunnableC0152a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.i("AnalyticsCacheManager", "Timeout execCacheBi.");
            if (HiAnalyticsUtils.getInstance().getInitFlag()) {
                a.this.b();
            } else {
                a.this.a();
            }
        }
    }

    private a() {
    }

    public static a c() {
        return f;
    }

    public void a(Runnable runnable) {
        synchronized (this.f5941a) {
            if (runnable == null) {
                return;
            }
            if (this.b) {
                return;
            }
            if (this.c.size() >= 60) {
                return;
            }
            this.c.add(runnable);
            this.d.removeCallbacks(this.e);
            this.d.postDelayed(this.e, PreConnectManager.CONNECT_INTERNAL);
        }
    }

    public void b() {
        synchronized (this.f5941a) {
            HMSLog.i("AnalyticsCacheManager", "execCacheBi: cache size: " + this.c.size());
            this.b = true;
            try {
                Iterator<Runnable> it = this.c.iterator();
                while (it.hasNext()) {
                    it.next().run();
                    it.remove();
                }
            } catch (Throwable th) {
                HMSLog.e("AnalyticsCacheManager", "<execCacheBi> failed. " + th.getMessage());
                a();
            }
            this.b = false;
        }
    }

    public void a() {
        synchronized (this.f5941a) {
            HMSLog.i("AnalyticsCacheManager", "clear AnalyticsCache.");
            this.c.clear();
        }
    }
}
