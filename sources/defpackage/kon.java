package defpackage;

import com.google.gson.annotations.SerializedName;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class kon {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("at")
    private int f14452a;

    @SerializedName("cd")
    private int b;

    @SerializedName("aat")
    private int d;

    @SerializedName("mm")
    private long f;

    @SerializedName("cr")
    private int g;

    @SerializedName("hr")
    private int h;

    @SerializedName("hz")
    private int i;

    @SerializedName("dt")
    private long j;

    @SerializedName("pa")
    private float k;

    @SerializedName("st")
    private int l;

    @SerializedName("ot")
    private int m;

    @SerializedName("pc")
    private int n;

    @SerializedName("rt")
    private long o;

    @SerializedName("tr")
    private int p;

    @SerializedName("td")
    private int r;

    @SerializedName("ta")
    private int s;

    @SerializedName("ti")
    private long t;

    @SerializedName("wdt")
    private long v;

    @SerializedName("al")
    private int e = 0;

    @SerializedName("UUID")
    private String q = "";

    @SerializedName("mActiveCalorie")
    private int c = 0;

    public void c(long j) {
        this.v = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int j() {
        return ((Integer) jdy.d(Integer.valueOf(this.h))).intValue();
    }

    public void f(int i) {
        this.h = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void j(int i) {
        this.i = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int k() {
        return ((Integer) jdy.d(Integer.valueOf(this.l))).intValue();
    }

    public void l(int i) {
        this.l = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int b() {
        return ((Integer) jdy.d(Integer.valueOf(this.b))).intValue();
    }

    public void a(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void b(float f) {
        this.k = ((Float) jdy.d(Float.valueOf(f))).floatValue();
    }

    public void b(long j) {
        this.j = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int i() {
        return ((Integer) jdy.d(Integer.valueOf(this.g))).intValue();
    }

    public void g(int i) {
        this.g = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int o() {
        return ((Integer) jdy.d(Integer.valueOf(this.p))).intValue();
    }

    public void n(int i) {
        this.p = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int n() {
        return ((Integer) jdy.d(Integer.valueOf(this.r))).intValue();
    }

    public void o(int i) {
        this.r = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int m() {
        return ((Integer) jdy.d(Integer.valueOf(this.s))).intValue();
    }

    public void m(int i) {
        this.s = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int e() {
        return ((Integer) jdy.d(Integer.valueOf(this.f14452a))).intValue();
    }

    public void b(int i) {
        this.f14452a = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.d))).intValue();
    }

    public void e(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int g() {
        return ((Integer) jdy.d(Integer.valueOf(this.n))).intValue();
    }

    public void i(int i) {
        this.n = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long l() {
        return ((Long) jdy.d(Long.valueOf(this.t))).longValue();
    }

    public void d(long j) {
        this.t = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public void h(int i) {
        this.m = i;
    }

    public long f() {
        return this.o;
    }

    public void e(long j) {
        this.o = j;
    }

    public long h() {
        return this.f;
    }

    public void a(long j) {
        this.f = j;
    }

    public int a() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public String q() {
        return this.q;
    }

    public void c(String str) {
        this.q = str;
    }

    public int d() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    public String s() {
        return "WorkoutReportPlayData{wdt=" + this.v + ", hr=" + this.h + ", hz=" + this.i + ", st=" + this.l + ", cd=" + this.b + ", pa=" + this.k + ", dt=" + this.j + ", cr=" + this.g + ", tr=" + this.p + ", td=" + this.r + ", ta=" + this.s + ", at=" + this.f14452a + ", aat=" + this.d + ", pc=" + this.n + ", ti=" + this.t + ", ot=" + this.m + ", rt=" + this.o + ", mm=" + this.f + ", al=" + this.e + ", cal=" + this.c + ", UUID='" + CommonUtil.l(this.q) + '}';
    }
}
