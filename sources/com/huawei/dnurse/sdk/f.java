package com.huawei.dnurse.sdk;

import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
class f extends TimerTask {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ DnurseDeviceTest f1960a;

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        byte b;
        Timer timer;
        IMeasureDataResultCallback iMeasureDataResultCallback;
        byte b2;
        if (this.f1960a.f == 7) {
            DnurseDeviceTest.k(this.f1960a);
            b = this.f1960a.D;
            if (b <= 0) {
                timer = this.f1960a.L;
                timer.cancel();
                this.f1960a.a(3000);
            } else {
                iMeasureDataResultCallback = this.f1960a.J;
                int i = this.f1960a.f;
                b2 = this.f1960a.D;
                iMeasureDataResultCallback.onMeasuring(i, b2);
            }
        }
    }

    f(DnurseDeviceTest dnurseDeviceTest) {
        this.f1960a = dnurseDeviceTest;
    }
}
