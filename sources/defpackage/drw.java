package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class drw {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("symptom")
    private String f11811a;

    @SerializedName("interventionDimensions")
    private List<Object> c;

    @SerializedName("interventionTarget")
    private drv d;

    public String toString() {
        return "HealthInterventionPlan{mSymptom=" + this.f11811a + ", mHealthInterventionTarget=" + this.d + ", mHealthInterventionDimensionList=" + this.c + "}";
    }
}
