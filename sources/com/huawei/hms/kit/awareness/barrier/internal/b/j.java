package com.huawei.hms.kit.awareness.barrier.internal.b;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes9.dex */
public class j extends f {
    private final List<com.huawei.hms.kit.awareness.barrier.internal.a.c> c;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public boolean c() {
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public com.huawei.hms.kit.awareness.barrier.internal.type.f d() {
        return com.huawei.hms.kit.awareness.barrier.internal.type.f.ALARM;
    }

    public List<com.huawei.hms.kit.awareness.barrier.internal.a.c> a() {
        return com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.c) ? new ArrayList() : this.c;
    }

    public j(List<com.huawei.hms.kit.awareness.barrier.internal.a.c> list) {
        this.c = list;
    }
}
