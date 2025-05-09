package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public class a6 implements g4 {

    /* renamed from: a, reason: collision with root package name */
    private final float f5735a;
    private final float b;

    public a6(float f, float f2) {
        this.b = f;
        this.f5735a = f2;
    }

    @Override // com.huawei.hms.scankit.p.g4
    public void a(w5 w5Var) {
        float f = this.f5735a;
        float f2 = this.b;
        if (f != f2) {
            f = n6.a(f - f2) + this.b;
        }
        w5Var.b(f);
        w5Var.a(f);
    }
}
