package defpackage;

import android.widget.Switch;

/* loaded from: classes9.dex */
public class nku {

    /* renamed from: a, reason: collision with root package name */
    private String f15353a;
    private int f;
    private int g;
    private int i;
    private String c = Switch.class.getName();
    private boolean b = false;
    private boolean d = false;
    private boolean e = false;

    public nku(String str, int i, int i2, int i3) {
        this.f15353a = str;
        this.f = i;
        this.i = i2;
        this.g = i3;
    }

    public String d() {
        return this.f15353a;
    }

    public int e() {
        return this.f;
    }

    public int c() {
        return this.i;
    }

    public int b() {
        return this.g;
    }

    public boolean h() {
        return this.b;
    }

    public void d(Boolean bool) {
        this.b = bool.booleanValue();
    }

    public boolean g() {
        return this.d;
    }

    public void b(Boolean bool) {
        this.d = bool.booleanValue();
    }

    public boolean a() {
        return this.e;
    }

    public void e(boolean z) {
        this.e = z;
    }
}
