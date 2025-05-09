package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebObtainOwnedPurchasesResponse;

/* loaded from: classes4.dex */
public class s implements com.huawei.hms.iapfull.network.h<WebObtainOwnedPurchasesResponse> {

    /* renamed from: a, reason: collision with root package name */
    private i1 f4764a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.h
    public void a(WebObtainOwnedPurchasesResponse webObtainOwnedPurchasesResponse) {
        this.b.execute(new q(this, webObtainOwnedPurchasesResponse));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("WebObtainOwnedPurchasesResultCallback", "onFailure request failure...");
            s.this.f4764a.a(60005, "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.h
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public s(i1 i1Var) {
        this.f4764a = i1Var;
    }
}
