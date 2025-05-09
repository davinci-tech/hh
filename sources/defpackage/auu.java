package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.healthmodel.bean.HealthLifeStatistic;
import java.util.List;

/* loaded from: classes3.dex */
public class auu extends auh {

    @SerializedName("maxDays")
    private int b;

    @SerializedName("statisticList")
    private List<HealthLifeStatistic> d;

    public int e() {
        return this.b;
    }

    public List<HealthLifeStatistic> b() {
        return this.d;
    }

    @Override // defpackage.auh
    public String toString() {
        return "HealthModelStatResponse{mMaxDays=" + this.b + "mHealthLiftStatisticList=" + this.d + "}";
    }
}
