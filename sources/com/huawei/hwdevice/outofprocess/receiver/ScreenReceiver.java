package com.huawei.hwdevice.outofprocess.receiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import defpackage.tno;
import health.compact.a.AuthorizationUtils;
import health.compact.a.DaemonServiceSpUtils;
import health.compact.a.DeviceUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes.dex */
public class ScreenReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        if (intent == null) {
            LogUtil.a("DEVMGR_ScrnRecv", "onReceive(): intent is null!");
        } else if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.outofprocess.receiver.ScreenReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    if (DaemonServiceSpUtils.d(context)) {
                        ScreenReceiver.this.d(context);
                        ScreenReceiver.this.b(context);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || defaultAdapter.getState() == 10) {
            LogUtil.a("DEVMGR_ScrnRecv", "switch not on, not need start service!");
            return;
        }
        if (!AuthorizationUtils.a(context)) {
            LogUtil.a("DEVMGR_ScrnRecv", "not authorize, so return");
            return;
        }
        if (DeviceUtil.a() && tno.c()) {
            LogUtil.c("DEVMGR_ScrnRecv", "ScreenReceiver : has device so start PhoneService");
            Intent intent = new Intent();
            intent.setClassName(context, "com.huawei.hwservicesmgr.PhoneService");
            intent.setPackage(context.getPackageName());
            intent.setAction("android.intent.action.USER_PRESENT");
            try {
                context.startService(intent);
                return;
            } catch (Exception e) {
                LogUtil.e("DEVMGR_ScrnRecv", ExceptionUtils.d(e));
                return;
            }
        }
        LogUtil.a("DEVMGR_ScrnRecv", "ScreenReceiver : have no device so do not need to start PhoneService");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        ReleaseLogUtil.b("DEVMGR_ScrnRecv", "ScreenReceiver : start DaemonService");
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.manager.DaemonService");
        intent.setPackage(context.getPackageName());
        intent.setAction("android.intent.action.USER_PRESENT");
        try {
            context.startService(intent);
        } catch (Exception e) {
            ReleaseLogUtil.b("DEVMGR_ScrnRecv", "processStartDaemonService ", e.getMessage());
        }
    }
}
