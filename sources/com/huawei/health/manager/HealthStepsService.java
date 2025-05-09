package com.huawei.health.manager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.huawei.haf.common.exception.ExceptionUtils;
import defpackage.jdh;
import health.compact.a.LogicalStepCounter;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class HealthStepsService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f2769a = false;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ReleaseLogUtil.e("HealthStepsService", "HealthStepsService onCreate");
        f2769a = true;
        try {
            startForeground(10010, jdh.c().xf_().build());
            ReleaseLogUtil.e("HealthStepsService", "HealthStepsService startForeground");
            LogicalStepCounter.c(this).p();
        } catch (Throwable th) {
            ReleaseLogUtil.e("HealthStepsService", "Remote Service throwable is ", ExceptionUtils.d(th));
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("HealthStepsService", "HealthStepsService onDestroy()");
        stopForeground(true);
        f2769a = false;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (!akl_(intent) || !f2769a) {
            return 1;
        }
        f2769a = false;
        ReleaseLogUtil.e("HealthStepsService", "HealthStepsService to stop");
        stopSelf();
        return 2;
    }

    private boolean akl_(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.getBooleanExtra("isStop", false);
    }
}
