package com.huawei.hms.scankit.p;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class x5 implements f4 {

    /* renamed from: a, reason: collision with root package name */
    private List<f4> f5920a = new ArrayList();

    @Override // com.huawei.hms.scankit.p.f4
    public void a(w5 w5Var, long j) {
        Iterator<f4> it = this.f5920a.iterator();
        while (it.hasNext()) {
            it.next().a(w5Var, j);
        }
    }

    public void a(f4 f4Var) {
        if (this.f5920a == null) {
            this.f5920a = new ArrayList();
        }
        this.f5920a.add(f4Var);
    }
}
