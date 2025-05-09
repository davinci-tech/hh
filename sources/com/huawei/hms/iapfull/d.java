package com.huawei.hms.iapfull;

import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.iapfull.network.model.DeveloperSignResponse;

/* loaded from: classes4.dex */
public class d implements com.huawei.hms.iapfull.network.e<DeveloperSignResponse> {

    /* renamed from: a, reason: collision with root package name */
    private q0 f4696a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.e
    public void a(DeveloperSignResponse developerSignResponse, Headers headers) {
        this.b.execute(new c(this, developerSignResponse));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("DeveloperSignCallback", "request failure...");
            d.this.f4696a.a("30005", "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.e
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public d(q0 q0Var) {
        this.f4696a = q0Var;
    }
}
