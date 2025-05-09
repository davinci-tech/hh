package com.huawei.health.device.ui;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dfd;
import defpackage.dfe;

/* loaded from: classes3.dex */
public class AutoTestHeartRateService extends Service {
    private String b = "";

    /* renamed from: a, reason: collision with root package name */
    private boolean f2229a = true;

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
        if (intent == null) {
            return 1;
        }
        this.b = intent.getStringExtra("mProductId");
        new b().start();
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.f2229a = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IHealthDeviceCallback iHealthDeviceCallback, HealthDevice healthDevice, HealthData healthData) {
        iHealthDeviceCallback.onDataChanged(healthDevice, healthData);
    }

    class b extends Thread {
        private b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            do {
                try {
                    Thread.sleep(5000L);
                    AutoTestHeartRateService.this.c(new dfd(50001), dfe.a().b(AutoTestHeartRateService.this.b), dfe.a().d(AutoTestHeartRateService.this.b));
                } catch (InterruptedException unused) {
                    LogUtil.c("AutoTestHeartRateService", "AutoTestHeartRateService InterruptedException");
                }
            } while (AutoTestHeartRateService.this.f2229a);
        }
    }
}
