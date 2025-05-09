package com.huawei.profile.coordinator.task;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import com.huawei.profile.scheduler.thread.SdkThread;
import com.huawei.profile.utils.ClassUtil;
import com.huawei.profile.utils.SensitiveUtil;
import com.huawei.profile.utils.logger.DsLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes6.dex */
public class ProfileTaskPoolSdk {
    private static final int CAPACITY = 100;
    public static final int DOWNLOAD_TASK = 2;
    private static final String NO_CORRESPONDED_TASK_MSG = "There is no corresponded task, return.";
    public static final int REGISTER_DAILY_TASK = 3;
    private static final String TAG = "ProfileTaskPoolSdk";
    public static final int UPLOAD_TASK = 1;
    public static final int WAIT_FOR_UPLOAD_TASK = 4;
    private static boolean sIsWaitingForUpload = false;
    private static ProfileTaskPoolSdk sPool;
    private volatile boolean hasRegisterNetworkCallback;
    private static final Object LOCK = new Object();
    private static final Object UPLOAD_WAIT_LOCK = new Object();
    private List<PooledTask> pooledTaskList = Collections.synchronizedList(new ArrayList());
    private BlockingQueue<NetworkCapabilities> networkCapabilitiesQueue = new ArrayBlockingQueue(100);

    public static ProfileTaskPoolSdk getInstance() {
        ProfileTaskPoolSdk profileTaskPoolSdk;
        synchronized (LOCK) {
            if (sPool == null) {
                sPool = new ProfileTaskPoolSdk();
            }
            profileTaskPoolSdk = sPool;
        }
        return profileTaskPoolSdk;
    }

    public void uploadLater(Context context) {
        if (getIsWaitingForUpload()) {
            DsLog.dt(TAG, "There is already a task waiting for upload.", new Object[0]);
        } else {
            setIsWaitingForUpload(true);
            execute(context, 4, false, 1);
        }
    }

    public static void setIsWaitingForUpload(boolean z) {
        synchronized (UPLOAD_WAIT_LOCK) {
            sIsWaitingForUpload = z;
            DsLog.dt(TAG, "set waiting for uploading to " + sIsWaitingForUpload, new Object[0]);
        }
    }

    private static boolean getIsWaitingForUpload() {
        boolean z;
        synchronized (UPLOAD_WAIT_LOCK) {
            DsLog.dt(TAG, "waiting for uploading: " + sIsWaitingForUpload, new Object[0]);
            z = sIsWaitingForUpload;
        }
        return z;
    }

    public void execute(Context context, int i, boolean z, int i2) {
        ProfileTask generateTask = generateTask(context, i);
        if (generateTask == null) {
            DsLog.et(TAG, NO_CORRESPONDED_TASK_MSG, new Object[0]);
        } else {
            executeTask(generateTask, z, i2);
        }
    }

    public boolean executeIfNetworkValid(Context context, int i, long j, int i2) {
        if (!isNetworkValid(context)) {
            DsLog.wt(TAG, "network state is invalid, stop to execute upload task", new Object[0]);
            return false;
        }
        ProfileTask generateTask = generateTask(context, i);
        if (generateTask == null) {
            DsLog.et(TAG, NO_CORRESPONDED_TASK_MSG, new Object[0]);
            return false;
        }
        return executeTask(generateTask, j, i2);
    }

    public void executeWithNetwork(Context context, int i, boolean z, Set<Integer> set) {
        if (context == null) {
            DsLog.et(TAG, "context is null when execute with network", new Object[0]);
            return;
        }
        if (set == null) {
            DsLog.et(TAG, "net work types is null", new Object[0]);
            return;
        }
        ProfileTask generateTask = generateTask(context, i);
        if (generateTask == null) {
            DsLog.et(TAG, NO_CORRESPONDED_TASK_MSG, new Object[0]);
        } else {
            executeNetworkTask(context, generateTask, z, set);
        }
    }

    private void executeNetworkTask(Context context, ProfileTask profileTask, boolean z, Set<Integer> set) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ClassUtil.cast(context.getSystemService("connectivity"), ConnectivityManager.class);
        if (connectivityManager == null) {
            DsLog.et(TAG, "connectivityManager is null", new Object[0]);
            return;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities != null && isNetworkStateValid(networkCapabilities, set)) {
            DsLog.dt(TAG, "network state is valid, start to execute task", new Object[0]);
            executeTask(profileTask, z, 2);
            return;
        }
        DsLog.dt(TAG, "Network state is not valid now, register a network-state-change receiver.", new Object[0]);
        if (!hasTask(profileTask.getName())) {
            DsLog.dt(TAG, "add New Task = " + profileTask.getName(), new Object[0]);
            this.pooledTaskList.add(new PooledTask(profileTask, z, set));
        }
        if (this.hasRegisterNetworkCallback) {
            return;
        }
        this.hasRegisterNetworkCallback = true;
        connectivityManager.requestNetwork(new NetworkRequest.Builder().build(), new ConnectivityManager.NetworkCallback() { // from class: com.huawei.profile.coordinator.task.ProfileTaskPoolSdk.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities2) {
                ProfileTaskPoolSdk.this.networkCapabilitiesQueue.offer(networkCapabilities2);
            }
        });
        new SdkThread("onNetworkChanged", new Runnable() { // from class: com.huawei.profile.coordinator.task.ProfileTaskPoolSdk.2
            @Override // java.lang.Runnable
            public void run() {
                ProfileTaskPoolSdk.this.onNetworkChanged();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNetworkChanged() {
        NetworkCapabilities networkCapabilities;
        while (true) {
            try {
                networkCapabilities = getNetworkCapabilities();
            } catch (RuntimeException e) {
                DsLog.et(TAG, "encounter unexpected exception " + SensitiveUtil.getMessage(e), new Object[0]);
            }
            if (networkCapabilities == null) {
                return;
            }
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException unused) {
                DsLog.et(TAG, "waiting for WiFi fully connecting is interrupted.", new Object[0]);
            }
            DsLog.dt(TAG, "pooledTaskList.size() = " + this.pooledTaskList.size(), new Object[0]);
            Iterator<PooledTask> it = this.pooledTaskList.iterator();
            while (it.hasNext() && this.networkCapabilitiesQueue.peek() == null) {
                PooledTask next = it.next();
                if (isNetworkStateValid(networkCapabilities, next.networkTypes)) {
                    DsLog.dt(TAG, "network capabilities changed, could transport by WiFi now.", new Object[0]);
                    it.remove();
                    executeTask(next.task, next.isSync, 2);
                }
            }
        }
    }

    private NetworkCapabilities getNetworkCapabilities() {
        try {
            NetworkCapabilities take = this.networkCapabilitiesQueue.take();
            while (true) {
                NetworkCapabilities networkCapabilities = take;
                if (this.networkCapabilitiesQueue.peek() == null) {
                    return networkCapabilities;
                }
                take = this.networkCapabilitiesQueue.take();
            }
        } catch (InterruptedException unused) {
            return null;
        }
    }

    private boolean hasTask(String str) {
        Iterator<PooledTask> it = this.pooledTaskList.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().task.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isNetworkStateValid(NetworkCapabilities networkCapabilities, Set<Integer> set) {
        if (networkCapabilities.hasTransport(1) && set.contains(1)) {
            return true;
        }
        if (networkCapabilities.hasTransport(0) && set.contains(0)) {
            return true;
        }
        DsLog.et(TAG, "Network state is not valid, network type: " + set.toString(), new Object[0]);
        return false;
    }

    private void executeTask(ProfileTask profileTask, boolean z, int i) {
        Future start = new SdkThread(profileTask.getName(), profileTask, i).start();
        if (!z) {
            DsLog.dt(TAG, " " + profileTask.getName() + " is not a sync task, return now.", new Object[0]);
            return;
        }
        try {
            start.get();
        } catch (InterruptedException | ExecutionException e) {
            DsLog.et(TAG, "Fail to execute task, error: " + e.getMessage(), new Object[0]);
        }
    }

    private boolean executeTask(ProfileTask profileTask, long j, int i) {
        try {
            new SdkThread(profileTask.getName(), profileTask, i).start().get(j, TimeUnit.MILLISECONDS);
            return true;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            DsLog.et(TAG, "Fail to execute task, error: " + e.getMessage(), new Object[0]);
            return false;
        }
    }

    private ProfileTask generateTask(Context context, int i) {
        ProfileTask profileUploadTaskSdk;
        if (i == 1) {
            profileUploadTaskSdk = new ProfileUploadTaskSdk();
        } else if (i == 3) {
            profileUploadTaskSdk = new RegisterDailyUploadTaskSdk();
        } else if (i == 4) {
            profileUploadTaskSdk = new ProfileWaitUploadTaskSdk();
        } else {
            DsLog.et(TAG, "Illegal taskNum.", new Object[0]);
            return null;
        }
        profileUploadTaskSdk.setContext(context);
        return profileUploadTaskSdk;
    }

    private boolean isNetworkValid(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ClassUtil.cast(context.getSystemService("connectivity"), ConnectivityManager.class);
        if (connectivityManager == null) {
            DsLog.et(TAG, "connectivityManager is null", new Object[0]);
            return false;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(0);
        return networkCapabilities != null && isNetworkStateValid(networkCapabilities, hashSet);
    }

    class PooledTask {
        private final boolean isSync;
        private final Set<Integer> networkTypes;
        private final ProfileTask task;

        PooledTask(ProfileTask profileTask, boolean z, Set<Integer> set) {
            this.task = profileTask;
            this.isSync = z;
            this.networkTypes = set;
        }
    }
}
