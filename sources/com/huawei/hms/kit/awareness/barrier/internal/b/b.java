package com.huawei.hms.kit.awareness.barrier.internal.b;

/* loaded from: classes9.dex */
public class b extends f {
    private static final float b = 0.0f;
    private float c;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public com.huawei.hms.kit.awareness.barrier.internal.type.f d() {
        return com.huawei.hms.kit.awareness.barrier.internal.type.f.AMBIENT;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public boolean c() {
        return this.c > 0.0f;
    }

    public void a(float f) {
        this.c = f;
    }

    public float a() {
        return this.c;
    }

    public b(float f) {
        this.c = f;
    }
}
