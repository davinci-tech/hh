package com.huawei.health.manager.util;

import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.StepCounterSupport;

/* loaded from: classes.dex */
public class DeviceClassWatchDog {

    /* renamed from: a, reason: collision with root package name */
    private Context f2805a;
    private int e = 0;
    private boolean c = true;
    private long d = 0;
    private long b = 0;

    public DeviceClassWatchDog(Context context) {
        if (context != null) {
            this.f2805a = context;
        }
    }

    public boolean a() {
        return this.c;
    }

    public boolean d(int i) {
        if (SharedPerferenceUtils.ae(this.f2805a)) {
            LogUtil.c("Step_DeviceClassWatchDog", "ability is one");
            return false;
        }
        if (Build.VERSION.SDK_INT >= 29 && StepCounterSupport.h(this.f2805a) && ContextCompat.checkSelfPermission(this.f2805a, "android.permission.ACTIVITY_RECOGNITION") != 0) {
            ReleaseLogUtil.b("Step_DeviceClassWatchDog", "isEnableChangeClass permission not granted ");
            return false;
        }
        if (this.e == i) {
            return false;
        }
        this.e = i;
        if (this.b == 0) {
            this.d++;
        } else {
            this.b = 0L;
            this.d = 0L;
        }
        LogUtil.d("Step_DeviceClassWatchDog", "isEnableChangeClass stand : ", Long.valueOf(this.d), " private: ", Long.valueOf(this.b));
        long j = this.d;
        if (j < 100) {
            return false;
        }
        LogUtil.c("Step_DeviceClassWatchDog", "isEnableChangeClass stand : ", Long.valueOf(j), " private: ", Long.valueOf(this.b));
        this.c = false;
        return true;
    }

    public void e() {
        if (this.c) {
            this.b++;
        }
    }
}
