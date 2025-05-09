package com.huawei.hianalytics.framework.threadpool;

import android.os.HandlerThread;
import android.os.Looper;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class TaskThread {
    public static final int KEEP_ALIVE_TIME = 10000;
    public static final int KEEP_UPDATE_ALIVE_TIME = 60000;
    public static final int MAX_TASK_SIZE = 5000;
    public static final String TAG = "TaskThread";
    public static HandlerThread worker;

    /* renamed from: a, reason: collision with root package name */
    public final ThreadPoolExecutor f3867a;
    public static final TaskThread RECORD_THREAD = new TaskThread(5, "OnEvent", 10000);
    public static final TaskThread REPORT_THREAD = new TaskThread(1, "OnReport", 10000);
    public static final TaskThread UPDATE_THREAD = new TaskThread(1, "Update", 60000);

    public static Looper getWorkLooper() {
        if (worker == null) {
            synchronized (TaskThread.class) {
                if (worker == null) {
                    HandlerThread handlerThread = new HandlerThread("HASDK-OnEvent-31");
                    worker = handlerThread;
                    handlerThread.start();
                }
            }
        }
        HandlerThread handlerThread2 = worker;
        return handlerThread2 != null ? handlerThread2.getLooper() : Looper.getMainLooper();
    }

    public void addToQueue(Runnable runnable) {
        try {
            this.f3867a.execute(new a(runnable));
        } catch (RejectedExecutionException unused) {
            HiLog.w(TAG, "exception has happened from rejected execution");
        }
    }

    public static TaskThread getUpdateThread() {
        return UPDATE_THREAD;
    }

    public static class b implements ThreadFactory {
        public static final AtomicInteger d = new AtomicInteger(1);

        /* renamed from: a, reason: collision with root package name */
        public final ThreadGroup f3869a;
        public final AtomicInteger b = new AtomicInteger(1);
        public final String c;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(this.f3869a, runnable, this.c + this.b.getAndIncrement(), 0L);
        }

        public b(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            this.f3869a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.c = "HASDK-" + str + Constants.LINK + d.getAndIncrement();
        }
    }

    public static TaskThread getReportThread() {
        return REPORT_THREAD;
    }

    public static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final Runnable f3868a;

        @Override // java.lang.Runnable
        public void run() {
            Runnable runnable = this.f3868a;
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    HiLog.w(TaskThread.TAG, "error occurred: " + th.getMessage() + ";" + th.getCause());
                }
            }
        }

        public a(Runnable runnable) {
            this.f3868a = runnable;
        }
    }

    public static TaskThread getRecordThread() {
        return RECORD_THREAD;
    }

    public TaskThread(int i, String str, int i2) {
        this.f3867a = new ThreadPoolExecutor(0, i, i2, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(5000), new b(str));
    }
}
