package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebProductInfoResp;

/* loaded from: classes9.dex */
class b0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebProductInfoResp f4691a;
    final /* synthetic */ c0 b;

    @Override // java.lang.Runnable
    public void run() {
        y0.b("WebProductInfoResultCallback", "onResponse request success...");
        this.b.f4693a.a(this.f4691a);
    }

    b0(c0 c0Var, WebProductInfoResp webProductInfoResp) {
        this.b = c0Var;
        this.f4691a = webProductInfoResp;
    }
}
