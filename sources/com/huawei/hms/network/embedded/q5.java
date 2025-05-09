package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.ThreadPoolExcutorEnhance;
import com.huawei.hms.framework.common.hianalytics.CrashHianalyticsData;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class q5 {
    public static final String c = "ThreadManager";

    /* renamed from: a, reason: collision with root package name */
    public RejectedExecutionHandler f5433a;
    public ThreadPoolExecutor b;

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public static final q5 f5434a = new q5();
    }

    public void a(Runnable runnable) {
        try {
            this.b.execute(new c(runnable));
        } catch (Throwable th) {
            Logger.e(c, "task exception!", th);
            HianalyticsHelper.getInstance().reportException(th, CrashHianalyticsData.EVENT_ID_CRASH);
        }
    }

    public static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public Runnable f5435a;

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f5435a.run();
            } catch (Throwable th) {
                Logger.e(q5.c, "task exception!", th);
                HianalyticsHelper.getInstance().reportException(th, CrashHianalyticsData.EVENT_ID_CRASH);
            }
        }

        public c(Runnable runnable) {
            this.f5435a = runnable;
        }
    }

    public static q5 a() {
        return b.f5434a;
    }

    public q5() {
        this.f5433a = new ThreadPoolExecutor.DiscardOldestPolicy();
        ThreadPoolExcutorEnhance threadPoolExcutorEnhance = new ThreadPoolExcutorEnhance(1, 1, 300, TimeUnit.SECONDS, new LinkedBlockingQueue(48), ExecutorsUtils.createThreadFactory("NetworkKit_Netdiag"), this.f5433a);
        this.b = threadPoolExcutorEnhance;
        threadPoolExcutorEnhance.allowCoreThreadTimeOut(true);
    }
}
