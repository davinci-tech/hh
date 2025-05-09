package com.huawei.dnurse.sdk;

import com.huawei.dnurse.sdk.DnurseDeviceTest;

/* loaded from: classes3.dex */
class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ DnurseDeviceTest f1958a;

    @Override // java.lang.Runnable
    public void run() {
        DnurseDeviceTest.a aVar;
        int i;
        aVar = this.f1958a.H;
        aVar.c();
        DnurseDeviceTest dnurseDeviceTest = this.f1958a;
        i = dnurseDeviceTest.n;
        dnurseDeviceTest.a(i);
    }

    d(DnurseDeviceTest dnurseDeviceTest) {
        this.f1958a = dnurseDeviceTest;
    }
}
