package com.huawei.hms.kit.awareness.barrier.internal.b;

import android.util.ArraySet;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

/* loaded from: classes9.dex */
public class c extends f {
    private final Set<com.huawei.hms.kit.awareness.barrier.internal.a.a.a> c = new ArraySet();

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public boolean c() {
        return true;
    }

    public boolean e() {
        return com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.c);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public com.huawei.hms.kit.awareness.barrier.internal.type.f d() {
        return com.huawei.hms.kit.awareness.barrier.internal.type.f.BEACON;
    }

    public void b() {
        this.c.forEach(new Consumer() { // from class: com.huawei.hms.kit.awareness.barrier.internal.b.c$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((com.huawei.hms.kit.awareness.barrier.internal.a.a.a) obj).x();
            }
        });
    }

    public void a(Collection<com.huawei.hms.kit.awareness.barrier.internal.a.a.a> collection) {
        this.c.addAll(collection);
    }

    public void a(com.huawei.hms.kit.awareness.barrier.internal.a.a.a aVar) {
        this.c.add(aVar);
    }

    public Set<com.huawei.hms.kit.awareness.barrier.internal.a.a.a> a() {
        return this.c;
    }
}
