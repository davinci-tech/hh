package defpackage;

import com.huawei.up.model.UserInfomation;

/* loaded from: classes7.dex */
public class qtk {

    /* renamed from: a, reason: collision with root package name */
    private int f16582a;
    private int b;
    private int c;
    private int d;
    private int e;
    private final UserInfomation g = gni.c();
    private double j;

    public UserInfomation j() {
        return this.g;
    }

    public int a() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int d() {
        int i = this.f16582a;
        return i > 0 ? i : this.c;
    }

    public void a(int i) {
        this.f16582a = i;
    }

    public int e() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public int b() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public int c() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public double h() {
        return this.j;
    }

    public void d(double d) {
        this.j = d;
    }

    public String toString() {
        return "DietRecordBaseInfo{mUserInformation=" + this.g + ", mRestingCaloriesBasic=" + this.c + ", mRestingCalories=" + this.f16582a + ", mRestingCaloriesBurned=" + this.e + ", mCaloricDeficitGoal=" + this.d + ", mActivityCalGoal=" + this.b + ", mWeightTargetDifferences=" + this.j + '}';
    }
}
