package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public class c6 implements Comparable<c6> {

    /* renamed from: a, reason: collision with root package name */
    public i2 f5756a;
    public int b;

    public c6(i2 i2Var, int i) {
        this.f5756a = i2Var;
        this.b = i;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(c6 c6Var) {
        return Float.compare((-c6Var.f5756a.g()) + c6Var.f5756a.h(), (-this.f5756a.g()) + this.f5756a.h());
    }
}
