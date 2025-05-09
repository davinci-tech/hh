package com.huawei.hihealthservice.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.jdh;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class FirstSyncForegroundService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.c("FirstSyncForegroundService", "start FirstSyncForegroundService, show the sync notification");
        try {
            startForeground(10010, jdh.c().xf_().build());
            return 2;
        } catch (IllegalStateException e) {
            LogUtil.a("FirstSyncForegroundService", "startForeground appear exception, message:", e.getMessage());
            return 2;
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c("FirstSyncForegroundService", "FirstSyncForegroundService onDestroy");
        stopForeground(false);
    }
}
