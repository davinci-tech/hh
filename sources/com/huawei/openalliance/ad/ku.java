package com.huawei.openalliance.ad;

import java.io.InputStream;

/* loaded from: classes5.dex */
public abstract class ku<R> implements lb<R> {

    /* renamed from: a, reason: collision with root package name */
    private long f7153a;

    protected abstract R a(String str);

    @Override // com.huawei.openalliance.ad.lb
    public R a(int i, InputStream inputStream, long j, ko koVar) {
        String a2 = com.huawei.openalliance.ad.utils.cy.a(inputStream);
        this.f7153a = System.currentTimeMillis();
        R a3 = a(a2);
        if (koVar != null) {
            koVar.a(a3);
        }
        return a3;
    }

    @Override // com.huawei.openalliance.ad.lb
    public long a() {
        return this.f7153a;
    }
}
