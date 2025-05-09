package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.healthmodel.bean.HealthLifeStatistic;
import java.util.List;

/* loaded from: classes3.dex */
public class dsa {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("statisticList")
    private List<HealthLifeStatistic> f11813a;

    @SerializedName("currentDay")
    private int c;

    @SerializedName("maxDays")
    private int e;

    public int b() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public void c(int i) {
        this.e = i;
    }

    public int c() {
        return this.e;
    }

    public void b(List<HealthLifeStatistic> list) {
        this.f11813a = list;
    }

    public List<HealthLifeStatistic> e() {
        return this.f11813a;
    }

    public String toString() {
        return "HealthTaskConsInfo{mMaxDays=" + this.e + " mHealthLiftStatisticList" + this.f11813a + "}";
    }
}
