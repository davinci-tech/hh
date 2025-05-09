package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class fdg extends fdd {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("monthly_level")
    private int f12458a;

    @SerializedName("monthly_problem")
    private int c;

    @SerializedName("monthly_rhythm")
    private int d;

    @SerializedName("monthly_problem_val")
    private int e;

    public void b(int i) {
        this.d = i;
    }

    public void d(int i) {
        this.c = i;
    }

    public void e(int i) {
        this.f12458a = i;
    }

    public void c(int i) {
        this.e = i;
    }

    public String toString() {
        return "QuestionnaireMonthlyResult{monthlyRhythm='" + this.d + "', monthlyProblem='" + this.c + "', monthlyLevel='" + this.f12458a + "', monthlyProblemVal='" + this.e + "'}" + super.toString();
    }
}
