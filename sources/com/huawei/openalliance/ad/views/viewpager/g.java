package com.huawei.openalliance.ad.views.viewpager;

import android.database.DataSetObserver;

/* loaded from: classes5.dex */
class g extends DataSetObserver {

    /* renamed from: a, reason: collision with root package name */
    private e f8172a;

    @Override // android.database.DataSetObserver
    public void onInvalidated() {
        this.f8172a.a();
    }

    @Override // android.database.DataSetObserver
    public void onChanged() {
        this.f8172a.a();
    }

    g(e eVar) {
        this.f8172a = eVar;
    }
}
