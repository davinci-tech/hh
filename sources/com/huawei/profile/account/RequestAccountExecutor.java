package com.huawei.profile.account;

import com.huawei.profile.executor.ProfileThreadPoolExecutor;
import com.huawei.profile.utils.logger.DsLog;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes6.dex */
public class RequestAccountExecutor {
    private static final int CAPACITY = 200;
    private static final long KEEP_ALIVE_TIME = 0;
    private static final Object LOCK = new Object();
    private static final int POOL_SIZE = 1;
    private static final String TAG = "RequestAccountExecutor";
    private static RequestAccountExecutor singleton;
    private ExecutorService executorService;
    private final BlockingQueue<Runnable> workQueue;

    private RequestAccountExecutor() {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(200);
        this.workQueue = linkedBlockingQueue;
        this.executorService = new ProfileThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, linkedBlockingQueue, "SingleRequestAccount");
    }

    public static RequestAccountExecutor getInstance() {
        RequestAccountExecutor requestAccountExecutor;
        synchronized (LOCK) {
            if (singleton == null) {
                singleton = new RequestAccountExecutor();
            }
            requestAccountExecutor = singleton;
        }
        return requestAccountExecutor;
    }

    public boolean executeTask(Runnable runnable, long j) {
        Future<?> future;
        if (runnable == null) {
            DsLog.wt(TAG, "params is null, not to request account.", new Object[0]);
            return false;
        }
        try {
            future = this.executorService.submit(runnable);
        } catch (RejectedExecutionException unused) {
            DsLog.dt(TAG, "the queue is full.", new Object[0]);
            future = null;
        }
        if (future == null || j == 0) {
            DsLog.dt(TAG, "not need to wait, return now.", new Object[0]);
            return true;
        }
        try {
            future.get(j, TimeUnit.MILLISECONDS);
            return true;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            DsLog.et(TAG, "Fail to request account, error: " + e.getMessage(), new Object[0]);
            return false;
        }
    }
}
