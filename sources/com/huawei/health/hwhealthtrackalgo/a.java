package com.huawei.health.hwhealthtrackalgo;

import defpackage.dwl;
import defpackage.dwq;

/* loaded from: classes3.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public dwq f2496a;

    public abstract int a(dwl dwlVar);

    private dwq a() {
        return this.f2496a;
    }

    public a(dwq dwqVar) {
        if (dwqVar == null) {
            throw new RuntimeException("algoMgr invalid params in constructor");
        }
        this.f2496a = dwqVar;
    }
}
