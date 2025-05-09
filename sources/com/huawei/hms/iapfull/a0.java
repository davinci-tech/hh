package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebProductDetailResponse;

/* loaded from: classes9.dex */
public class a0 implements com.huawei.hms.iapfull.network.h<WebProductDetailResponse> {

    /* renamed from: a, reason: collision with root package name */
    private k1 f4689a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.h
    public void a(WebProductDetailResponse webProductDetailResponse) {
        this.b.execute(new z(this, webProductDetailResponse));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("WebProductDetailCallback", "WebProductDetailResultCallback request failure...");
            a0.this.f4689a.a(30005, "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.h
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public a0(k1 k1Var) {
        this.f4689a = k1Var;
    }
}
