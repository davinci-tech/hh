package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebObtainOwnedPurchasesResponse;

/* loaded from: classes9.dex */
class q implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebObtainOwnedPurchasesResponse f4763a;
    final /* synthetic */ s b;

    @Override // java.lang.Runnable
    public void run() {
        y0.b("WebObtainOwnedPurchasesResultCallback", "onResponse request success...");
        this.b.f4764a.a(this.f4763a);
    }

    q(s sVar, WebObtainOwnedPurchasesResponse webObtainOwnedPurchasesResponse) {
        this.b = sVar;
        this.f4763a = webObtainOwnedPurchasesResponse;
    }
}
