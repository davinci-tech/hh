package com.huawei.hms.scankit.p;

/* loaded from: classes4.dex */
public final class h6 {

    /* renamed from: a, reason: collision with root package name */
    private u4 f5789a;
    private b3 b;
    private b8 c;
    private int d = -1;
    private c0 e;

    public static boolean a(int i) {
        return i >= 0 && i < 8;
    }

    public c0 a() {
        return this.e;
    }

    public void b(int i) {
        this.d = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n mode: ");
        sb.append(this.f5789a);
        sb.append("\n ecLevel: ");
        sb.append(this.b);
        sb.append("\n version: ");
        sb.append(this.c);
        sb.append("\n maskPattern: ");
        sb.append(this.d);
        sb.append(">>\n");
        return sb.toString();
    }

    public void a(u4 u4Var) {
        this.f5789a = u4Var;
    }

    public void a(b3 b3Var) {
        this.b = b3Var;
    }

    public void a(b8 b8Var) {
        this.c = b8Var;
    }

    public void a(c0 c0Var) {
        this.e = c0Var;
    }
}
