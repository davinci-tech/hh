package com.huawei.hms.iapfull.webpay;

import com.huawei.hms.iapfull.f0;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
class a implements f0 {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f4776a;

    @Override // com.huawei.hms.iapfull.f0
    public void a(String str) {
        y0.a("WebPayPresenter", "onQueryGrsFail: GRS fail " + str);
        this.f4776a.a().a();
        this.f4776a.a().finish();
    }

    @Override // com.huawei.hms.iapfull.f0
    public void a() {
        b.a(this.f4776a);
    }

    a(b bVar) {
        this.f4776a = bVar;
    }
}
