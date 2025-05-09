package com.huawei.hms.kit.awareness.barrier.internal.b;

import java.util.Set;

/* loaded from: classes9.dex */
public class a extends f {
    private final Set<String> c;
    private final boolean d;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public boolean c() {
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public com.huawei.hms.kit.awareness.barrier.internal.type.f d() {
        return com.huawei.hms.kit.awareness.barrier.internal.type.f.BLUETOOTH;
    }

    public boolean b() {
        return this.d;
    }

    public Set<String> a() {
        return this.c;
    }

    public a(Set<String> set, boolean z) {
        this.c = set;
        this.d = z;
    }
}
