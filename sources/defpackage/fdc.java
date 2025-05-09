package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes.dex */
public class fdc {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("monthly_level")
    private int f12454a;

    @SerializedName("monthly_problem_val")
    private int b;

    @SerializedName("questionnaire")
    private List<String> c;

    @SerializedName("monthly_rhythm")
    private int d;

    @SerializedName("monthly_problem")
    private int e;

    @SerializedName("result_code")
    private int j;

    public int d() {
        return this.j;
    }

    public List<String> b() {
        return this.c;
    }

    public String toString() {
        return "QuestionnaireResultBean{resultCode=" + this.j + ", questionnaire=" + this.c + ", monthlyRhythm='" + this.d + "', monthlyProblem='" + this.e + "', monthlyLevel='" + this.f12454a + "', monthlyProblemVal='" + this.b + "'}";
    }
}
