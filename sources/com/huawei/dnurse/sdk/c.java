package com.huawei.dnurse.sdk;

import android.os.Handler;
import com.huawei.dnurse.sdk.DnurseDeviceTest;

/* loaded from: classes3.dex */
class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ DnurseDeviceTest f1957a;

    @Override // java.lang.Runnable
    public void run() {
        DnurseDeviceTest.b bVar;
        boolean z;
        Handler handler;
        int i;
        if (this.f1957a.f != 0) {
            bVar = this.f1957a.G;
            if (!bVar.a().booleanValue()) {
                handler = this.f1957a.K;
                Runnable runnable = this.f1957a.b;
                i = this.f1957a.q;
                handler.postDelayed(runnable, i);
                return;
            }
            this.f1957a.b(0);
            z = this.f1957a.B;
            if (z) {
                this.f1957a.wakeupDevice();
            }
        }
    }

    c(DnurseDeviceTest dnurseDeviceTest) {
        this.f1957a = dnurseDeviceTest;
    }
}
