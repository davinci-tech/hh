package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class ffr {

    @SerializedName("mCadence")
    private int c = -1;

    @SerializedName("mStepLength")
    private int l = -1;

    @SerializedName("mGroundContactTime")
    private int g = -1;

    @SerializedName("mGroundImpactAcceleration")
    private int h = -1;

    @SerializedName("mForeFootStrikePattern")
    private int e = -1;

    @SerializedName("mWholeFootStrikePattern")
    private int p = -1;

    @SerializedName("mHindPawStrikePattern")
    private int f = -1;

    @SerializedName("mStep")
    private int k = -1;

    @SerializedName("mSwingAngle")
    private int q = -1;

    @SerializedName("mEversionExcursion")
    private int b = -101;

    @SerializedName("mHangTime")
    private int i = -1;

    @SerializedName("mImpactHangRate")
    private int m = -1;

    @SerializedName("mTimeInfo")
    private long r = -1;

    @SerializedName("mRideCadence")
    private int o = -1;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mAverageVerticalImpactRate")
    private float f12491a = -1.0f;

    @SerializedName("mImpactPeak")
    private float n = -1.0f;

    @SerializedName("mVerticalOscillation")
    private float t = -1.0f;

    @SerializedName("mGcTimeBalance")
    private float j = -1.0f;

    @SerializedName("mVerticalRatio")
    private float s = -1.0f;

    @SerializedName("mExtraDataMap")
    private LinkedHashMap<String, String> d = new LinkedHashMap<>();

    public int c() {
        return this.c;
    }

    public void e(int i) {
        this.c = i;
    }

    public int n() {
        return this.l;
    }

    public void f(int i) {
        this.l = i;
    }

    public int a() {
        return this.g;
    }

    public void c(int i) {
        this.g = i;
    }

    public int g() {
        return this.h;
    }

    public void d(int i) {
        this.h = i;
    }

    public int b() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int m() {
        return this.p;
    }

    public void o(int i) {
        this.p = i;
    }

    public int i() {
        return this.f;
    }

    public void j(int i) {
        this.f = i;
    }

    public int o() {
        return this.q;
    }

    public void n(int i) {
        this.q = i;
    }

    public int d() {
        return this.b;
    }

    public void b(int i) {
        this.b = i - 100;
    }

    public int j() {
        return this.i;
    }

    public void i(int i) {
        this.i = i;
    }

    public int h() {
        return this.m;
    }

    public void h(int i) {
        this.m = i;
    }

    public void c(long j) {
        this.r = j;
    }

    public int f() {
        return this.o;
    }

    public void g(int i) {
        this.o = i;
    }

    public LinkedHashMap<String, String> e() {
        return this.d;
    }

    public String toString() {
        return "RunPostureDataInfo {mCadence = " + this.c + "mStepLength = " + this.l + "mGroundContactTime = " + this.g + "mGroundImpactAcceleration = " + this.h + "mForeFootStrikePattern = " + this.e + "mWholeFootStrikePattern = " + this.p + "mHindPawStrikePattern = " + this.f + "mStep = " + this.k + "mSwingAngle = " + this.q + "mEversionExcursion = " + this.b + "mHangTime = " + this.i + "mImpactHangRate = " + this.m + "mTimeInfo = " + this.r + "mRideCadence = " + this.o + "mAverageVerticalImpactRate = " + this.f12491a + "mImpactPeak = " + this.n + "mVerticalOscillation = " + this.t + "mGcTimeBalance = " + this.j + "mVerticalRatio = " + this.s + "mExtraDataMap = " + this.d.toString() + "}";
    }
}
