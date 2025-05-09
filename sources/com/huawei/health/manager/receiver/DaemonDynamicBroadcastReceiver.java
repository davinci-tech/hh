package com.huawei.health.manager.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogicalStepCounter;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class DaemonDynamicBroadcastReceiver extends BroadcastReceiver {
    WeakReference<LogicalStepCounter> b;

    public DaemonDynamicBroadcastReceiver(LogicalStepCounter logicalStepCounter) {
        this.b = new WeakReference<>(logicalStepCounter);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || context == null) {
            LogUtil.b("Step_DynamicReceiver", "onReceive() intent or context null");
            return;
        }
        LogUtil.a("Step_DynamicReceiver", "onReceive action: ", intent.getAction());
        LogicalStepCounter logicalStepCounter = this.b.get();
        if (logicalStepCounter != null) {
            logicalStepCounter.akF_(intent);
        }
    }
}
