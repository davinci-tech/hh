package defpackage;

import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class pwe {

    /* renamed from: a, reason: collision with root package name */
    private String f16297a;
    private String b;
    private int c;
    private boolean d;
    private boolean e;
    private String i;

    public void e(boolean z) {
        this.d = z;
    }

    public boolean b() {
        return this.d;
    }

    public String a() {
        return this.f16297a;
    }

    public void d(String str) {
        this.f16297a = str;
    }

    public int e() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public String d() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public boolean h() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public String c() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public String toString() {
        return "StepSourceItem{mId='" + CommonUtil.l(this.f16297a) + ", mDeviceName='" + this.b + "', mIsLocalDevice=" + this.e + ", mSteps=" + this.i + '}';
    }
}
