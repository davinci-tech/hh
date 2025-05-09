package defpackage;

import com.huawei.basefitnessadvice.model.intplan.Goal;
import com.huawei.health.plan.model.intplan.GoalBean;

/* loaded from: classes3.dex */
public class epj implements Goal {

    /* renamed from: a, reason: collision with root package name */
    private Object f12143a;
    private Object b;
    private Object c;
    private String d;
    private Object e;

    @Override // com.huawei.basefitnessadvice.model.intplan.Goal
    public String getStatType() {
        return this.d;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.Goal
    public Object getGoalSrc() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.Goal
    public Object getGoalDst() {
        return this.e;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.Goal
    public Object getGoalEstimate() {
        return this.f12143a;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.Goal
    public Object getGoalActual() {
        return this.b;
    }

    public void e(String str) {
        this.d = str;
    }

    public void b(Object obj) {
        this.e = obj;
    }

    public epj b(GoalBean goalBean) {
        if (goalBean != null) {
            e(goalBean.getType());
            b(goalBean.getValue());
        }
        return this;
    }
}
