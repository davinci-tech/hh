package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebPurchaseInfoResp;

/* loaded from: classes9.dex */
public class e0 implements com.huawei.hms.iapfull.network.h<WebPurchaseInfoResp> {

    /* renamed from: a, reason: collision with root package name */
    private m1 f4703a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.h
    public void a(WebPurchaseInfoResp webPurchaseInfoResp) {
        this.b.execute(new d0(this, webPurchaseInfoResp));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("WebPurchaseInfoResultCallback", "WebPurchaseInfoResultCallback request failure...");
            e0.this.f4703a.a(30005, "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.h
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public e0(m1 m1Var) {
        this.f4703a = m1Var;
    }
}
