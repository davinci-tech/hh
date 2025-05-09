package com.huawei.hwsmartinteractmgr.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.jdh;

/* loaded from: classes9.dex */
public class SmartInteractService extends Service {
    private SmartInteractBinder c;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("SMART_SmartInteractService", "onCreate");
        startForeground(20220505, jdh.c().xf_().build());
        this.c = new SmartInteractBinder();
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("SmartInteractService", "onCreate");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.a("SMART_SmartInteractService", "onStartCommand");
        super.onStartCommand(intent, i, i2);
        stopSelf(i2);
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.a("SMART_SmartInteractService", "onBind");
        return this.c;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        LogUtil.a("SMART_SmartInteractService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("SMART_SmartInteractService", "onDestroy");
        jdh.c().a(20220505);
        if (this.c != null) {
            this.c = null;
        }
    }
}
