package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
final class x0 {

    /* renamed from: a, reason: collision with root package name */
    private final int f5916a;
    private final int b;
    private final int c;
    private final int d;
    private int e = -1;

    x0(int i, int i2, int i3, int i4) {
        this.f5916a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    boolean a(int i) {
        return i != -1 && this.c == (i % 3) * 3;
    }

    int b() {
        return this.b;
    }

    int c() {
        return this.e;
    }

    int d() {
        return this.f5916a;
    }

    int e() {
        return this.d;
    }

    int f() {
        return this.b - this.f5916a;
    }

    boolean g() {
        return a(this.e);
    }

    void h() {
        this.e = ((this.d / 30) * 3) + (this.c / 3);
    }

    public String toString() {
        return this.e + "|" + this.d;
    }

    int a() {
        return this.c;
    }

    void b(int i) {
        this.e = i;
    }
}
