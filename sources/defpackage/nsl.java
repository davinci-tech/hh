package defpackage;

import health.compact.a.util.LogUtil;

/* loaded from: classes9.dex */
public class nsl {

    /* renamed from: a, reason: collision with root package name */
    private int f15467a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int h;
    private int j;

    public void f(int i) {
        this.j = i;
    }

    public int d(int i) {
        return i / 3600;
    }

    public int a(int i) {
        return (i / 60) % 60;
    }

    public int b(int i) {
        return i % 60;
    }

    public int c(int i) {
        return Math.max(Math.min(i, this.d), this.h);
    }

    public int e(int i) {
        int a2 = a(this.j);
        return i == 1 ? a2 - this.c : a2 + 5;
    }

    public int e() {
        int d = d(this.j);
        if (d == this.e) {
            return 1;
        }
        return d == this.f15467a ? 3 : 2;
    }

    public int a() {
        int i = this.j;
        int i2 = this.h;
        int i3 = (i - i2) / 60;
        int i4 = (this.d - i2) / 60;
        if (i3 == 0) {
            return 1;
        }
        return i3 == i4 ? 3 : 2;
    }

    public int i(int i) {
        int b = b(this.j);
        return i == 1 ? b - this.b : b + 5;
    }

    public int c() {
        return d(this.j) - this.e;
    }

    public String d(String[] strArr, int i) {
        if (strArr != null) {
            return (i < 0 || i >= strArr.length) ? "" : strArr[i];
        }
        LogUtil.b("TimeDialogUtils", "the valid value array is null");
        return null;
    }
}
