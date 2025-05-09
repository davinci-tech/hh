package com.huawei.profile.coordinator.task;

import android.content.Context;
import com.huawei.profile.utils.logger.DsLog;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class ProfileWaitUploadTaskSdk extends ProfileTask {
    private static final int DELAY_TIME = 2000;
    private static final String TAG = "ProfileWaitUploadTaskSdk";
    private Context context;

    @Override // com.huawei.profile.coordinator.task.ProfileTask
    public void setContext(Context context) {
        this.context = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            DsLog.dt(TAG, "start to wait", new Object[0]);
            TimeUnit.MILLISECONDS.sleep(2000L);
            HashSet hashSet = new HashSet();
            ProfileTaskPoolSdk profileTaskPoolSdk = ProfileTaskPoolSdk.getInstance();
            hashSet.add(1);
            hashSet.add(0);
            profileTaskPoolSdk.executeWithNetwork(this.context, 1, true, hashSet);
        } catch (InterruptedException unused) {
            DsLog.et(TAG, "sleeping is interrupted.", new Object[0]);
            ProfileTaskPoolSdk.setIsWaitingForUpload(false);
        }
    }

    @Override // com.huawei.profile.coordinator.task.ProfileTask
    public String getName() {
        return TAG;
    }
}
