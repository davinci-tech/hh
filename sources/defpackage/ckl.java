package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class ckl {

    /* renamed from: a, reason: collision with root package name */
    private float f770a;

    @SerializedName("mAnaerobic")
    private int b;
    private int c;

    @SerializedName("mAerobic")
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;

    @SerializedName("mFatburn")
    private int j;
    private int k;

    @SerializedName("mLimit")
    private int l;
    private int m;
    private String n;
    private String o;
    private int p;
    private int q;
    private String r;

    @SerializedName("mWarmup")
    private int s;
    private int t;

    public void c(String str) {
        this.o = str;
    }

    public String k() {
        return this.n;
    }

    public void b(String str) {
        this.n = str;
    }

    public void n(int i) {
        this.q = i;
    }

    public int f() {
        return this.h;
    }

    public void f(int i) {
        this.h = i;
    }

    public int g() {
        return this.f;
    }

    public void h(int i) {
        this.f = i;
    }

    public int i() {
        return this.i;
    }

    public void d(int i) {
        this.i = i;
    }

    public int l() {
        return this.m;
    }

    public void l(int i) {
        this.m = i;
    }

    public int n() {
        return this.k;
    }

    public void k(int i) {
        this.k = i;
    }

    public float a() {
        return this.f770a;
    }

    public void b(float f) {
        this.f770a = f;
    }

    public int c() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public void m(int i) {
        this.p = i;
    }

    public int h() {
        return this.g;
    }

    public void i(int i) {
        this.g = i;
    }

    public int t() {
        return this.t;
    }

    public void o(int i) {
        this.t = i;
    }

    public int b() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int m() {
        return this.l;
    }

    public void g(int i) {
        this.l = i;
    }

    public int d() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public int e() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public int j() {
        return this.j;
    }

    public void j(int i) {
        this.j = i;
    }

    public int q() {
        return this.s;
    }

    public void p(int i) {
        this.s = i;
    }

    public String o() {
        return this.r;
    }

    public void e(String str) {
        this.r = str;
    }

    public String toString() {
        return "PostureRecord{id='" + this.o + "', postureId='" + this.n + "', startTime=" + this.p + ", endTime=" + this.g + ", totalTime=" + this.t + ", userTime=" + this.q + ", goalType=" + this.h + ", goal=" + this.f + ", doneCount=" + this.i + ", minHeartRate=" + this.m + ", maxHeartRate=" + this.k + ", mLimit=" + this.l + ", mAnaerobic=" + this.b + ", mAerobic=" + this.d + ", mFatburn=" + this.j + ", mWarmup=" + this.s + ", carlorie=" + this.f770a + ", bestRecord=" + this.e + ", deviceType=" + this.c + ", postureName=" + this.r + '}';
    }
}
