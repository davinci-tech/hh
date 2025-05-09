package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.network.model.QueryWithholdResultResponse;

/* loaded from: classes9.dex */
class i implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ QueryWithholdResultResponse f4717a;
    final /* synthetic */ j b;

    @Override // java.lang.Runnable
    public void run() {
        y0.b("QueryWithholdCallback", "queryWithhold success");
        this.b.f4719a.a(this.f4717a);
    }

    i(j jVar, QueryWithholdResultResponse queryWithholdResultResponse) {
        this.b = jVar;
        this.f4717a = queryWithholdResultResponse;
    }
}
