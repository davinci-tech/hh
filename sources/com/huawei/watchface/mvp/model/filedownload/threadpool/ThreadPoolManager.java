package com.huawei.watchface.mvp.model.filedownload.threadpool;

import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.utils.HwLog;
import java.lang.Thread;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes7.dex */
public final class ThreadPoolManager {
    private static final int MAX_CORE_THREAD_NUM = 5;
    private static final int MAX_POOL_THREAD_NUM = 10;
    private static final int MAX_UI_CORE_THREAD_NUM = 10;
    private static final int MAX_UI_POOL_THREAD_NUM = 30;
    private static final String TAG = "ThreadPoolManager";
    private static volatile ThreadPoolManager sInstance;
    private WatchFaceThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager(int i, int i2) {
        this.mThreadPoolExecutor = new WatchFaceThreadPoolExecutor(i, i2);
    }

    private ThreadPoolManager(int i, int i2, long j) {
        this.mThreadPoolExecutor = new WatchFaceThreadPoolExecutor(i, i2, j);
    }

    public static ThreadPoolManager getInstance() {
        if (sInstance == null) {
            synchronized (ThreadPoolManager.class) {
                if (sInstance == null) {
                    sInstance = make(10, 30);
                }
            }
        }
        return sInstance;
    }

    public static ThreadPoolManager make(int i, int i2) {
        return new ThreadPoolManager(i, i2);
    }

    public static ThreadPoolManager make(int i, int i2, long j) {
        return new ThreadPoolManager(i, i2, j);
    }

    public void execute(Runnable runnable) {
        if (runnable instanceof WatchFaceRunnable) {
            this.mThreadPoolExecutor.execute(runnable);
        } else if (runnable != null) {
            this.mThreadPoolExecutor.execute(new WatchFaceRunnable(runnable));
        }
    }

    public boolean remove(Runnable runnable) {
        if (runnable instanceof WatchFaceRunnable) {
            return this.mThreadPoolExecutor.remove(runnable);
        }
        if (runnable != null) {
            return this.mThreadPoolExecutor.remove(new WatchFaceRunnable(runnable));
        }
        return false;
    }

    public void tagExecute(String str, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (str == null) {
            throw new IllegalArgumentException("tag is null");
        }
        execute(new WatchFaceTagRunnable(str, runnable));
    }

    public int removeTagTask(String str, Runnable runnable) {
        if (str == null) {
            throw new IllegalArgumentException("tag is null");
        }
        if (runnable != null) {
            return this.mThreadPoolExecutor.remove(new WatchFaceTagRunnable(str, runnable)) ? 1 : 0;
        }
        return this.mThreadPoolExecutor.removeTagTask(str);
    }

    public void serialExecute(String str, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (str == null) {
            throw new IllegalArgumentException("serialTag is null");
        }
        execute(new WatchFaceSerialRunnable(str, runnable));
    }

    public int removeSerialTask(String str, Runnable runnable) {
        if (str == null) {
            throw new IllegalArgumentException("serialTag is null");
        }
        if (runnable != null) {
            return this.mThreadPoolExecutor.remove(new WatchFaceSerialRunnable(str, runnable)) ? 1 : 0;
        }
        return this.mThreadPoolExecutor.removeSerialTask(str);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return this.mThreadPoolExecutor.submit(callable);
    }

    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.mThreadPoolExecutor.submit(runnable, t);
    }

    public Future<?> submit(Runnable runnable) {
        return this.mThreadPoolExecutor.submit(runnable);
    }

    public void shutdown() {
        if (this == getInstance()) {
            HwLog.w(TAG, "shutdown");
            throw new UnsupportedOperationException("Unsupported shutdown default thread pool");
        }
        this.mThreadPoolExecutor.shutdown();
    }

    public List<Runnable> shutdownNow() {
        if (this == getInstance()) {
            HwLog.w(TAG, "shutdownNow");
            throw new UnsupportedOperationException("Unsupported shutdownNow default thread pool");
        }
        List<Runnable> shutdownNow = this.mThreadPoolExecutor.shutdownNow();
        for (int i = 0; i < shutdownNow.size(); i++) {
            Runnable runnable = shutdownNow.get(i);
            if (runnable instanceof WatchFaceRunnable) {
                shutdownNow.set(i, ((WatchFaceRunnable) runnable).getRunnable());
            }
        }
        return shutdownNow;
    }

    static class WatchFaceRunnable implements Runnable {
        private final Runnable mRunnable;

        WatchFaceRunnable(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("runnable == null");
            }
            this.mRunnable = runnable;
        }

        Runnable getRunnable() {
            return this.mRunnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mRunnable.run();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof WatchFaceRunnable) {
                return this.mRunnable.equals(((WatchFaceRunnable) obj).mRunnable);
            }
            return false;
        }

        public int hashCode() {
            return this.mRunnable.hashCode() + super.hashCode();
        }

        public String toString() {
            if (this.mRunnable.getClass().isAnonymousClass()) {
                String obj = this.mRunnable.toString();
                return obj.lastIndexOf(".") > 0 ? SafeString.substring(obj, obj.lastIndexOf(".") + 1) : obj;
            }
            return this.mRunnable.toString();
        }
    }

    static class WatchFaceTagRunnable extends WatchFaceRunnable {
        private static final Runnable MATCH_RUNNABLE = new Runnable() { // from class: com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager.WatchFaceTagRunnable.1
            @Override // java.lang.Runnable
            public void run() {
            }
        };
        private final String mTag;

        WatchFaceTagRunnable(String str, Runnable runnable) {
            super(runnable);
            if (str == null) {
                throw new IllegalArgumentException("tag == null");
            }
            this.mTag = str;
        }

        WatchFaceTagRunnable(String str) {
            super(MATCH_RUNNABLE);
            if (str == null) {
                throw new IllegalArgumentException("tag == null");
            }
            this.mTag = str;
        }

        @Override // com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager.WatchFaceRunnable
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WatchFaceTagRunnable)) {
                return false;
            }
            WatchFaceTagRunnable watchFaceTagRunnable = (WatchFaceTagRunnable) obj;
            if (!this.mTag.equals(watchFaceTagRunnable.mTag)) {
                return false;
            }
            Runnable runnable = getRunnable();
            Runnable runnable2 = MATCH_RUNNABLE;
            if (runnable == runnable2 || watchFaceTagRunnable.getRunnable() == runnable2) {
                return true;
            }
            return super.equals(obj);
        }

        @Override // com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager.WatchFaceRunnable
        public int hashCode() {
            return this.mTag.hashCode() + super.hashCode();
        }

        @Override // com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager.WatchFaceRunnable
        public String toString() {
            return "tag=(" + this.mTag + "), " + super.toString();
        }
    }

    static class WatchFaceSerialRunnable extends WatchFaceRunnable {
        private WatchFaceSerialRunnable mNextTask;
        private final String mSerialTag;

        WatchFaceSerialRunnable(String str, Runnable runnable) {
            super(runnable);
            if (str == null) {
                throw new IllegalArgumentException("serialTag == null");
            }
            this.mSerialTag = str;
        }

        @Override // com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager.WatchFaceRunnable
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof WatchFaceSerialRunnable) && this.mSerialTag.equals(((WatchFaceSerialRunnable) obj).mSerialTag)) {
                return super.equals(obj);
            }
            return false;
        }

        @Override // com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager.WatchFaceRunnable
        public int hashCode() {
            return this.mSerialTag.hashCode() + super.hashCode();
        }

        @Override // com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager.WatchFaceRunnable
        public String toString() {
            return "serialTag=(" + this.mSerialTag + "), " + super.toString();
        }
    }

    public static final class WatchFaceThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup mGroup;
        private final String mNamePrefix;
        private final AtomicInteger mThreadNumber = new AtomicInteger(1);

        public WatchFaceThreadFactory() {
            SecurityManager securityManager = System.getSecurityManager();
            this.mGroup = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.mNamePrefix = "health-" + POOL_NUMBER.getAndIncrement() + "-th-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.mGroup, runnable, this.mNamePrefix + this.mThreadNumber.getAndIncrement(), 0L);
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager$WatchFaceThreadFactory$$ExternalSyntheticLambda0
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public final void uncaughtException(Thread thread2, Throwable th) {
                    HwLog.w(ThreadPoolManager.TAG, "task rejected:" + thread2.getName() + ", e:" + HwLog.printException(th));
                }
            });
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }

        public String toString() {
            return this.mNamePrefix + this.mGroup.getName();
        }
    }

    static class HealthWrapExecutionHandler implements RejectedExecutionHandler {
        private volatile boolean mIsExecuteFullFlag;
        private final BlockingQueue<WatchFaceRunnable> mQueue;

        private HealthWrapExecutionHandler() {
            this.mQueue = new LinkedBlockingQueue();
            this.mIsExecuteFullFlag = true;
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            this.mIsExecuteFullFlag = true;
        }

        void wrapExecute(Runnable runnable, WatchFaceThreadPoolExecutor watchFaceThreadPoolExecutor) {
            if ((runnable == null && this.mQueue.isEmpty()) || watchFaceThreadPoolExecutor.isShutdown()) {
                return;
            }
            if (runnable != null && !this.mQueue.offer(wrapRunnable(runnable))) {
                HwLog.w(ThreadPoolManager.TAG, "task rejected:" + runnable.toString());
            }
            if (this.mIsExecuteFullFlag) {
                wrapExecute(watchFaceThreadPoolExecutor);
            }
        }

        private void wrapExecute(WatchFaceThreadPoolExecutor watchFaceThreadPoolExecutor) {
            synchronized (this) {
                try {
                    this.mIsExecuteFullFlag = false;
                    WatchFaceRunnable peek = this.mQueue.peek();
                    while (peek != null) {
                        watchFaceThreadPoolExecutor.swapExecute(peek);
                        if (this.mIsExecuteFullFlag) {
                            break;
                        }
                        this.mQueue.poll();
                        peek = this.mQueue.peek();
                    }
                } finally {
                    this.mIsExecuteFullFlag = true;
                }
            }
        }

        boolean wrapRemove(Runnable runnable, WatchFaceThreadPoolExecutor watchFaceThreadPoolExecutor) {
            boolean z;
            synchronized (this) {
                WatchFaceRunnable wrapRunnable = wrapRunnable(runnable);
                if (!watchFaceThreadPoolExecutor.swapRemove(wrapRunnable)) {
                    z = this.mQueue.remove(wrapRunnable);
                }
            }
            return z;
        }

        int wrapRemoveTagTask(String str, WatchFaceThreadPoolExecutor watchFaceThreadPoolExecutor) {
            int i;
            synchronized (this) {
                WatchFaceTagRunnable watchFaceTagRunnable = new WatchFaceTagRunnable(str);
                BlockingQueue<Runnable> queue = watchFaceThreadPoolExecutor.getQueue();
                i = 0;
                while (queue.remove(watchFaceTagRunnable)) {
                    i++;
                }
                while (this.mQueue.remove(watchFaceTagRunnable)) {
                    i++;
                }
            }
            return i;
        }

        int wrapQueueSize(WatchFaceThreadPoolExecutor watchFaceThreadPoolExecutor) {
            int size;
            int size2;
            synchronized (this) {
                size = watchFaceThreadPoolExecutor.getQueue().size();
                size2 = this.mQueue.size();
            }
            return size + size2;
        }

        void wrapShutdown(WatchFaceThreadPoolExecutor watchFaceThreadPoolExecutor) {
            synchronized (this) {
                watchFaceThreadPoolExecutor.swapShutdown();
                this.mQueue.clear();
            }
        }

        List<Runnable> wrapShutdownNow(WatchFaceThreadPoolExecutor watchFaceThreadPoolExecutor) {
            List<Runnable> swapShutdownNow;
            synchronized (this) {
                swapShutdownNow = watchFaceThreadPoolExecutor.swapShutdownNow();
                swapShutdownNow.addAll(this.mQueue);
                this.mQueue.clear();
            }
            return swapShutdownNow;
        }

        private WatchFaceRunnable wrapRunnable(Runnable runnable) {
            if (runnable instanceof WatchFaceRunnable) {
                return (WatchFaceRunnable) runnable;
            }
            return new WatchFaceRunnable(runnable);
        }
    }

    static final class WatchFaceSerialExecutionHandler {
        private final WatchFaceThreadPoolExecutor mExecutor;
        private final HashMap<String, WatchFaceSerialRunnable> mSerialTaskMap = new HashMap<>();
        private final AtomicInteger mWaitRunSerialTaskNum = new AtomicInteger(0);
        private final ThreadLocal<Boolean> mIsInSerialExecute = new ThreadLocal<>();

        WatchFaceSerialExecutionHandler(WatchFaceThreadPoolExecutor watchFaceThreadPoolExecutor) {
            this.mExecutor = watchFaceThreadPoolExecutor;
        }

        int serialQueueSize() {
            return this.mWaitRunSerialTaskNum.intValue();
        }

        boolean putSerialTask(WatchFaceSerialRunnable watchFaceSerialRunnable) {
            synchronized (this.mSerialTaskMap) {
                WatchFaceSerialRunnable watchFaceSerialRunnable2 = this.mSerialTaskMap.get(watchFaceSerialRunnable.mSerialTag);
                if (watchFaceSerialRunnable2 == null) {
                    this.mSerialTaskMap.put(watchFaceSerialRunnable.mSerialTag, watchFaceSerialRunnable);
                    return false;
                }
                while (watchFaceSerialRunnable2.mNextTask != null) {
                    watchFaceSerialRunnable2 = watchFaceSerialRunnable2.mNextTask;
                }
                watchFaceSerialRunnable2.mNextTask = watchFaceSerialRunnable;
                this.mWaitRunSerialTaskNum.getAndIncrement();
                return true;
            }
        }

        void trySerialExecute(Runnable runnable, Throwable th, HealthWrapExecutionHandler healthWrapExecutionHandler) {
            if (runnable instanceof WatchFaceSerialRunnable) {
                if (th != null) {
                    WatchFaceSerialRunnable nextSerialTaskSafe = getNextSerialTaskSafe(((WatchFaceSerialRunnable) runnable).mSerialTag);
                    if (nextSerialTaskSafe != null) {
                        healthWrapExecutionHandler.wrapExecute(nextSerialTaskSafe, this.mExecutor);
                        return;
                    }
                    return;
                }
                Boolean bool = this.mIsInSerialExecute.get();
                if (bool == null || !bool.booleanValue()) {
                    this.mIsInSerialExecute.set(Boolean.TRUE);
                    try {
                        serialExecute((WatchFaceSerialRunnable) runnable);
                    } finally {
                        this.mIsInSerialExecute.set(Boolean.FALSE);
                    }
                }
            }
        }

        boolean removeSerialTask(WatchFaceSerialRunnable watchFaceSerialRunnable, HealthWrapExecutionHandler healthWrapExecutionHandler) {
            WatchFaceSerialRunnable nextSerialTask;
            synchronized (this.mSerialTaskMap) {
                boolean wrapRemove = healthWrapExecutionHandler.wrapRemove(watchFaceSerialRunnable, this.mExecutor);
                WatchFaceSerialRunnable watchFaceSerialRunnable2 = this.mSerialTaskMap.get(watchFaceSerialRunnable.mSerialTag);
                if (watchFaceSerialRunnable2 == null) {
                    return wrapRemove;
                }
                if (!wrapRemove) {
                    while (watchFaceSerialRunnable2.mNextTask != null) {
                        if (watchFaceSerialRunnable2.mNextTask.equals(watchFaceSerialRunnable)) {
                            watchFaceSerialRunnable2.mNextTask = watchFaceSerialRunnable2.mNextTask.mNextTask;
                            this.mWaitRunSerialTaskNum.getAndDecrement();
                            return true;
                        }
                        watchFaceSerialRunnable2 = watchFaceSerialRunnable2.mNextTask;
                    }
                    return false;
                }
                if (watchFaceSerialRunnable2.equals(watchFaceSerialRunnable) && (nextSerialTask = getNextSerialTask(watchFaceSerialRunnable2.mSerialTag)) != null) {
                    healthWrapExecutionHandler.wrapExecute(nextSerialTask, this.mExecutor);
                }
                return true;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        int removeSerialTask(String str, HealthWrapExecutionHandler healthWrapExecutionHandler) {
            synchronized (this.mSerialTaskMap) {
                WatchFaceSerialRunnable watchFaceSerialRunnable = this.mSerialTaskMap.get(str);
                if (watchFaceSerialRunnable == null) {
                    return 0;
                }
                boolean wrapRemove = healthWrapExecutionHandler.wrapRemove(watchFaceSerialRunnable, this.mExecutor);
                WatchFaceSerialRunnable nextSerialTask = getNextSerialTask(str);
                int i = wrapRemove;
                while (nextSerialTask != null) {
                    nextSerialTask = getNextSerialTask(str);
                    i++;
                }
                return i;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeAllSerialTask(List<Runnable> list) {
            synchronized (this.mSerialTaskMap) {
                if (this.mSerialTaskMap.isEmpty()) {
                    return;
                }
                for (String str : (String[]) this.mSerialTaskMap.keySet().toArray(new String[this.mSerialTaskMap.size()])) {
                    WatchFaceSerialRunnable nextSerialTask = getNextSerialTask(str);
                    while (nextSerialTask != null) {
                        if (list != null) {
                            list.add(nextSerialTask);
                        }
                        nextSerialTask = getNextSerialTask(str);
                    }
                }
            }
        }

        private WatchFaceSerialRunnable getNextSerialTask(String str) {
            WatchFaceSerialRunnable remove = this.mSerialTaskMap.remove(str);
            if (remove == null) {
                return null;
            }
            WatchFaceSerialRunnable watchFaceSerialRunnable = remove.mNextTask;
            if (watchFaceSerialRunnable == null) {
                return watchFaceSerialRunnable;
            }
            this.mSerialTaskMap.put(watchFaceSerialRunnable.mSerialTag, watchFaceSerialRunnable);
            this.mWaitRunSerialTaskNum.getAndDecrement();
            return watchFaceSerialRunnable;
        }

        private WatchFaceSerialRunnable getNextSerialTaskSafe(String str) {
            WatchFaceSerialRunnable nextSerialTask;
            synchronized (this.mSerialTaskMap) {
                nextSerialTask = getNextSerialTask(str);
            }
            return nextSerialTask;
        }

        private void serialExecute(WatchFaceSerialRunnable watchFaceSerialRunnable) {
            WatchFaceSerialRunnable nextSerialTaskSafe = getNextSerialTaskSafe(watchFaceSerialRunnable.mSerialTag);
            while (nextSerialTaskSafe != null) {
                runSerialTask(Thread.currentThread(), nextSerialTaskSafe);
                nextSerialTaskSafe = getNextSerialTaskSafe(nextSerialTaskSafe.mSerialTag);
            }
        }

        private void runSerialTask(Thread thread, Runnable runnable) {
            this.mExecutor.beforeExecute(thread, runnable);
            try {
                runnable.run();
                this.mExecutor.afterExecute(runnable, null);
            } catch (Throwable th) {
                this.mExecutor.afterExecute(runnable, th);
            }
        }
    }

    static final class WatchFaceThreadPoolExecutor extends ThreadPoolExecutor {
        private static final long ALL_TASK_RUN_LOG_PRINT_TIME_INTERVAL = 300000;
        private static final int MIN_BLOCKING_QUEUE_LEN = 1;
        private static final int SHIFT_OPERATOR_ONE = 1;
        private static final long TASK_EXECUTED_ABOVE_10 = 10000;
        private static final long TASK_EXECUTED_ABOVE_20 = 20000;
        private static final long TASK_EXECUTED_ABOVE_30 = 30000;
        private static final long THREAD_KEEP_ALIVE_TIME = 20;
        private final AtomicLong mCompletedTaskCostTimes;
        private final AtomicLong mCompletedTaskCount;
        private final AtomicLong mLastLogPrintTime;
        private final WatchFaceSerialExecutionHandler mSerialExecutionHandler;
        private final ThreadLocal<Long> mStartTime;
        private final HealthWrapExecutionHandler mSwapExecutionHandler;
        private final AtomicLong mTaskMaxCostTime;
        private final AtomicInteger mTaskRunNum;

        private static int calcQueueSize(int i) {
            return (i << 1) + 1;
        }

        WatchFaceThreadPoolExecutor(int i, int i2) {
            this(i, i2, 20L);
        }

        WatchFaceThreadPoolExecutor(int i, int i2, long j) {
            this(i, i2, j, calcQueueSize(i), new HealthWrapExecutionHandler());
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private WatchFaceThreadPoolExecutor(int r12, int r13, long r14, int r16, com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager.HealthWrapExecutionHandler r17) {
            /*
                r11 = this;
                r9 = r11
                java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS
                r10 = 1
                r0 = r16
                if (r0 >= r10) goto L9
                r0 = r10
            L9:
                java.util.concurrent.ArrayBlockingQueue r6 = new java.util.concurrent.ArrayBlockingQueue
                r6.<init>(r0)
                com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager$WatchFaceThreadFactory r7 = new com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager$WatchFaceThreadFactory
                r7.<init>()
                r0 = r11
                r1 = r12
                r2 = r13
                r3 = r14
                r8 = r17
                r0.<init>(r1, r2, r3, r5, r6, r7, r8)
                java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
                r1 = 0
                r0.<init>(r1)
                r9.mCompletedTaskCount = r0
                java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
                r0.<init>(r1)
                r9.mCompletedTaskCostTimes = r0
                java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
                r0.<init>(r1)
                r9.mTaskMaxCostTime = r0
                java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
                r0.<init>(r1)
                r9.mLastLogPrintTime = r0
                java.util.concurrent.atomic.AtomicInteger r0 = new java.util.concurrent.atomic.AtomicInteger
                r1 = 0
                r0.<init>(r1)
                r9.mTaskRunNum = r0
                java.lang.ThreadLocal r0 = new java.lang.ThreadLocal
                r0.<init>()
                r9.mStartTime = r0
                r0 = r17
                r9.mSwapExecutionHandler = r0
                com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager$WatchFaceSerialExecutionHandler r0 = new com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager$WatchFaceSerialExecutionHandler
                r0.<init>(r11)
                r9.mSerialExecutionHandler = r0
                r11.allowCoreThreadTimeOut(r10)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager.WatchFaceThreadPoolExecutor.<init>(int, int, long, int, com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager$HealthWrapExecutionHandler):void");
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
            throw new UnsupportedOperationException("Unsupported setRejectedExecutionHandler");
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public long getTaskCount() {
            return getTaskRunNum() + getTaskWaitRunNum();
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public long getCompletedTaskCount() {
            return this.mCompletedTaskCount.get();
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if ((runnable instanceof WatchFaceSerialRunnable) && !isShutdown() && this.mSerialExecutionHandler.putSerialTask((WatchFaceSerialRunnable) runnable)) {
                return;
            }
            this.mSwapExecutionHandler.wrapExecute(runnable, this);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public boolean remove(Runnable runnable) {
            if (runnable instanceof WatchFaceSerialRunnable) {
                return this.mSerialExecutionHandler.removeSerialTask((WatchFaceSerialRunnable) runnable, this.mSwapExecutionHandler);
            }
            return this.mSwapExecutionHandler.wrapRemove(runnable, this);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
        public void shutdown() {
            this.mSwapExecutionHandler.wrapShutdown(this);
            this.mSerialExecutionHandler.removeAllSerialTask(null);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
        public List<Runnable> shutdownNow() {
            List<Runnable> wrapShutdownNow = this.mSwapExecutionHandler.wrapShutdownNow(this);
            this.mSerialExecutionHandler.removeAllSerialTask(wrapShutdownNow);
            return wrapShutdownNow;
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        protected void beforeExecute(Thread thread, Runnable runnable) {
            this.mSwapExecutionHandler.wrapExecute(null, this);
            this.mTaskRunNum.getAndIncrement();
            this.mStartTime.set(Long.valueOf(System.currentTimeMillis()));
            super.beforeExecute(thread, runnable);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        protected void afterExecute(Runnable runnable, Throwable th) {
            super.afterExecute(runnable, th);
            afterExecuteStatistical(runnable, th);
            this.mTaskRunNum.getAndDecrement();
            this.mSerialExecutionHandler.trySerialExecute(runnable, th, this.mSwapExecutionHandler);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        protected void terminated() {
            super.terminated();
            HwLog.i(ThreadPoolManager.TAG, "terminated() thread pool terminated, " + getThreadFactory().toString());
        }

        int removeTagTask(String str) {
            return this.mSwapExecutionHandler.wrapRemoveTagTask(str, this);
        }

        int removeSerialTask(String str) {
            return this.mSerialExecutionHandler.removeSerialTask(str, this.mSwapExecutionHandler);
        }

        void swapExecute(WatchFaceRunnable watchFaceRunnable) {
            super.execute(watchFaceRunnable);
        }

        boolean swapRemove(WatchFaceRunnable watchFaceRunnable) {
            return super.remove(watchFaceRunnable);
        }

        void swapShutdown() {
            super.shutdown();
        }

        List<Runnable> swapShutdownNow() {
            return super.shutdownNow();
        }

        private int getTaskRunNum() {
            return this.mTaskRunNum.intValue();
        }

        private int getTaskWaitRunNum() {
            return this.mSwapExecutionHandler.wrapQueueSize(this) + this.mSerialExecutionHandler.serialQueueSize();
        }

        private void afterExecuteStatistical(Runnable runnable, Throwable th) {
            long currentTimeMillis = System.currentTimeMillis();
            long longValue = currentTimeMillis - this.mStartTime.get().longValue();
            long addAndGet = this.mCompletedTaskCostTimes.addAndGet(longValue);
            long incrementAndGet = this.mCompletedTaskCount.incrementAndGet();
            if (longValue > this.mTaskMaxCostTime.get()) {
                this.mTaskMaxCostTime.set(longValue);
            }
            int maximumPoolSize = getMaximumPoolSize();
            if (incrementAndGet != 0 && maximumPoolSize != 0 && incrementAndGet % maximumPoolSize == 0 && currentTimeMillis - this.mLastLogPrintTime.get() > 300000) {
                this.mLastLogPrintTime.set(currentTimeMillis);
                HwLog.i(ThreadPoolManager.TAG, "taskCmpNum=" + incrementAndGet + ", avgRunTime=" + (addAndGet / incrementAndGet) + ", maxRunTime=" + this.mTaskMaxCostTime.get() + ", activeCount=" + getActiveCount() + ", taskRunNum=" + getTaskRunNum() + ", taskWaitNum=" + getTaskWaitRunNum());
            }
            if (longValue >= 30000) {
                HwLog.w(ThreadPoolManager.TAG, getThreadFactory().toString() + ", " + runnable.toString() + ", runTime=" + longValue);
            } else if (longValue >= TASK_EXECUTED_ABOVE_20) {
                HwLog.i(ThreadPoolManager.TAG, getThreadFactory().toString() + ", " + runnable.toString() + ", runTime=" + longValue);
            } else if (longValue >= 10000) {
                HwLog.d(ThreadPoolManager.TAG, getThreadFactory().toString() + ", " + runnable.toString() + ", runTime=" + longValue);
            }
            if (th != null) {
                HwLog.w(ThreadPoolManager.TAG, "afterExecuteStatistical: " + runnable.toString() + ", throwable=" + HwLog.printException(th));
            }
        }
    }
}
