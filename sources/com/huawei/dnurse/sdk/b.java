package com.huawei.dnurse.sdk;

import android.os.Handler;
import com.huawei.dnurse.sdk.DnurseDeviceTest;

/* loaded from: classes3.dex */
class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ DnurseDeviceTest f1956a;

    @Override // java.lang.Runnable
    public void run() {
        DnurseDeviceTest.b bVar;
        Handler handler;
        int i;
        bVar = this.f1956a.G;
        if (bVar.a().booleanValue()) {
            this.f1956a.b();
            return;
        }
        handler = this.f1956a.K;
        Runnable runnable = this.f1956a.f1951a;
        i = this.f1956a.o;
        handler.postDelayed(runnable, i);
    }

    b(DnurseDeviceTest dnurseDeviceTest) {
        this.f1956a = dnurseDeviceTest;
    }
}
