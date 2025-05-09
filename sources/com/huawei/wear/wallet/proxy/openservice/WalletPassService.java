package com.huawei.wear.wallet.proxy.openservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.huawei.wallet.proxy.InitWalletProxy;
import defpackage.stq;
import defpackage.tek;

/* loaded from: classes9.dex */
public class WalletPassService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        stq.b("WalletPassService", "onBind");
        tek.c("WalletPassService", getApplicationContext());
        try {
            InitWalletProxy c = InitWalletProxy.c();
            if (c == null) {
                return null;
            }
            stq.b("WalletPassService", "HealthHmsPassService onBind");
            return c.getServiceBinder(getApplicationContext(), "WalletPassService", intent);
        } catch (ClassNotFoundException unused) {
            stq.b("WalletPassService", "onBind, not PluginAvailable");
            return null;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        stq.b("WalletPassService", "onCreate");
        super.onCreate();
        try {
            InitWalletProxy c = InitWalletProxy.c();
            if (c != null) {
                c.serviceOnCreate(getApplicationContext(), "WalletPassService");
                stq.b("WalletPassService", "HealthHmsPassService onCreate");
            }
        } catch (ClassNotFoundException unused) {
            stq.b("WalletPassService", "onCreate, not PluginAvailable");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        stq.b("WalletPassService", "onDestroy");
        super.onDestroy();
        try {
            InitWalletProxy c = InitWalletProxy.c();
            if (c != null) {
                c.serviceOnDestroy(getApplicationContext(), "WalletPassService");
                stq.b("WalletPassService", "HealthHmsPassService onDestory");
            }
        } catch (ClassNotFoundException unused) {
            stq.b("WalletPassService", "onDestroy, not PluginAvailable");
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        stq.b("WalletPassService", "onStartCommand");
        super.onStartCommand(intent, i, i2);
        stopSelf(i2);
        return 2;
    }
}
