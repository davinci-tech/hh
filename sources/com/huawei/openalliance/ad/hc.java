package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.linked.view.LinkedAppDetailView;

/* loaded from: classes9.dex */
public class hc {

    /* renamed from: a, reason: collision with root package name */
    private LinkedAppDetailView f6898a;
    private gs b;

    public boolean d() {
        LinkedAppDetailView linkedAppDetailView = this.f6898a;
        return linkedAppDetailView != null && linkedAppDetailView.getVisibility() == 0;
    }

    public void c() {
        LinkedAppDetailView linkedAppDetailView = this.f6898a;
        if (linkedAppDetailView != null) {
            linkedAppDetailView.setVisibility(8);
        }
    }

    public boolean b() {
        ContentRecord a2;
        gs gsVar = this.b;
        return (gsVar == null || (a2 = gsVar.a()) == null || a2.ae() == null) ? false : true;
    }

    public void a(LinkedAppDetailView linkedAppDetailView) {
        this.f6898a = linkedAppDetailView;
    }

    public void a(gs gsVar) {
        this.b = gsVar;
    }

    public void a() {
        if (this.f6898a == null || this.b == null || !b()) {
            return;
        }
        this.f6898a.setAdLandingPageData(this.b.c());
        this.f6898a.setContentRecord(this.b.a());
        this.f6898a.setVisibility(0);
    }
}
