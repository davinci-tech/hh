package com.huawei.hms.scankit.p;

/* loaded from: classes4.dex */
public final class l2 {

    /* renamed from: a, reason: collision with root package name */
    private final int f5822a;
    private final int b;

    public int a() {
        return this.b;
    }

    public int b() {
        return this.f5822a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof l2) {
            l2 l2Var = (l2) obj;
            if (this.f5822a == l2Var.f5822a && this.b == l2Var.b) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.f5822a * 32713) + this.b;
    }

    public String toString() {
        return this.f5822a + "x" + this.b;
    }
}
