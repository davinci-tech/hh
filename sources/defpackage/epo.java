package defpackage;

import com.huawei.basefitnessadvice.model.Introduction;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.Goal;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.basefitnessadvice.model.intplan.PlanInputInfo;
import com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo;
import com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo;
import com.huawei.basefitnessadvice.model.intplan.StatInfo;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.health.plan.model.intplan.GoalBean;
import com.huawei.health.plan.model.intplan.IntPlanBean;
import com.huawei.health.plan.model.intplan.IntWeekPlanBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class epo implements IntPlan {

    /* renamed from: a, reason: collision with root package name */
    private String f12147a;
    public epn b;
    public epq c;
    private Plan d;
    public epr e;
    private int h;
    private UserFitnessPlanInfo i;
    private Introduction j;
    private String k;
    private String n;
    private List<epp> o = new ArrayList();
    private Map<String, epj> g = new HashMap();
    private Map<String, epw> m = new HashMap();
    private volatile boolean f = false;

    @Override // com.huawei.operation.utils.TodoTaskInterface
    public int getIconId() {
        return 0;
    }

    @Override // com.huawei.operation.utils.TodoTaskInterface
    public String getProgress() {
        return null;
    }

    private epo() {
    }

    public static epo a() {
        return new epo();
    }

    public static epo a(IntPlanBean intPlanBean) {
        return new epo().b(intPlanBean);
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public IntPlan.PlanType getPlanType() {
        return this.c.getPlanType();
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public String getPlanId() {
        return this.c.getPlanId();
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public String getPlanTempId() {
        return this.c.getPlanTempId();
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public String getTimeZone() {
        return this.k;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public PlanMetaInfo getMetaInfo() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public PlanTimeInfo getPlanTimeInfo() {
        return this.e;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public PlanInputInfo getPlanInputInfo() {
        return this.b;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public IntWeekPlan getWeekInfoByIdx(int i) {
        List<epp> list = this.o;
        if (list != null && i >= 0 && i < list.size()) {
            return this.o.get(i);
        }
        return null;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public IntWeekPlan getWeekInfoByOrder(int i) {
        List<epp> list = this.o;
        if (list == null) {
            return null;
        }
        for (epp eppVar : list) {
            if (eppVar.getWeekOrder() == i) {
                return eppVar;
            }
        }
        return null;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public IntDayPlan getDayInfo(int i, int i2) {
        List<epp> list = this.o;
        if (list == null) {
            return null;
        }
        for (epp eppVar : list) {
            if (eppVar.getWeekOrder() == i) {
                return eppVar.getDayByOrder(i2);
            }
        }
        return null;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public String getWeekReport() {
        return this.n;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public String getFinalReport() {
        return this.f12147a;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public Goal getGoal(String str) {
        return this.g.get(str);
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public StatInfo getStat(String str) {
        return this.m.get(str);
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public Plan getCompatiblePlan() {
        return this.d;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public int getTransactionStatus() {
        return this.h;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntPlan
    public Introduction getIntroduction() {
        return this.j;
    }

    public void d(epq epqVar) {
        this.c = epqVar;
    }

    public void d(epr eprVar) {
        this.e = eprVar;
    }

    public void a(String str) {
        this.k = str;
    }

    public int c() {
        return this.c.getDayCount();
    }

    public List<epp> d() {
        return this.o;
    }

    public void d(List<epp> list) {
        this.o = list;
    }

    public void a(epj epjVar) {
        this.g.put(epjVar.getStatType(), epjVar);
    }

    public void e(epw epwVar) {
        this.m.put(epwVar.getStatType(), epwVar);
    }

    public void b(Plan plan) {
        this.d = plan;
    }

    public void e(int i) {
        this.h = i;
    }

    public void a(Introduction introduction) {
        this.j = introduction;
    }

    public UserFitnessPlanInfo b() {
        return this.i;
    }

    public void b(UserFitnessPlanInfo userFitnessPlanInfo) {
        this.i = userFitnessPlanInfo;
    }

    private epo b(IntPlanBean intPlanBean) {
        if (intPlanBean == null) {
            return this;
        }
        this.h = intPlanBean.getTransactionStatus();
        this.k = intPlanBean.getTimeZone();
        this.c = new epq().e(intPlanBean.getMetaInfo());
        this.b = new epn().b(intPlanBean.getPlanInput());
        this.e = new epr().e(intPlanBean.getTimeInfo());
        e(intPlanBean);
        c(intPlanBean);
        eve.a(this);
        eve.b(intPlanBean.getStats(), this);
        return this;
    }

    private void e(IntPlanBean intPlanBean) {
        List<IntWeekPlanBean> weekPlans = intPlanBean.getWeekPlans();
        if (koq.b(weekPlans)) {
            return;
        }
        this.o = new ArrayList();
        Iterator<IntWeekPlanBean> it = weekPlans.iterator();
        while (it.hasNext()) {
            this.o.add(new epp().c(it.next()));
        }
    }

    private void c(IntPlanBean intPlanBean) {
        List<GoalBean> goals = intPlanBean.getGoals();
        if (koq.b(goals)) {
            return;
        }
        Iterator<GoalBean> it = goals.iterator();
        while (it.hasNext()) {
            a(new epj().b(it.next()));
        }
    }

    public void e() {
        this.f = true;
    }

    public boolean j() {
        return this.f;
    }
}
