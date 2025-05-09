package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebIsEnvReadyResponse;

/* loaded from: classes9.dex */
class o implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebIsEnvReadyResponse f4736a;
    final /* synthetic */ p b;

    @Override // java.lang.Runnable
    public void run() {
        y0.b("WebIsEnvReadyResultCallback", "onResponse request success...");
        this.b.f4738a.a(this.f4736a);
    }

    o(p pVar, WebIsEnvReadyResponse webIsEnvReadyResponse) {
        this.b = pVar;
        this.f4736a = webIsEnvReadyResponse;
    }
}
