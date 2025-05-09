package com.huawei.hms.network.embedded;

import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes9.dex */
public final class a9 {

    /* renamed from: a, reason: collision with root package name */
    public final Set<x7> f5160a = new LinkedHashSet();

    public boolean c(x7 x7Var) {
        boolean contains;
        synchronized (this) {
            contains = this.f5160a.contains(x7Var);
        }
        return contains;
    }

    public void b(x7 x7Var) {
        synchronized (this) {
            this.f5160a.add(x7Var);
        }
    }

    public void a(x7 x7Var) {
        synchronized (this) {
            this.f5160a.remove(x7Var);
        }
    }
}
