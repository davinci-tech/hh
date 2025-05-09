package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.network.model.WebPayPayResponse;

/* loaded from: classes9.dex */
class v implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebPayPayResponse f4770a;
    final /* synthetic */ w b;

    @Override // java.lang.Runnable
    public void run() {
        if (this.f4770a.getReturnCode() == 0) {
            y0.b("WebPayCreatePurchaseCallback", "onResponse request success...");
            this.b.f4771a.a(this.f4770a);
        } else {
            y0.a("WebPayCreatePurchaseCallback", "onResponse request failure...");
            this.b.f4771a.a(this.f4770a.getReturnCode(), this.f4770a.getErrMsg());
        }
    }

    v(w wVar, WebPayPayResponse webPayPayResponse) {
        this.b = wVar;
        this.f4770a = webPayPayResponse;
    }
}
