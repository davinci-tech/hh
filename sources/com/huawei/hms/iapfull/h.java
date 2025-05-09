package com.huawei.hms.iapfull;

import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.iapfull.network.model.QueryOrderResponse;

/* loaded from: classes4.dex */
public class h implements com.huawei.hms.iapfull.network.e<QueryOrderResponse> {

    /* renamed from: a, reason: collision with root package name */
    private u0 f4714a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.e
    public void a(QueryOrderResponse queryOrderResponse, Headers headers) {
        this.b.execute(new g(this, queryOrderResponse));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("QueryOrderCallback", "queryOrder failure");
            h.this.f4714a.a("30005", "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.e
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public h(u0 u0Var) {
        this.f4714a = u0Var;
    }
}
