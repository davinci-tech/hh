package defpackage;

import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes3.dex */
public class cgo {

    /* renamed from: a, reason: collision with root package name */
    private int f705a;
    private int b;
    private float c;
    private int d;
    private boolean e = false;
    private int f;
    private String g;
    private int h;
    private int i;
    private int j;
    private int l;
    private float n;

    public boolean n() {
        return ((Boolean) cpt.d(Boolean.valueOf(this.e))).booleanValue();
    }

    public void a(boolean z) {
        this.e = ((Boolean) cpt.d(Boolean.valueOf(z))).booleanValue();
    }

    public float c() {
        return ((Float) cpt.d(Float.valueOf(this.c))).floatValue();
    }

    public void d(float f) {
        this.c = ((Float) cpt.d(Float.valueOf(f))).floatValue();
    }

    public float i() {
        return ((Float) cpt.d(Float.valueOf(this.n))).floatValue();
    }

    public void c(float f) {
        this.n = ((Float) cpt.d(Float.valueOf(f))).floatValue();
    }

    public int g() {
        return ((Integer) cpt.d(Integer.valueOf(this.h))).intValue();
    }

    public void a(int i) {
        this.h = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public int h() {
        return ((Integer) cpt.d(Integer.valueOf(this.l))).intValue();
    }

    public void f(int i) {
        this.l = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public int b() {
        return ((Integer) cpt.d(Integer.valueOf(this.i))).intValue();
    }

    public void b(int i) {
        this.i = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public int a() {
        return ((Integer) cpt.d(Integer.valueOf(this.b))).intValue();
    }

    public void d(int i) {
        this.b = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public int d() {
        return ((Integer) cpt.d(Integer.valueOf(this.d))).intValue();
    }

    public void e(int i) {
        this.d = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public int e() {
        return ((Integer) cpt.d(Integer.valueOf(this.f705a))).intValue();
    }

    public void c(int i) {
        this.f705a = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public int f() {
        return ((Integer) cpt.d(Integer.valueOf(this.f))).intValue();
    }

    public void g(int i) {
        this.f = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public void j(int i) {
        this.j = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public String j() {
        return (String) cpt.d(this.g);
    }

    public void c(String str) {
        this.g = (String) cpt.d(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("fat measure test result.test time:");
        stringBuffer.append(this.l).append(Constants.LINK).append(this.i).append(Constants.LINK).append(this.b);
        stringBuffer.append(" ").append(this.d).append(":").append(this.f705a).append(":").append(this.f);
        return stringBuffer.toString();
    }
}
