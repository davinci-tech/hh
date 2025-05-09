package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class aun extends auh {

    @SerializedName("stats")
    private List<dse> c;

    public List<dse> e() {
        return this.c;
    }

    @Override // defpackage.auh
    public String toString() {
        return "HealthModelStatResponse{mStats=" + this.c + "}";
    }
}
