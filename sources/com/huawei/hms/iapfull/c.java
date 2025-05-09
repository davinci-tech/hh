package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.network.model.DeveloperSignResponse;

/* loaded from: classes9.dex */
class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ DeveloperSignResponse f4692a;
    final /* synthetic */ d b;

    @Override // java.lang.Runnable
    public void run() {
        if ("0".equals(this.f4692a.getReturnCode())) {
            y0.b("DeveloperSignCallback", "request success...");
            this.b.f4696a.a(this.f4692a);
        } else {
            this.b.f4696a.a(this.f4692a.getReturnCode(), this.f4692a.getReturnDesc());
        }
    }

    c(d dVar, DeveloperSignResponse developerSignResponse) {
        this.b = dVar;
        this.f4692a = developerSignResponse;
    }
}
