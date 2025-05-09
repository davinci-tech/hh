package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;

/* loaded from: classes.dex */
public class mxf {
    private static final HeartZoneConf e = new HeartZoneConf();

    @SerializedName("walkRunThreshold")
    private int o = 110;

    @SerializedName("climbThreshold")
    private int i = 60;

    @SerializedName("heartRateThreshold")
    private int g = 65;

    @SerializedName("cycleSpeedThreshold")
    private int j = 80;

    @SerializedName("sampleThreshold")
    private int k = 3;

    @SerializedName("countLength")
    private int f = 5;

    @SerializedName("walkRunThreshold_v2")
    private int p = 105;

    @SerializedName("climbThreshold_v2")
    private int b = 60;

    @SerializedName("heartRateThreshold_v2")
    private int c = 20;

    @SerializedName("cycleSpeedThreshold_v2")
    private int d = 88;

    @SerializedName("sampleThreshold_v2")
    private int m = 1;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("countLength_v2")
    private int f15230a = 1;

    @SerializedName("walkRunThresholdWithHeartRate_v2")
    private int l = 40;

    @SerializedName("heartRateThreshold_v3")
    private int h = 25;

    @SerializedName("walkRunSpeedThreshold_v2")
    private int n = 40;

    public int l() {
        return this.o;
    }

    public int c() {
        return this.i;
    }

    public int g() {
        return this.g;
    }

    public int d() {
        return this.j;
    }

    public int h() {
        return this.k;
    }

    public int a() {
        return this.f;
    }

    public void b(int i) {
        this.i = i;
    }

    public void j(int i) {
        this.g = i;
    }

    public void a(int i) {
        this.j = i;
    }

    public void f(int i) {
        this.k = i;
    }

    public void c(int i) {
        this.f = i;
    }

    public int n() {
        return this.n;
    }

    public void n(int i) {
        this.n = i;
    }

    public void o(int i) {
        this.o = i;
    }

    public int d(HeartZoneConf heartZoneConf) {
        return (int) (((j() / 100.0f) * (heartZoneConf.getMaxThreshold() - heartZoneConf.getRestHeartRate())) + heartZoneConf.getRestHeartRate());
    }

    public int k() {
        return this.p;
    }

    public void l(int i) {
        this.p = i;
    }

    public int e() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public int i() {
        return this.d;
    }

    public void h(int i) {
        this.d = i;
    }

    public int f() {
        return this.m;
    }

    public void i(int i) {
        this.m = i;
    }

    public int b() {
        return this.f15230a;
    }

    public void d(int i) {
        this.f15230a = i;
    }

    public int m() {
        return this.l;
    }

    public int j() {
        return this.h;
    }

    public void g(int i) {
        this.h = i;
    }

    public String toString() {
        return "SportIntensityConfig WalkRunThreshold = " + this.p + " ClimbThreshold = " + this.b + " CycleSpeedThreshold = " + this.d + " WalkRunSpeedThreshold = " + this.n + " WalkRunThresholdWithHeartRate = " + this.l + " heartRateThreshold = " + this.h;
    }
}
