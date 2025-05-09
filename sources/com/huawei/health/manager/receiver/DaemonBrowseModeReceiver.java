package com.huawei.health.manager.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.icommon.ISimpleResultCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.LogicalStepCounter;
import health.compact.a.ReleaseLogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class DaemonBrowseModeReceiver extends BroadcastReceiver {
    private long b = 0;
    WeakReference<LogicalStepCounter> d;

    public DaemonBrowseModeReceiver(LogicalStepCounter logicalStepCounter) {
        this.d = new WeakReference<>(logicalStepCounter);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || context == null) {
            LogUtil.b("Step_BrowseModeRcv", "onReceive() intent or context null");
            return;
        }
        String action = intent.getAction();
        LogicalStepCounter logicalStepCounter = this.d.get();
        if (logicalStepCounter == null) {
            LogUtil.a("Step_BrowseModeRcv", "stepCounter == null");
            return;
        }
        if ("com.huawei.plugin.account.login".equals(action)) {
            logicalStepCounter.e(false);
            return;
        }
        if ("com.huawei.plugin.account.logout".equals(action)) {
            logicalStepCounter.e(true);
            return;
        }
        if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
            long b = LogUtil.b(300000, this.b);
            if (b != -1) {
                try {
                    DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                    if (deviceInfo == null) {
                        LogUtil.h("Step_BrowseModeRcv", "onReceive deviceInfo == null ");
                        return;
                    }
                    int deviceConnectState = deviceInfo.getDeviceConnectState();
                    ReleaseLogUtil.b("Step_BrowseModeRcv", "onReceive ACTION_CONNECTION_STATE_CHANGED connectState ", Integer.valueOf(deviceConnectState));
                    if (deviceConnectState == 2) {
                        logicalStepCounter.b((ISimpleResultCallback) null);
                        this.b = b;
                    }
                } catch (RuntimeException e) {
                    LogUtil.b("Step_BrowseModeRcv", "onReceive exception:", LogAnonymous.b((Throwable) e));
                }
            }
        }
    }
}
