package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class aul extends auh {

    @SerializedName("healthLifeChallengeBeans")
    private List<drx> e;

    public List<drx> b() {
        return this.e;
    }

    @Override // defpackage.auh
    public String toString() {
        return "HealthModelChallengesResponse{mHealthLifeChallengeBeans=" + this.e + "}";
    }
}
