package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebProductDetailResponse;

/* loaded from: classes9.dex */
class z implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebProductDetailResponse f4790a;
    final /* synthetic */ a0 b;

    @Override // java.lang.Runnable
    public void run() {
        y0.b("WebProductDetailCallback", "WebProductDetailResultCallback request success...");
        this.b.f4689a.a(this.f4790a);
    }

    z(a0 a0Var, WebProductDetailResponse webProductDetailResponse) {
        this.b = a0Var;
        this.f4790a = webProductDetailResponse;
    }
}
