package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.network.model.WebPayPayResponse;

/* loaded from: classes4.dex */
public class y implements com.huawei.hms.iapfull.network.h<WebPayPayResponse> {

    /* renamed from: a, reason: collision with root package name */
    private n1 f4788a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.h
    public void a(WebPayPayResponse webPayPayResponse) {
        this.b.execute(new x(this, webPayPayResponse));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("WebPayResultCallback", "WebPayResultCallback request failure...");
            y.this.f4788a.a(30005, "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.h
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public y(n1 n1Var) {
        this.f4788a = n1Var;
    }
}
