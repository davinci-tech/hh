package com.huawei.hms.iapfull;

import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.iapfull.network.model.AliPaySignResponse;

/* loaded from: classes4.dex */
public class f implements com.huawei.hms.iapfull.network.e<AliPaySignResponse> {

    /* renamed from: a, reason: collision with root package name */
    private r0 f4707a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.e
    public void a(AliPaySignResponse aliPaySignResponse, Headers headers) {
        this.b.execute(new e(this, aliPaySignResponse));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("PaySignCallback", "request failure...");
            f.this.f4707a.a("30005", "request failed");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.e
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public f(r0 r0Var) {
        this.f4707a = r0Var;
    }
}
