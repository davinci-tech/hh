package defpackage;

import java.util.List;

/* loaded from: classes8.dex */
public class exp {

    /* renamed from: a, reason: collision with root package name */
    private String f12371a;
    private int b;
    private List<Integer> c;
    private double d;
    private String e;
    private exs f;
    private double g;
    private exr h;
    private List<exs> i;
    private String j;

    public String b() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public void c(String str) {
        this.j = str;
    }

    public void b(exr exrVar) {
        this.h = exrVar;
    }

    public List<exs> a() {
        return this.i;
    }

    public void e(List<exs> list) {
        this.i = list;
    }

    public void c(double d) {
        this.g = d;
    }

    public void e(exs exsVar) {
        this.f = exsVar;
    }

    public void b(List<Integer> list) {
        this.c = list;
    }

    public double d() {
        return this.d;
    }

    public void e(double d) {
        this.d = d;
    }

    public int c() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public void b(String str) {
        this.f12371a = str;
    }

    public String toString() {
        return "MotionPathInfo{geoHash='" + this.e + "', pathId='" + this.j + "', pathRange=" + this.h + ", pathPoints=" + this.i + ", totalDistance=" + this.g + ", pathLocation=" + this.f + ", heat=" + this.c + ", confidence=" + this.d + ", flag=" + this.b + ", executionTime='" + this.f12371a + "'}";
    }
}
