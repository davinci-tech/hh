package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebOrderResp;

/* loaded from: classes9.dex */
class t implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebOrderResp f4766a;
    final /* synthetic */ u b;

    @Override // java.lang.Runnable
    public void run() {
        y0.b("WebOrderDetailResultCallback", "WebOrderDetailResultCallback request success...");
        this.b.f4768a.a(this.f4766a);
    }

    t(u uVar, WebOrderResp webOrderResp) {
        this.b = uVar;
        this.f4766a = webOrderResp;
    }
}
