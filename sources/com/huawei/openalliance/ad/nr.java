package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes5.dex */
public class nr extends jj<com.huawei.openalliance.ad.views.interfaces.s> implements og<com.huawei.openalliance.ad.views.interfaces.s> {
    private gc d;
    private Context e;
    private ns f;
    private hl g;
    private boolean h = false;

    @Override // com.huawei.openalliance.ad.og
    public boolean a(int i, boolean z) {
        ho.b("SloganPresenter", "loadSloganRecord " + i);
        b(i, z);
        return this.h;
    }

    @Override // com.huawei.openalliance.ad.og
    public void a(hl hlVar) {
        this.g = hlVar;
    }

    private void b(int i, boolean z) {
        ho.b("SloganPresenter", "show default slogan");
        if (z) {
            nb nbVar = new nb(this.d, this.g);
            this.f = nbVar;
            nbVar.a();
        }
        d().a(i);
    }

    public nr(Context context, com.huawei.openalliance.ad.views.interfaces.s sVar, int i) {
        a((nr) sVar);
        this.e = context.getApplicationContext();
        this.d = fh.b(context);
    }
}
