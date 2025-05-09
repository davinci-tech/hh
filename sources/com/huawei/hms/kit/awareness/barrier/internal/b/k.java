package com.huawei.hms.kit.awareness.barrier.internal.b;

import android.util.ArraySet;
import java.util.Set;

/* loaded from: classes9.dex */
public class k extends f {
    private final Set<g> c = new ArraySet();
    private final Set<g> d = new ArraySet();

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public boolean c() {
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public com.huawei.hms.kit.awareness.barrier.internal.type.f d() {
        return com.huawei.hms.kit.awareness.barrier.internal.type.f.LOCATION;
    }

    public void b(g gVar) {
        this.d.add(gVar);
    }

    public Set<g> b() {
        return this.d;
    }

    public void a(g gVar) {
        this.c.add(gVar);
    }

    public Set<g> a() {
        return this.c;
    }
}
