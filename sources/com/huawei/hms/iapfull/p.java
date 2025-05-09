package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebIsEnvReadyResponse;

/* loaded from: classes4.dex */
public class p implements com.huawei.hms.iapfull.network.h<WebIsEnvReadyResponse> {

    /* renamed from: a, reason: collision with root package name */
    private h1 f4738a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.h
    public void a(WebIsEnvReadyResponse webIsEnvReadyResponse) {
        this.b.execute(new o(this, webIsEnvReadyResponse));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("WebIsEnvReadyResultCallback", "onFailure request failure...");
            p.this.f4738a.a(60005, "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.h
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public p(h1 h1Var) {
        this.f4738a = h1Var;
    }
}
