package com.huawei.profile.scheduler.thread;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class SdkThread {
    private static final int DEFAULT_POOL_SIZE = 10;
    public static final int DEFAULT_THREAD = 0;
    private static final ThreadPoolExecutor EXECUTOR_SERVICE;
    private static final int KEEP_ALIVE_TEN_SECONDS = 30;
    private static final ThreadPoolExecutor UPLOAD_EXECUTOR_SERVICE;
    public static final int UPLOAD_THREAD = 2;
    private static final ThreadPoolExecutor WAIT_EXECUTOR_SERVICE;
    public static final int WAIT_FOR_UPLOAD_THREAD = 1;
    private final SdkRunnable runnable;
    private int threadType;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        EXECUTOR_SERVICE = threadPoolExecutor;
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(10, 10, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        UPLOAD_EXECUTOR_SERVICE = threadPoolExecutor2;
        ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(10, 10, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        WAIT_EXECUTOR_SERVICE = threadPoolExecutor3;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor2.allowCoreThreadTimeOut(true);
        threadPoolExecutor3.allowCoreThreadTimeOut(true);
    }

    public SdkThread(String str, Runnable runnable) {
        this(str, runnable, 0);
    }

    public SdkThread(String str, Runnable runnable, int i) {
        this.runnable = new SdkRunnable(str, runnable);
        this.threadType = i;
    }

    public Future start() {
        int i = this.threadType;
        if (i == 1) {
            return WAIT_EXECUTOR_SERVICE.submit(this.runnable);
        }
        if (i == 2) {
            return UPLOAD_EXECUTOR_SERVICE.submit(this.runnable);
        }
        return EXECUTOR_SERVICE.submit(this.runnable);
    }
}
