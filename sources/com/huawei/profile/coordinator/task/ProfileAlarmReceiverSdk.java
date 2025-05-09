package com.huawei.profile.coordinator.task;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.profile.utils.logger.DsLog;
import java.util.HashSet;

/* loaded from: classes6.dex */
public class ProfileAlarmReceiverSdk extends BroadcastReceiver {
    private static final String TAG = "ProfileAlarmReceiverSdk";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        DsLog.dt(TAG, "triggered.", new Object[0]);
        RegisterDailyUploadTaskSdk.setIsRegistered(false);
        RegisterDailyUploadTaskSdk.setLastReceiveTime(System.currentTimeMillis());
        ProfileTaskPoolSdk profileTaskPoolSdk = ProfileTaskPoolSdk.getInstance();
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        profileTaskPoolSdk.executeWithNetwork(context, 1, false, hashSet);
        DsLog.dt(TAG, "finished.", new Object[0]);
    }
}
