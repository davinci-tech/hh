package defpackage;

import com.huawei.health.plan.model.data.intelligentplan.IntelligentPlanDataApi;

/* loaded from: classes3.dex */
public class eun {
    private static volatile IntelligentPlanDataApi d;

    private eun() {
    }

    public static IntelligentPlanDataApi a() {
        if (d == null) {
            synchronized (eun.class) {
                if (d == null) {
                    d = new euq();
                }
            }
        }
        return d;
    }
}
