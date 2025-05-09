package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.network.model.WebPayPayResponse;

/* loaded from: classes9.dex */
class x implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebPayPayResponse f4786a;
    final /* synthetic */ y b;

    @Override // java.lang.Runnable
    public void run() {
        if (this.f4786a.getReturnCode() == 0) {
            y0.b("WebPayResultCallback", "WebPayResultCallback request success...");
            this.b.f4788a.a(this.f4786a);
        } else {
            y0.a("WebPayResultCallback", "WebPayResultCallback request returncode failure...");
            this.b.f4788a.a(this.f4786a.getReturnCode(), this.f4786a.getReturnDesc());
        }
    }

    x(y yVar, WebPayPayResponse webPayPayResponse) {
        this.b = yVar;
        this.f4786a = webPayPayResponse;
    }
}
