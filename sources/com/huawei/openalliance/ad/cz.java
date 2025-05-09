package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public abstract class cz {

    /* renamed from: a, reason: collision with root package name */
    private cz f6687a = null;

    public abstract boolean a();

    protected boolean b() {
        cz czVar = this.f6687a;
        if (czVar != null) {
            return czVar.a();
        }
        return false;
    }

    public void a(cz czVar) {
        this.f6687a = czVar;
    }
}
