package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes.dex */
public class qts {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("caloricDeficitGoal")
    private int f16587a;

    @SerializedName("activityCalGoal")
    private int b;

    @SerializedName(ParsedFieldTag.GOAL)
    private float c;

    @SerializedName("consumption")
    private float d;

    @SerializedName("canTake")
    private float e;

    @SerializedName("inTake")
    private float f;

    @SerializedName("weekGoalDiff")
    private double g;

    @SerializedName("updateTime")
    private long h;

    @SerializedName("restingCalories")
    private float i;

    @SerializedName("restingCaloriesBurned")
    private int j;

    public qts(float f, float f2, float f3, float f4) {
        this.f = f;
        this.d = f2;
        this.c = f3;
        this.i = f4;
        this.e = Math.max(f3 - f, 0.0f);
    }

    public void d(float f) {
        float f2 = this.f + f;
        this.f = f2;
        float f3 = this.c - f2;
        this.e = f3;
        this.e = Math.max(0.0f, f3);
    }

    public void e(float f) {
        this.f = f;
        float f2 = this.c - f;
        this.e = f2;
        this.e = Math.max(0.0f, f2);
    }

    public float f() {
        return this.f;
    }

    public float a() {
        return this.e;
    }

    public float e() {
        return this.d;
    }

    public float c() {
        return this.c;
    }

    public float i() {
        return this.i;
    }

    public double j() {
        return this.g;
    }

    public void a(double d) {
        this.g = d;
    }

    public void c(float f) {
        this.d = f;
        float f2 = this.c - this.f;
        this.e = f2;
        this.e = Math.max(0.0f, f2);
    }

    public void b(float f) {
        this.c = f;
        float f2 = f - this.f;
        this.e = f2;
        this.e = Math.max(0.0f, f2);
    }

    public void a(float f) {
        this.i = f;
    }

    public int h() {
        return this.j;
    }

    public void a(int i) {
        this.j = i;
    }

    public int d() {
        return this.f16587a;
    }

    public void e(int i) {
        this.f16587a = i;
    }

    public int b() {
        return this.b;
    }

    public void c(int i) {
        this.b = i;
    }

    public long g() {
        return this.h;
    }

    public void b(long j) {
        this.h = j;
    }

    public String toString() {
        return "DietCalorieOverview{mInTake=" + this.f + ", mCanTake=" + this.e + ", mConsumption=" + this.d + ", mGoal=" + this.c + ", mRestingCalories=" + this.i + ", mRestingCaloriesBurned=" + this.j + ", mWeekGoalDiff=" + this.g + ", mCaloricDeficitGoal=" + this.f16587a + ", mActivityCalGoal=" + this.b + ", mUpdateTime=" + this.h + '}';
    }
}
