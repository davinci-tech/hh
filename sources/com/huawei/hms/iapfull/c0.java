package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebProductInfoResp;

/* loaded from: classes4.dex */
public class c0 implements com.huawei.hms.iapfull.network.h<WebProductInfoResp> {

    /* renamed from: a, reason: collision with root package name */
    private l1 f4693a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.h
    public void a(WebProductInfoResp webProductInfoResp) {
        this.b.execute(new b0(this, webProductInfoResp));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("WebProductInfoResultCallback", "onFailure request failure...");
            c0.this.f4693a.a(60005, "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.h
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public c0(l1 l1Var) {
        this.f4693a = l1Var;
    }
}
