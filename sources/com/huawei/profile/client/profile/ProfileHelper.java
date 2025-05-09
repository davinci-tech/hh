package com.huawei.profile.client.profile;

import android.content.Context;
import android.util.Log;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import com.huawei.profile.function.ProfileConsumer;
import com.huawei.profile.function.ProfileFunction;
import com.huawei.profile.profile.ProfileFuture;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes9.dex */
public final class ProfileHelper {
    private static final int CPU_COUNT;
    private static final int DEFAULT_KEEP_ALIVE_TIME = 30;
    private static final ThreadPoolExecutor PROFILE_HELPER_EXECUTOR;
    private static final int RETRY_TIMES = 2;
    private static final String TAG = "ProfileHelper";
    private static final int WAIT_CONNECT_TIMEOUT = 10000;
    private static final int WAIT_RESULT_TIMEOUT = 10000;
    private static Map<Integer, ProfileHelper> profileHelperMap;
    private int connectCount;
    private final Object lock = new Object();
    private ProfileClient profileClient;
    private ExecuteContext serialExecuteContext;

    public interface ExecuteContext {
        void asyncExecute(ProfileConsumer<ProfileClient> profileConsumer);

        void asyncExecuteEx(ProfileConsumer<ProfileOptional<ProfileClient>> profileConsumer);

        <T> T syncExecute(ProfileFunction<ProfileClient, T> profileFunction);

        <T> T syncExecuteEx(ProfileFunction<ProfileOptional<ProfileClient>, T> profileFunction);
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = availableProcessors;
        PROFILE_HELPER_EXECUTOR = new ThreadPoolExecutor(availableProcessors, availableProcessors, 30L, TimeUnit.SECONDS, new LinkedBlockingDeque());
        profileHelperMap = new HashMap();
    }

    private ProfileHelper(Context context, int i) {
        this.profileClient = new ProfileClient(context, i);
    }

    public static ProfileHelper getInstance(Context context) {
        ProfileHelper profileHelper;
        synchronized (ProfileHelper.class) {
            profileHelper = getInstance(context, -2);
        }
        return profileHelper;
    }

    public static ProfileHelper getInstance(Context context, int i) {
        synchronized (ProfileHelper.class) {
            if (profileHelperMap.containsKey(Integer.valueOf(i))) {
                return profileHelperMap.get(Integer.valueOf(i));
            }
            ProfileHelper profileHelper = new ProfileHelper(context, i);
            profileHelperMap.put(Integer.valueOf(i), profileHelper);
            return profileHelper;
        }
    }

    public ExecuteContext serial() {
        ExecuteContext executeContext;
        synchronized (this.lock) {
            if (this.serialExecuteContext == null) {
                this.serialExecuteContext = new SerialExecuteContext();
            }
            executeContext = this.serialExecuteContext;
        }
        return executeContext;
    }

    @Deprecated
    public <T> T syncExecute(ProfileFunction<ProfileClient, T> profileFunction) {
        return (T) syncExecute(profileFunction, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public <T> T syncExecute(final ProfileFunction<ProfileClient, T> profileFunction, ExecutorService executorService) {
        return (T) waitResult(submitTask(executorService, new Callable<T>() { // from class: com.huawei.profile.client.profile.ProfileHelper.1
            @Override // java.util.concurrent.Callable
            public T call() {
                return (T) ProfileHelper.this.executeInner(profileFunction);
            }
        }), 10000, null);
    }

    @Deprecated
    public void asyncExecute(ProfileConsumer<ProfileClient> profileConsumer) {
        asyncExecute(profileConsumer, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void asyncExecute(final ProfileConsumer<ProfileClient> profileConsumer, ExecutorService executorService) {
        executeTask(executorService, new Runnable() { // from class: com.huawei.profile.client.profile.ProfileHelper.2
            @Override // java.lang.Runnable
            public void run() {
                ProfileHelper.this.executeInner((ProfileConsumer<ProfileClient>) profileConsumer);
            }
        });
    }

    private <T> T waitResult(Future<T> future, int i, Runnable runnable) {
        try {
            return future.get(i, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            Log.e(TAG, "wait result timeout");
            if (runnable == null) {
                return null;
            }
            runnable.run();
            return null;
        }
    }

    public boolean connectProfileService() {
        synchronized (this) {
            Log.i(TAG, "connecting profile service");
            if (this.profileClient.hasConnected()) {
                this.connectCount++;
                Log.i(TAG, "reuse existed connection, connectCount = " + this.connectCount);
                return true;
            }
            ProfileFuture profileFuture = new ProfileFuture();
            this.profileClient.connect(new ConnectionCallback(profileFuture));
            Boolean bool = (Boolean) waitResult(profileFuture, 10000, new Runnable() { // from class: com.huawei.profile.client.profile.ProfileHelper.3
                @Override // java.lang.Runnable
                public void run() {
                    ProfileHelper.this.profileClient.disconnect();
                }
            });
            Boolean valueOf = Boolean.valueOf(bool != null ? bool.booleanValue() : false);
            if (valueOf.booleanValue()) {
                this.connectCount++;
                Log.i(TAG, "create new connection, connectCount = " + this.connectCount);
            }
            return valueOf.booleanValue();
        }
    }

    public void disconnectProfileService() {
        synchronized (this) {
            this.connectCount--;
            Log.i(TAG, "disconnect, connectCount = " + this.connectCount);
            if (this.connectCount <= 0) {
                this.profileClient.disconnect();
                this.connectCount = 0;
            }
        }
    }

    public <T> T execute(ProfileFunction<ProfileClient, T> profileFunction) {
        T apply;
        synchronized (this) {
            apply = profileFunction.apply(this.profileClient);
        }
        return apply;
    }

    public boolean hasConnected() {
        return this.profileClient.hasConnected();
    }

    public <T> T syncExecuteEx(ProfileFunction<ProfileOptional<ProfileClient>, T> profileFunction) {
        return (T) syncExecuteEx(profileFunction, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T syncExecuteEx(final ProfileFunction<ProfileOptional<ProfileClient>, T> profileFunction, ExecutorService executorService) {
        return (T) waitResult(submitTask(executorService, new Callable<T>() { // from class: com.huawei.profile.client.profile.ProfileHelper.4
            @Override // java.util.concurrent.Callable
            public T call() {
                return (T) ProfileHelper.this.executeInnerEx(profileFunction);
            }
        }), 10000, null);
    }

    private <T> Future<T> submitTask(ExecutorService executorService, Callable<T> callable) {
        if (executorService != null) {
            return executorService.submit(callable);
        }
        return PROFILE_HELPER_EXECUTOR.submit(callable);
    }

    private void executeTask(ExecutorService executorService, Runnable runnable) {
        if (executorService != null) {
            executorService.execute(runnable);
        } else {
            PROFILE_HELPER_EXECUTOR.execute(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T executeInner(ProfileFunction<ProfileClient, T> profileFunction) {
        if (!isConnected()) {
            return profileFunction.apply(null);
        }
        try {
            return profileFunction.apply(this.profileClient);
        } finally {
            disconnectProfileService();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeInner(final ProfileConsumer<ProfileClient> profileConsumer) {
        executeInner(new ProfileFunction<ProfileClient, Object>() { // from class: com.huawei.profile.client.profile.ProfileHelper.5
            @Override // com.huawei.profile.function.ProfileFunction
            public Object apply(ProfileClient profileClient) {
                profileConsumer.accept(profileClient);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T executeInnerEx(ProfileFunction<ProfileOptional<ProfileClient>, T> profileFunction) {
        if (!isConnected()) {
            return profileFunction.apply(ProfileOptional.of(null));
        }
        try {
            return profileFunction.apply(ProfileOptional.of(this.profileClient));
        } finally {
            disconnectProfileService();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeInnerEx(final ProfileConsumer<ProfileOptional<ProfileClient>> profileConsumer) {
        executeInnerEx(new ProfileFunction<ProfileOptional<ProfileClient>, Object>() { // from class: com.huawei.profile.client.profile.ProfileHelper.6
            @Override // com.huawei.profile.function.ProfileFunction
            public Object apply(ProfileOptional<ProfileClient> profileOptional) {
                profileConsumer.accept(profileOptional);
                return null;
            }
        });
    }

    private boolean isConnected() {
        int i = 0;
        while (i < 2) {
            i++;
            if (connectProfileService()) {
                return true;
            }
        }
        Log.e(TAG, "failed to connect profile service");
        return false;
    }

    public void asyncExecuteEx(ProfileConsumer<ProfileOptional<ProfileClient>> profileConsumer) {
        asyncExecuteEx(profileConsumer, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void asyncExecuteEx(final ProfileConsumer<ProfileOptional<ProfileClient>> profileConsumer, ExecutorService executorService) {
        executeTask(executorService, new Runnable() { // from class: com.huawei.profile.client.profile.ProfileHelper.7
            @Override // java.lang.Runnable
            public void run() {
                ProfileHelper.this.executeInnerEx((ProfileConsumer<ProfileOptional<ProfileClient>>) profileConsumer);
            }
        });
    }

    static class ConnectionCallback implements ServiceConnectCallback {
        private ProfileFuture<Boolean> future;

        ConnectionCallback(ProfileFuture<Boolean> profileFuture) {
            this.future = profileFuture;
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onConnect() {
            Log.i(ProfileHelper.TAG, "profile service is connected");
            this.future.set(true);
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onDisconnect() {
            Log.i(ProfileHelper.TAG, "profile service is disconnected");
            this.future.set(false);
        }
    }

    class SerialExecuteContext implements ExecuteContext {
        private static final int CORE_POOL_SIZE = 1;
        private static final int KEEP_ALIVE_TIME = 1;
        private static final int MAX_QUEUE_SIZE = 100;
        private final RejectedExecutionHandler rejectHandler;
        private final ThreadPoolExecutor singleExecuteService;

        SerialExecuteContext() {
            RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() { // from class: com.huawei.profile.client.profile.ProfileHelper.SerialExecuteContext.1
                @Override // java.util.concurrent.RejectedExecutionHandler
                public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                    Log.e(ProfileHelper.TAG, "Task " + runnable + " rejected from " + threadPoolExecutor);
                }
            };
            this.rejectHandler = rejectedExecutionHandler;
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue(100), new ThreadFactory() { // from class: com.huawei.profile.client.profile.ProfileHelper.SerialExecuteContext.2
                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(Runnable runnable) {
                    return new Thread(runnable, "ProfileClientSerialThread");
                }
            }, rejectedExecutionHandler);
            this.singleExecuteService = threadPoolExecutor;
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        }

        @Override // com.huawei.profile.client.profile.ProfileHelper.ExecuteContext
        @Deprecated
        public void asyncExecute(ProfileConsumer<ProfileClient> profileConsumer) {
            ProfileHelper.this.asyncExecute(profileConsumer, this.singleExecuteService);
        }

        @Override // com.huawei.profile.client.profile.ProfileHelper.ExecuteContext
        @Deprecated
        public <T> T syncExecute(ProfileFunction<ProfileClient, T> profileFunction) {
            return (T) ProfileHelper.this.syncExecute(profileFunction, this.singleExecuteService);
        }

        @Override // com.huawei.profile.client.profile.ProfileHelper.ExecuteContext
        public void asyncExecuteEx(ProfileConsumer<ProfileOptional<ProfileClient>> profileConsumer) {
            ProfileHelper.this.asyncExecuteEx(profileConsumer, this.singleExecuteService);
        }

        @Override // com.huawei.profile.client.profile.ProfileHelper.ExecuteContext
        public <T> T syncExecuteEx(ProfileFunction<ProfileOptional<ProfileClient>, T> profileFunction) {
            return (T) ProfileHelper.this.syncExecuteEx(profileFunction, this.singleExecuteService);
        }
    }
}
