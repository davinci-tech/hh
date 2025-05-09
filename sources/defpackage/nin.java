package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;

/* loaded from: classes6.dex */
public class nin {

    @SerializedName("effectiveTime")
    private long b = -1;

    @SerializedName("lastEffectiveGoal")
    private int c;

    @SerializedName("effectiveGoal")
    private int d;

    @SerializedName("goalKey")
    private String e;

    public int a() {
        if (d()) {
            return this.d;
        }
        return this.c;
    }

    private boolean d() {
        long j = this.b;
        if (j != -1 && j != 0) {
            return j <= HiDateUtil.f(System.currentTimeMillis());
        }
        LogUtil.h("ActiveGoalModel", "isGoalEffective mEffectiveTime is not init ok");
        return false;
    }

    public String toString() {
        return "ActiveGoalModel{mGoalKey='" + this.e + "', mLastEffectiveGoal=" + this.c + ", mEffectiveGoal=" + this.d + ", mEffectiveTime=" + this.b + '}';
    }
}
