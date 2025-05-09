package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* loaded from: classes7.dex */
public final class i implements g {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ aa f824a;
    public final /* synthetic */ h b;

    @Override // com.alipay.android.phone.mrpc.core.g
    public final boolean d() {
        return this.f824a.c();
    }

    @Override // com.alipay.android.phone.mrpc.core.g
    public final aa c() {
        return this.f824a;
    }

    @Override // com.alipay.android.phone.mrpc.core.g
    public final ab b() {
        Context context;
        context = this.b.f823a;
        return l.a(context.getApplicationContext());
    }

    @Override // com.alipay.android.phone.mrpc.core.g
    public final String a() {
        return this.f824a.a();
    }

    public i(h hVar, aa aaVar) {
        this.b = hVar;
        this.f824a = aaVar;
    }
}
