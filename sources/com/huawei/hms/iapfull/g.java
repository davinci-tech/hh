package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.network.model.QueryOrderResponse;

/* loaded from: classes9.dex */
class g implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ QueryOrderResponse f4710a;
    final /* synthetic */ h b;

    @Override // java.lang.Runnable
    public void run() {
        y0.b("QueryOrderCallback", "queryOrder success");
        this.b.f4714a.a(this.f4710a);
    }

    g(h hVar, QueryOrderResponse queryOrderResponse) {
        this.b = hVar;
        this.f4710a = queryOrderResponse;
    }
}
