package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes5.dex */
public abstract class kp<P> implements ks<P> {

    /* renamed from: a, reason: collision with root package name */
    protected Context f7151a;

    protected abstract String a(P p);

    @Override // com.huawei.openalliance.ad.ks
    public String a(P p, ko koVar) {
        if (koVar != null) {
            koVar.a(p);
        }
        return a(p);
    }

    public kp(Context context) {
        this.f7151a = context;
    }
}
