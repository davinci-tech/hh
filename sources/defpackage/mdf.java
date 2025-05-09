package defpackage;

/* loaded from: classes6.dex */
public class mdf extends mcz implements Comparable<mdf> {

    /* renamed from: a, reason: collision with root package name */
    private int f14892a;
    private int aa;
    private int ab;
    private long ac;
    private String ad;
    private long ae;
    private long af;
    private long ag;
    private int ah;
    private int ai;
    private int an;
    private String b;
    private int c;
    private long d;
    private int e;
    private long f;
    private int g;
    private int h;
    private String i;
    private int j;
    private String k;
    private String l;
    private int m;
    private String n;
    private String o;
    private long p;
    private String q;
    private int r;
    private String s;
    private String t;
    private String u;
    private int v;
    private int w;
    private int x;
    private int y;
    private String z;

    public mdf() {
        super(12);
    }

    public String h() {
        return this.s;
    }

    public void e(String str) {
        this.s = str;
    }

    public String f() {
        return this.u;
    }

    public void g(String str) {
        this.u = str;
    }

    public String b() {
        return this.l;
    }

    public void d(String str) {
        this.l = str;
    }

    public String o() {
        return this.ad;
    }

    public void f(String str) {
        this.ad = str;
    }

    public String e() {
        return this.q;
    }

    public void c(String str) {
        this.q = str;
    }

    public int j() {
        return this.r;
    }

    public void b(int i) {
        this.r = i;
    }

    public int n() {
        return this.x;
    }

    public void a(int i) {
        this.x = i;
    }

    public int l() {
        return this.v;
    }

    public void c(int i) {
        this.v = i;
    }

    public int s() {
        return this.ai;
    }

    public void j(int i) {
        this.ai = i;
    }

    public String a() {
        return this.n;
    }

    public void b(String str) {
        this.n = str;
    }

    public long d() {
        return this.d;
    }

    public void c(long j) {
        this.d = j;
    }

    public long q() {
        return this.ae;
    }

    public void a(long j) {
        this.ae = j;
    }

    public long k() {
        return this.ac;
    }

    public void b(long j) {
        this.ac = j;
    }

    public void d(long j) {
        this.p = j;
    }

    public String p() {
        return this.z;
    }

    public void i(String str) {
        this.z = str;
    }

    public int m() {
        return this.y;
    }

    public void e(int i) {
        this.y = i;
    }

    public String g() {
        return this.t;
    }

    public void a(String str) {
        this.t = str;
    }

    public int t() {
        return this.ah;
    }

    public void h(int i) {
        this.ah = i;
    }

    public int i() {
        return this.w;
    }

    public void d(int i) {
        this.w = i;
    }

    public long c() {
        return this.f;
    }

    public void e(long j) {
        this.f = j;
    }

    public int r() {
        return this.ab;
    }

    public void f(int i) {
        this.ab = i;
    }

    public void o(int i) {
        this.c = i;
    }

    public int y() {
        return this.g;
    }

    public void k(int i) {
        this.g = i;
    }

    public String z() {
        return this.o;
    }

    public void o(String str) {
        this.o = str;
    }

    public String aa() {
        return this.k;
    }

    public void n(String str) {
        this.k = str;
    }

    public int x() {
        return this.e;
    }

    public void g(int i) {
        this.e = i;
    }

    public int ag() {
        return this.aa;
    }

    public void q(int i) {
        this.aa = i;
    }

    public int ad() {
        return this.h;
    }

    public void m(int i) {
        this.h = i;
    }

    public int u() {
        return this.f14892a;
    }

    public void i(int i) {
        this.f14892a = i;
    }

    public long ah() {
        return this.af;
    }

    public void i(long j) {
        this.af = j;
    }

    public long ai() {
        return this.ag;
    }

    public void f(long j) {
        this.ag = j;
    }

    public int ac() {
        return this.m;
    }

    public void n(int i) {
        this.m = i;
    }

    public String ab() {
        return this.i;
    }

    public void j(String str) {
        this.i = str;
    }

    public int w() {
        return this.j;
    }

    public void l(int i) {
        this.j = i;
    }

    public String v() {
        return this.b;
    }

    public void h(String str) {
        this.b = str;
    }

    public int ae() {
        return this.an;
    }

    public void r(int i) {
        this.an = i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof mdf) {
            mdf mdfVar = (mdf) obj;
            String str = this.s;
            if (str != null && str.equals(mdfVar.h()) && mdfVar.ag() == this.aa && mdfVar.ac() == this.m) {
                return true;
            }
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    @Override // java.lang.Comparable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(mdf mdfVar) {
        if (mdfVar == null) {
            return 0;
        }
        int i = this.y;
        int i2 = mdfVar.y;
        return i != i2 ? i2 - i : mdfVar.aa - this.aa;
    }

    public String toString() {
        return "KakaTaskInfo{taskId=" + this.s + ", taskName='" + this.u + "', taskDes='" + this.l + "', taskSpecification='" + this.ad + "', taskDetailUrl='" + this.q + "', taskFrequency=" + this.r + ", taskRewardKaka=" + this.x + ", taskRewardExperience=" + this.v + ", taskTypes=" + this.ai + ", taskConditions='" + this.n + "', eventTimestamp=" + this.d + ", taskSyncTimestamp=" + this.ae + ", taskStartTime=" + this.ac + ", taskEndTime=" + this.p + ", taskStatus='" + this.z + "', taskPriority=" + this.y + ", taskIcon='" + this.t + "', taskIsShow='" + this.w + "', lastTimestamp='" + this.f + "', taskSyncLocation='" + this.ab + "', taskSyncStatus=" + this.ah + ", healthTaskType=" + this.c + ", kakaContinuous=" + this.g + ", preBotton='" + this.o + "', postBotton='" + this.k + "', category=" + this.e + ", taskRule=" + this.aa + ", level=" + this.h + ", bonusType=" + this.f14892a + ", updateTime=" + this.af + ", timeZoneOffset=" + this.ag + ", pictureUrl='" + this.i + "', scenario=" + this.m + ", labelMatch=" + this.j + ", buttonColor='" + this.b + '}';
    }
}
