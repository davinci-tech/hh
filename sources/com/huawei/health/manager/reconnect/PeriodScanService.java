package com.huawei.health.manager.reconnect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class PeriodScanService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            LogUtil.a("PeriodScanService", "onStartCommand. intent is invalid.");
            return 2;
        }
        super.onStartCommand(intent, i, i2);
        int intExtra = intent.getIntExtra("type", 1);
        ReleaseLogUtil.e("DEVMGR_PeriodScanService", "onStartCommand, type: ", Integer.valueOf(intExtra));
        BleReconnectManager.b().d();
        if (intExtra == 1) {
            if (!BleReconnectManager.b().c()) {
                return 3;
            }
            ReleaseLogUtil.e("DEVMGR_PeriodScanService", "onStartCommand. need subscript next reconnect.");
            BleReconnectManager.b().e();
            return 3;
        }
        if (!BleReconnectManager.b().c()) {
            return 3;
        }
        BleReconnectManager.b().a();
        return 3;
    }
}
