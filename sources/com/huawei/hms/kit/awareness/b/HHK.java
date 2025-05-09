package com.huawei.hms.kit.awareness.b;

import com.huawei.hms.kit.awareness.b;
import com.huawei.hms.kit.awareness.b.a.b;

/* loaded from: classes4.dex */
class HHK extends b.a {

    /* renamed from: a, reason: collision with root package name */
    private static final long f4818a = 15000;
    private final b.a b = b.a.a();
    private com.huawei.hms.kit.awareness.a.a.f d;

    com.huawei.hms.kit.awareness.a.a.f c() {
        return this.d;
    }

    boolean b() {
        return this.b.a(f4818a);
    }

    @Override // com.huawei.hms.kit.awareness.b
    public void a(com.huawei.hms.kit.awareness.a.a.f fVar) {
        this.d = fVar;
        this.b.b();
    }

    HHK() {
    }
}
