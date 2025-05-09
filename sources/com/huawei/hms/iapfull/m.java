package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebConsumeOwnedPurchaseResponse;

/* loaded from: classes9.dex */
class m implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebConsumeOwnedPurchaseResponse f4724a;
    final /* synthetic */ n b;

    @Override // java.lang.Runnable
    public void run() {
        y0.b("WebConsumeOwnedPurchaseResultCallback", "onResponse request success...");
        this.b.f4727a.a(this.f4724a);
    }

    m(n nVar, WebConsumeOwnedPurchaseResponse webConsumeOwnedPurchaseResponse) {
        this.b = nVar;
        this.f4724a = webConsumeOwnedPurchaseResponse;
    }
}
