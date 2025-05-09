package defpackage;

import java.util.List;

/* loaded from: classes3.dex */
public class eeb {

    /* renamed from: a, reason: collision with root package name */
    private int f11978a;
    private long b;
    private int c;
    private CharSequence d;
    private int e;
    private int f;
    private int g;
    private CharSequence h;
    private int i;
    private int j;
    private List<Integer> k;
    private CharSequence l;
    private CharSequence m;

    public eeb(long j, int i, CharSequence charSequence, int i2) {
        this.b = j;
        this.i = i;
        this.l = charSequence;
        this.f = i2;
    }

    public long b() {
        return this.b;
    }

    public int i() {
        return this.i;
    }

    public CharSequence o() {
        return this.l;
    }

    public int g() {
        return this.f;
    }

    public CharSequence j() {
        return this.h;
    }

    public void e(CharSequence charSequence) {
        this.h = charSequence;
    }

    public int f() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }

    public CharSequence d() {
        return this.d;
    }

    public void c(CharSequence charSequence) {
        this.d = charSequence;
    }

    public CharSequence l() {
        return this.m;
    }

    public void a(CharSequence charSequence) {
        this.m = charSequence;
    }

    public int h() {
        return this.j;
    }

    public void d(int i) {
        this.j = i;
    }

    public List<Integer> n() {
        return this.k;
    }

    public void e(List<Integer> list) {
        this.k = list;
    }

    public int a() {
        return this.f11978a;
    }

    public void a(int i) {
        this.f11978a = i;
    }

    public int e() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public int c() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public String toString() {
        return "SectionActiveWeekBean{mDayTimeMillis=" + this.b + ", mIconResourceId=" + this.i + ", mTitle=" + ((Object) this.l) + ", mIconTipResourceId=" + this.f + ", mTipText=" + ((Object) this.h) + ", mEventType=" + this.g + ", mDayValue=" + ((Object) this.d) + ", mWeekValue=" + ((Object) this.m) + ", mGoalValue=" + this.j + ", mValueList=" + this.k + ", mBarChartColor=" + this.f11978a + ", mBarChartHighlightColor=" + this.c + ", mBarChartTargetColor=" + this.e + '}';
    }
}
