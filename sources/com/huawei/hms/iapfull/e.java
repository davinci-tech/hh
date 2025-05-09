package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.network.model.AliPaySignResponse;

/* loaded from: classes9.dex */
class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ AliPaySignResponse f4702a;
    final /* synthetic */ f b;

    @Override // java.lang.Runnable
    public void run() {
        this.b.f4707a.a(this.f4702a);
    }

    e(f fVar, AliPaySignResponse aliPaySignResponse) {
        this.b = fVar;
        this.f4702a = aliPaySignResponse;
    }
}
