package com.huawei.hms.iapfull.pay;

import com.huawei.hms.iapfull.f0;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
class h implements f0 {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ j f4751a;

    @Override // com.huawei.hms.iapfull.f0
    public void a(String str) {
        y0.a("PayPresenter", "onQueryGrsFail: GRS fail " + str);
        this.f4751a.a().a();
        this.f4751a.a().finish();
    }

    @Override // com.huawei.hms.iapfull.f0
    public void a() {
        j.a(this.f4751a);
    }

    h(j jVar) {
        this.f4751a = jVar;
    }
}
