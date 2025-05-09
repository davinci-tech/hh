package com.huawei.hms.scankit.p;

/* loaded from: classes4.dex */
abstract class i7 {
    static final i7 b = new a7(null, 0, 0);

    /* renamed from: a, reason: collision with root package name */
    private final i7 f5800a;

    i7(i7 i7Var) {
        this.f5800a = i7Var;
    }

    final i7 a() {
        return this.f5800a;
    }

    abstract void a(r rVar, byte[] bArr);

    final i7 b(int i, int i2) {
        return new q(this, i, i2);
    }

    final i7 a(int i, int i2) {
        return new a7(this, i, i2);
    }
}
