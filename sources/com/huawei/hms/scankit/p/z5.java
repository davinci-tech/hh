package com.huawei.hms.scankit.p;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class z5 implements g4 {

    /* renamed from: a, reason: collision with root package name */
    private List<g4> f5934a = new ArrayList();

    @Override // com.huawei.hms.scankit.p.g4
    public void a(w5 w5Var) {
        Iterator<g4> it = this.f5934a.iterator();
        while (it.hasNext()) {
            it.next().a(w5Var);
        }
    }

    public void a(g4 g4Var) {
        if (this.f5934a == null) {
            this.f5934a = new ArrayList();
        }
        this.f5934a.add(g4Var);
    }
}
