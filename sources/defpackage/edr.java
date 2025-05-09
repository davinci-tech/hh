package defpackage;

import java.util.Arrays;
import java.util.Map;

/* loaded from: classes3.dex */
public class edr {

    /* renamed from: a, reason: collision with root package name */
    private float f11967a;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private Map<Long, Integer> ae;
    private Map<Long, Integer> af;
    private int ag;
    private Map<Long, Integer> ah;
    private int b;
    private float c;
    private int d;
    private long e;
    private boolean f = false;
    private int g;
    private int h;
    private long i;
    private float j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private int o;
    private long[] p;
    private long q;
    private int r;
    private int s;
    private int t;
    private float u;
    private float v;
    private int w;
    private int x;
    private int y;
    private int z;

    public int ab() {
        return this.ac;
    }

    public void t(int i) {
        this.ac = i;
    }

    public boolean af() {
        return this.f;
    }

    public void d(boolean z) {
        this.f = z;
    }

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int v() {
        return this.x;
    }

    public void k(int i) {
        this.x = i;
    }

    public float x() {
        return this.v;
    }

    public void b(float f) {
        this.v = f;
    }

    public int y() {
        return this.y;
    }

    public float u() {
        return this.u;
    }

    public void c(float f) {
        this.u = f;
    }

    public void n(int i) {
        this.y = i;
    }

    public int p() {
        return this.w;
    }

    public void l(int i) {
        this.w = i;
    }

    public Map<Long, Integer> z() {
        return this.ah;
    }

    public void c(Map<Long, Integer> map) {
        this.ah = map;
    }

    public Map<Long, Integer> aa() {
        return this.af;
    }

    public void a(Map<Long, Integer> map) {
        this.af = map;
    }

    public int w() {
        return this.aa;
    }

    public void o(int i) {
        this.aa = i;
    }

    public int i() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }

    public int f() {
        return this.g;
    }

    public void c(int i) {
        this.g = i;
    }

    public int c() {
        return this.d;
    }

    public void e(int i) {
        this.d = i;
    }

    public int s() {
        return this.s;
    }

    public void m(int i) {
        this.s = i;
    }

    public int q() {
        return this.r;
    }

    public void g(int i) {
        this.r = i;
    }

    public float h() {
        return this.j;
    }

    public void e(float f) {
        this.j = f;
    }

    public int e() {
        return Math.round(this.f11967a);
    }

    public float d() {
        return this.f11967a;
    }

    public void d(float f) {
        this.f11967a = f;
    }

    public float g() {
        return this.c;
    }

    public void a(float f) {
        this.c = f;
    }

    public long t() {
        return this.q;
    }

    public void a(long j) {
        this.q = j;
    }

    public void c(long j) {
        this.i = j;
    }

    public long j() {
        long j = this.e;
        return j == 0 ? System.currentTimeMillis() : j;
    }

    public void d(long j) {
        this.e = j;
    }

    public void e(long[] jArr) {
        this.p = jArr == null ? new long[0] : (long[]) jArr.clone();
    }

    public int ad() {
        return this.ab;
    }

    public void p(int i) {
        this.ab = i;
    }

    public int k() {
        return this.m;
    }

    public void j(int i) {
        this.m = i;
    }

    public int ac() {
        return this.z;
    }

    public void s(int i) {
        this.z = i;
    }

    public int o() {
        return this.o;
    }

    public void h(int i) {
        this.o = i;
    }

    public int ag() {
        return this.ag;
    }

    public void q(int i) {
        this.ag = i;
    }

    public int r() {
        return this.t;
    }

    public void f(int i) {
        this.t = i;
    }

    public void b(boolean z) {
        this.n = z;
    }

    public boolean m() {
        return this.n;
    }

    public Map<Long, Integer> ah() {
        return this.ae;
    }

    public void d(Map<Long, Integer> map) {
        this.ae = map;
    }

    public int l() {
        return this.l;
    }

    public void d(int i) {
        this.l = i;
    }

    public void r(int i) {
        this.ad = i;
    }

    public int n() {
        return this.k;
    }

    public void i(int i) {
        this.k = i;
    }

    public String toString() {
        return "ActiveRecordData{mDayStep=" + this.ac + ", mStepValue=" + this.y + ", mStepGoal=" + this.w + ", mDayIntensity=" + this.aa + ", mIntensityValue=" + this.h + ", mIntensityGoal=" + this.g + ", mStandValue=" + this.s + ", mStandGoal=" + this.r + ", mDistance=" + this.j + ", mCalorie=" + this.f11967a + ", mClimbs=" + this.c + ", mDayTime=" + this.e + ", mStartTime=" + this.q + ", mEndTime=" + this.i + ", mStartEndOfWeek=" + Arrays.toString(this.p) + ", mWeekAverageStep=" + this.ab + ", mLastWeekAverageStep=" + this.m + ", mWeekAverageIntensity=" + this.z + ", mLastWeekAverageIntensity=" + this.o + ", mYesterdayStand=" + this.ag + ", mLastYesterdayStand=" + this.t + ", mIsSupportFloor=" + this.n + '}';
    }

    public String b() {
        return "ActiveRecordData{a=" + this.ac + ", b=" + this.y + ", c=" + this.w + ", d=" + this.aa + ", e=" + this.h + ", f=" + this.g + ", g=" + this.s + ", h=" + this.r + ", i=" + this.j + ", j=" + this.f11967a + ", k=" + this.c + ", l=" + this.e + ", m=" + this.q + ", n=" + this.i + ", o=" + Arrays.toString(this.p) + ", p=" + this.ab + ", q=" + this.m + ", r=" + this.z + ", s=" + this.o + ", t=" + this.ag + ", u=" + this.t + ", v=" + this.n + '}';
    }
}
