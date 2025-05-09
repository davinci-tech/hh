package com.huawei.dnurse.sdk;

import com.huawei.dnurse.sdk.DnurseDeviceTest;

/* loaded from: classes3.dex */
class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ DnurseDeviceTest f1959a;

    @Override // java.lang.Runnable
    public void run() {
        DnurseDeviceTest dnurseDeviceTest;
        int i;
        DnurseDeviceTest.a aVar;
        DnurseDeviceTest.b bVar;
        switch (this.f1959a.f) {
            case 1:
                aVar = this.f1959a.H;
                if (aVar.f1952a) {
                    bVar = this.f1959a.G;
                    if (bVar.b()) {
                        dnurseDeviceTest = this.f1959a;
                        i = 15;
                    }
                } else {
                    dnurseDeviceTest = this.f1959a;
                    i = 14;
                }
                dnurseDeviceTest.b(i);
            case 2:
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                dnurseDeviceTest = this.f1959a;
                i = 17;
                dnurseDeviceTest.b(i);
            default:
                return;
        }
        dnurseDeviceTest = this.f1959a;
        i = 16;
        dnurseDeviceTest.b(i);
    }

    e(DnurseDeviceTest dnurseDeviceTest) {
        this.f1959a = dnurseDeviceTest;
    }
}
