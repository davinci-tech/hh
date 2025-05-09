package com.huawei.hms.network.embedded;

import com.huawei.hms.network.netdiag.info.SignalInfoMetrics;

/* loaded from: classes9.dex */
public class a5 extends z4 {

    /* renamed from: a, reason: collision with root package name */
    public g5 f5156a = new f5();
    public w4 b = new v4();
    public SignalInfoMetrics c;
    public SignalInfoMetrics d;
    public c5 e;
    public int f;
    public int g;
    public int h;

    public String toString() {
        return "NetDiagInfoImpl{systemControlInfo=" + this.f5156a + ", allDetectInfo=" + this.b + ", signalInfo=" + this.c + ", networkInfo=" + this.e + '}';
    }

    @Override // com.huawei.hms.network.embedded.z4
    public int h() {
        return this.f;
    }

    @Override // com.huawei.hms.network.embedded.z4
    public g5 g() {
        return this.f5156a;
    }

    @Override // com.huawei.hms.network.embedded.z4
    public SignalInfoMetrics f() {
        return this.c;
    }

    @Override // com.huawei.hms.network.embedded.z4
    public c5 e() {
        return this.e;
    }

    @Override // com.huawei.hms.network.embedded.z4
    public int d() {
        return this.h;
    }

    public void c(int i) {
        this.h = i;
    }

    @Override // com.huawei.hms.network.embedded.z4
    public SignalInfoMetrics c() {
        return this.d;
    }

    public void b(SignalInfoMetrics signalInfoMetrics) {
        if (signalInfoMetrics != null) {
            this.c = signalInfoMetrics;
        }
    }

    public void b(int i) {
        this.f = i;
    }

    @Override // com.huawei.hms.network.embedded.z4
    public w4 b() {
        return this.b;
    }

    public void a(SignalInfoMetrics signalInfoMetrics) {
        this.d = signalInfoMetrics;
    }

    public void a(w4 w4Var) {
        if (w4Var != null) {
            this.b = w4Var;
        }
    }

    public void a(g5 g5Var) {
        if (g5Var != null) {
            this.f5156a = g5Var;
        }
    }

    public void a(c5 c5Var) {
        this.e = c5Var;
    }

    public void a(int i) {
        this.g = i;
    }

    @Override // com.huawei.hms.network.embedded.z4
    public int a() {
        return this.g;
    }

    public a5() {
        e5 e5Var = new e5();
        this.c = e5Var;
        this.d = e5Var;
        this.e = new b5();
        this.f = 0;
        this.g = 0;
        this.h = 0;
    }
}
