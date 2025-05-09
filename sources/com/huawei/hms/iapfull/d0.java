package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebPurchaseInfoResp;

/* loaded from: classes9.dex */
class d0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebPurchaseInfoResp f4698a;
    final /* synthetic */ e0 b;

    @Override // java.lang.Runnable
    public void run() {
        y0.b("WebPurchaseInfoResultCallback", "WebPurchaseInfoResultCallback request success...");
        this.b.f4703a.a(this.f4698a);
    }

    d0(e0 e0Var, WebPurchaseInfoResp webPurchaseInfoResp) {
        this.b = e0Var;
        this.f4698a = webPurchaseInfoResp;
    }
}
