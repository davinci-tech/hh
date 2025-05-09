package com.huawei.hms.iapfull;

import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.iapfull.network.model.ReportPayResultResponse;

/* loaded from: classes9.dex */
public class l implements com.huawei.hms.iapfull.network.e<ReportPayResultResponse> {

    /* renamed from: a, reason: collision with root package name */
    private s0 f4722a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.e
    public void a(ReportPayResultResponse reportPayResultResponse, Headers headers) {
        this.b.execute(new k(this, reportPayResultResponse, headers));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("ReportPayResutCallback", "request failure...");
            l.this.f4722a.a("30005", "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.e
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public l(s0 s0Var) {
        this.f4722a = s0Var;
    }
}
