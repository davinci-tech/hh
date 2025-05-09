package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes5.dex */
public class si extends rz {
    private sk b;
    private int c;

    @Override // com.huawei.openalliance.ad.rz, com.huawei.openalliance.ad.sl
    public void d() {
        this.b.a();
    }

    @Override // com.huawei.openalliance.ad.rz, com.huawei.openalliance.ad.sl
    public void c() {
        this.b.b();
    }

    @Override // com.huawei.openalliance.ad.sl
    public void b() {
        super.a(fh.b(this.f7524a).aV());
    }

    @Override // com.huawei.openalliance.ad.sl
    public int a() {
        return this.c;
    }

    public si(Context context, int i) {
        super(context);
        this.b = new rw(context);
        this.c = i;
    }
}
