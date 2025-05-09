package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class drv {

    @SerializedName("version")
    private String b;

    @SerializedName("mainTarget")
    private List<Object> d;

    public String toString() {
        return "HealthInterventionTarget{mVersion=" + this.b + ", mDataRuleList=" + this.d + "}";
    }
}
