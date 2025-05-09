package com.huawei.devicesdk.reconnect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.bjy;
import defpackage.bkb;
import defpackage.bmh;
import defpackage.bmw;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Map;

/* loaded from: classes3.dex */
public class PeriodScanService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            LogUtil.c("PeriodScanService", "onStartCommand. intent is invalid.");
            return 2;
        }
        super.onStartCommand(intent, i, i2);
        int intExtra = intent.getIntExtra("type", 1);
        ReleaseLogUtil.b("DEVMGR_PeriodScanService", "onStartCommand, type: ", Integer.valueOf(intExtra));
        bkb.e().a();
        if (intExtra == 1) {
            c();
            if (!bkb.e().b()) {
                return 3;
            }
            ReleaseLogUtil.b("DEVMGR_PeriodScanService", "onStartCommand. need subscript next reconnect.");
            bkb.e().c();
            return 3;
        }
        if (!bkb.e().b()) {
            return 3;
        }
        bkb.e().f();
        return 3;
    }

    private void c() {
        for (Map.Entry<String, Boolean> entry : bjy.d().a().entrySet()) {
            if (entry.getValue() != null && entry.getValue().booleanValue()) {
                String a2 = bkb.e().a(entry.getKey());
                LogUtil.c("PeriodScanService", "reportNotScanGattDevice deviceName: ", a2);
                bjy.d().a().put(entry.getKey(), false);
                bmw.e(100094, bmh.b(a2), "", "2");
            }
        }
    }
}
