package com.huawei.wear.wallet.proxy.openservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.huawei.wallet.proxy.InitWalletProxy;
import defpackage.stq;
import defpackage.tek;

/* loaded from: classes9.dex */
public class BleCarKeyService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        stq.b("BleCarKeyService", "onBind");
        tek.c("BleCarKeyService", getApplicationContext());
        try {
            InitWalletProxy c = InitWalletProxy.c();
            if (c == null) {
                return null;
            }
            stq.b("BleCarKeyService", "BleCarKeyService onBind");
            return c.getServiceBinder(getApplicationContext(), "BleCarKeyService", intent);
        } catch (ClassNotFoundException unused) {
            stq.b("BleCarKeyService", "onBind, not PluginAvailable");
            return null;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        stq.b("BleCarKeyService", "onCreate");
        super.onCreate();
        tek.c("BleCarKeyService", getApplicationContext());
        try {
            InitWalletProxy c = InitWalletProxy.c();
            if (c != null) {
                c.serviceOnCreate(getApplicationContext(), "BleCarKeyService");
                stq.b("BleCarKeyService", "BleCarKeyService onCreate");
            }
        } catch (ClassNotFoundException unused) {
            stq.b("BleCarKeyService", "onCreate, not PluginAvailable");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        stq.b("BleCarKeyService", "onDestroy");
        super.onDestroy();
        try {
            InitWalletProxy c = InitWalletProxy.c();
            if (c != null) {
                c.serviceOnDestroy(getApplicationContext(), "BleCarKeyService");
                stq.b("BleCarKeyService", "BleCarKeyService onDestory");
            }
        } catch (ClassNotFoundException unused) {
            stq.b("BleCarKeyService", "onDestroy, not PluginAvailable");
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        stq.b("BleCarKeyService", "onStartCommand");
        super.onStartCommand(intent, i, i2);
        stopSelf(i2);
        return 2;
    }
}
