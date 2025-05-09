package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public final class e3 extends u6 {
    private final float e;
    private final int f;
    private final boolean g;

    e3(float f, float f2, float f3, boolean z) {
        this(f, f2, f3, z, 1);
    }

    e3 a(float f, float f2, float f3, boolean z) {
        int i = this.f;
        int i2 = i + 1;
        float f4 = i2;
        float b = ((i * b()) + f2) / f4;
        float c = ((this.f * c()) + f) / f4;
        float f5 = ((this.f * this.e) + f3) / f4;
        boolean z2 = this.g;
        return new e3(b, c, f5, z2 ? z : z2, i2);
    }

    boolean b(float f, float f2, float f3) {
        if (Math.abs(f2 - c()) <= f && Math.abs(f3 - b()) <= f) {
            float abs = Math.abs(f - this.e);
            if (abs <= 1.0f || abs <= this.e) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.hms.scankit.p.u6
    public boolean d() {
        return this.g;
    }

    public float e() {
        return this.e;
    }

    public e3(float f, float f2, float f3, boolean z, int i) {
        super(f, f2, i);
        this.e = f3;
        this.f = i;
        this.g = z;
    }
}
