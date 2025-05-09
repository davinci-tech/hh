package defpackage;

import com.huawei.basefitnessadvice.model.intplan.PlanInputInfo;
import com.huawei.health.plan.model.intplan.PlanInputInfoBean;
import java.util.List;

/* loaded from: classes3.dex */
public class epn implements PlanInputInfo {

    /* renamed from: a, reason: collision with root package name */
    public ept f12146a;
    private List<Integer> b;
    private int c;
    private int d;
    private int e;
    private int f;

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanInputInfo
    public int getGoalType() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanInputInfo
    public List<Integer> getBodyParts() {
        return this.b;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanInputInfo
    public int getExperience() {
        return this.d;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanInputInfo
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public ept getUserInfo() {
        return this.f12146a;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanInputInfo
    public int getIsRunFlag() {
        return this.e;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanInputInfo
    public int getIsSkipRopeFlag() {
        return this.f;
    }

    public void c(int i) {
        this.c = i;
    }

    public void e(List<Integer> list) {
        this.b = list;
    }

    public void d(int i) {
        this.d = i;
    }

    public void a(ept eptVar) {
        this.f12146a = eptVar;
    }

    public void a(int i) {
        this.e = i;
    }

    public void e(int i) {
        this.f = i;
    }

    public epn b(PlanInputInfoBean planInputInfoBean) {
        if (planInputInfoBean != null) {
            d(planInputInfoBean.getExperience());
            e(planInputInfoBean.getBodyParts());
            c(planInputInfoBean.getGoalType());
            a(new ept().e(planInputInfoBean.getUserInfo()));
            a(planInputInfoBean.getIsRunFlag());
            e(planInputInfoBean.getIsSkipRopeFlag());
        }
        return this;
    }
}
