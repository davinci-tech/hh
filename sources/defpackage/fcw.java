package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class fcw {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("monthly_problem_val")
    private int f12449a;

    @SerializedName("monthly_level")
    private int b;

    @SerializedName("monthly_problem")
    private int c;

    @SerializedName("monthly_rhythm")
    private int d;

    public int c() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public int b() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int d() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int a() {
        return this.f12449a;
    }

    public void e(int i) {
        this.f12449a = i;
    }

    public String toString() {
        return "QuestionnaireMonthlyResult{monthlyRhythm='" + this.d + "', monthlyProblem='" + this.c + "', monthlyLevel='" + this.b + "', monthlyProblemVal='" + this.f12449a + "'}";
    }
}
