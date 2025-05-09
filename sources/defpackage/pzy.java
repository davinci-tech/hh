package defpackage;

import com.huawei.hihealth.ResultUtils;

/* loaded from: classes6.dex */
public class pzy extends nnw {

    /* renamed from: a, reason: collision with root package name */
    private pzy f16364a;
    private String b;
    private pzy c;
    private int d;
    private String e;
    private long f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int l;
    private long o;

    public pzy(float f, int i, int i2) {
        super(f);
        this.l = i;
        this.i = i2;
    }

    public int j() {
        return this.i;
    }

    public int o() {
        return this.l;
    }

    public long k() {
        return this.o;
    }

    public String g() {
        return this.e;
    }

    public pzy e() {
        return this.c;
    }

    public void e(pzy pzyVar) {
        this.c = pzyVar;
    }

    public pzy d() {
        return this.f16364a;
    }

    public void c(pzy pzyVar) {
        this.f16364a = pzyVar;
    }

    public void b(String str) {
        this.e = (String) ResultUtils.a(str);
    }

    public void b(long j) {
        this.o = j;
    }

    public boolean l() {
        return this.h;
    }

    public int h() {
        return this.j;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public void b(int i) {
        this.j = i;
    }

    public void e(String str) {
        this.b = str;
    }

    public String c() {
        return this.b;
    }

    public int b() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public long i() {
        return this.f;
    }

    public void e(long j) {
        this.f = j;
    }

    public boolean f() {
        return this.g;
    }

    public void e(boolean z) {
        this.g = z;
    }

    public String toString() {
        return "BloodSugarStorageModel{mTimePeriod=" + this.l + ", mStatusLevel=" + this.i + ", mIsNeedConfirm=" + this.h + ", mStatusLevelColor=" + this.j + ", mTime=" + this.o + ", mDeviceUuid='" + this.e + "', mBeforeMealData=" + this.c + ", mAfterMealData=" + this.f16364a + ", mDataType='" + this.b + "'}";
    }
}
