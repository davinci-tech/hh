package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.network.model.WebPayPayResponse;

/* loaded from: classes4.dex */
public class w implements com.huawei.hms.iapfull.network.h<WebPayPayResponse> {

    /* renamed from: a, reason: collision with root package name */
    private n1 f4771a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.h
    public void a(WebPayPayResponse webPayPayResponse) {
        this.b.execute(new v(this, webPayPayResponse));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("WebPayCreatePurchaseCallback", "onFailure request failure...");
            w.this.f4771a.a(60005, "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.h
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public w(n1 n1Var) {
        this.f4771a = n1Var;
    }
}
