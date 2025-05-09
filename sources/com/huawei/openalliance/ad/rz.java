package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes5.dex */
public abstract class rz implements sl {

    /* renamed from: a, reason: collision with root package name */
    protected Context f7524a;
    private fv b;

    @Override // com.huawei.openalliance.ad.sl
    public void c() {
    }

    @Override // com.huawei.openalliance.ad.sl
    public void d() {
    }

    public void a(long j) {
        this.b.a(com.huawei.openalliance.ad.utils.ao.c() - j, a());
    }

    public rz(Context context) {
        this.f7524a = context.getApplicationContext();
        this.b = ex.a(context);
    }
}
