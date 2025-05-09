package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class cbj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("reminderPlans")
    private List<cbg> f594a;

    public List<cbg> e() {
        return this.f594a;
    }

    public void d(List<cbg> list) {
        this.f594a = list;
    }

    public String toString() {
        return "BloodPressureMeasurePlan{mBloodPressureAlarmInfoCloudList=" + this.f594a + "}";
    }
}
