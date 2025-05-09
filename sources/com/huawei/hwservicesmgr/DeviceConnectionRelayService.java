package com.huawei.hwservicesmgr;

import android.app.Service;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.IBinder;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.joa;

/* loaded from: classes9.dex */
public class DeviceConnectionRelayService extends Service {
    private static final IBinder e = null;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("DeviceConnectionRelayService", "DeviceConnectionRelayService is start");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.a("DeviceConnectionRelayService", "DeviceConnectionRelayService is bind");
        if (intent != null) {
            try {
                if (BaseApplication.APP_PACKAGE_HEALTH_TV.equals(intent.getStringExtra("package_name"))) {
                    joa.bIz_(intent);
                }
            } catch (BadParcelableException unused) {
                LogUtil.b("DeviceConnectionRelayService", "onBind getStringExtra catch BadParcelableException");
            }
        }
        stopSelf();
        LogUtil.a("DeviceConnectionRelayService", "DeviceConnectionRelayService is stopSelf.");
        return e;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("DeviceConnectionRelayService", "DeviceConnectionRelayService is Destroy");
    }
}
