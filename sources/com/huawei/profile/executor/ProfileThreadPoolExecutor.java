package com.huawei.profile.executor;

import com.huawei.profile.utils.logger.DsLog;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class ProfileThreadPoolExecutor extends ThreadPoolExecutor {
    private static final int DEFAULT_KEEP_ALIVE_TIME = 30;
    private static final int INITIAL_VALUE = 1;
    private static final String TAG = "ProfileThreadPoolExecutor";

    public ProfileThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, String str) {
        super(i, i2, j, timeUnit, blockingQueue, new ProfileThreadFactory(str));
    }

    public ProfileThreadPoolExecutor(int i, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, final String str) {
        super(i, i, j, timeUnit, blockingQueue, new ProfileThreadFactory(str));
        setRejectedExecutionHandler(new RejectedExecutionHandler() { // from class: com.huawei.profile.executor.ProfileThreadPoolExecutor.1
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                DsLog.wt(ProfileThreadPoolExecutor.TAG, str + " is full, discard request", new Object[0]);
            }
        });
        allowCoreThreadTimeOut(true);
    }

    public ProfileThreadPoolExecutor(int i, int i2, String str) {
        this(i, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(i2), str);
    }

    public ProfileThreadPoolExecutor(int i, String str) {
        this(i, i, 30L, TimeUnit.SECONDS, new LinkedBlockingDeque(), str);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        super.execute(new TracedRunnable(runnable));
    }

    static class ProfileThreadFactory implements ThreadFactory {
        private final String baseName;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();

        ProfileThreadFactory(String str) {
            this.baseName = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread newThread = this.defaultThreadFactory.newThread(runnable);
            newThread.setName(this.baseName + "_thread_" + this.threadNumber.getAndIncrement());
            return newThread;
        }
    }
}
