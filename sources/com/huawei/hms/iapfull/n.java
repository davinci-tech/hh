package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebConsumeOwnedPurchaseResponse;

/* loaded from: classes9.dex */
public class n implements com.huawei.hms.iapfull.network.h<WebConsumeOwnedPurchaseResponse> {

    /* renamed from: a, reason: collision with root package name */
    private g1 f4727a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.h
    public void a(WebConsumeOwnedPurchaseResponse webConsumeOwnedPurchaseResponse) {
        this.b.execute(new m(this, webConsumeOwnedPurchaseResponse));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("WebConsumeOwnedPurchaseResultCallback", "onFailure request failure...");
            n.this.f4727a.a(60005, "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.h
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public n(g1 g1Var) {
        this.f4727a = g1Var;
    }
}
