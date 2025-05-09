package com.huawei.hms.iapfull;

import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.iapfull.network.model.QueryWithholdResultResponse;

/* loaded from: classes4.dex */
public class j implements com.huawei.hms.iapfull.network.e<QueryWithholdResultResponse> {

    /* renamed from: a, reason: collision with root package name */
    private v0 f4719a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.e
    public void a(QueryWithholdResultResponse queryWithholdResultResponse, Headers headers) {
        this.b.execute(new i(this, queryWithholdResultResponse));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("QueryWithholdCallback", "queryWithhold failure");
            j.this.f4719a.a("client10008", "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.e
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public j(v0 v0Var) {
        this.f4719a = v0Var;
    }
}
