package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class cwp {

    @SerializedName("standardIntakeUpper")
    private int d;

    @SerializedName("standardIntakeLower")
    private int e;

    public int d() {
        return this.e;
    }

    public int a() {
        return this.d;
    }

    public String toString() {
        return "NutritionAnalysisItem{mStandardIntakeLower=" + this.e + ", mStandardIntakeUpper=" + this.d + '}';
    }
}
