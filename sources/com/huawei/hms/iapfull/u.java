package com.huawei.hms.iapfull;

import com.huawei.hms.iapfull.bean.WebOrderResp;

/* loaded from: classes9.dex */
public class u implements com.huawei.hms.iapfull.network.h<WebOrderResp> {

    /* renamed from: a, reason: collision with root package name */
    private j1 f4768a;
    private c1 b = new c1();

    @Override // com.huawei.hms.iapfull.network.h
    public void a(WebOrderResp webOrderResp) {
        this.b.execute(new t(this, webOrderResp));
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            y0.a("WebOrderDetailResultCallback", "WebOrderDetailResultCallback request failure...");
            u.this.f4768a.a(30005, "network error");
        }

        a() {
        }
    }

    @Override // com.huawei.hms.iapfull.network.h
    public void onFailure(Throwable th) {
        this.b.execute(new a());
    }

    public u(j1 j1Var) {
        this.f4768a = j1Var;
    }
}
