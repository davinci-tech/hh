package com.huawei.haf.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class DebugSwitchReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.c("HAF_SwitchReceiver", "onReceive ", intent);
        LocalBroadcastManager.getInstance(BaseApplication.e()).sendBroadcast(intent);
    }
}
