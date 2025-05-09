package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes9.dex */
public class nk extends nh<com.huawei.openalliance.ad.views.interfaces.m> implements oa {
    @Override // com.huawei.openalliance.ad.oa
    public void i() {
        this.b.c();
    }

    @Override // com.huawei.openalliance.ad.nh
    protected void b(String str) {
        ((com.huawei.openalliance.ad.views.interfaces.m) d()).d();
        ho.b("PPSVideoViewPresenter", "onMaterialLoaded - begin to load video");
        ((com.huawei.openalliance.ad.views.interfaces.m) d()).a(str);
    }

    @Override // com.huawei.openalliance.ad.oa
    public void a(boolean z) {
        this.b.b(z);
    }

    @Override // com.huawei.openalliance.ad.oa
    public void a(long j, long j2, long j3, long j4) {
        this.b.c(j, j2, (int) j3, (int) j4);
    }

    public nk(Context context, com.huawei.openalliance.ad.views.interfaces.m mVar, int i) {
        super(context, mVar, i);
    }
}
