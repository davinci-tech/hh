package defpackage;

import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.health.plan.model.intplan.DayPlanBean;
import com.huawei.health.plan.model.intplan.IntWeekPlanBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class epp implements IntWeekPlan {

    /* renamed from: a, reason: collision with root package name */
    private String f12148a;
    private String b;
    private List<epl> d = new ArrayList();
    private int e;

    @Override // com.huawei.basefitnessadvice.model.intplan.IntWeekPlan
    public String getWeekDesc() {
        return this.b;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntWeekPlan
    public String getWeekPeriod() {
        return this.f12148a;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntWeekPlan
    public int getWeekOrder() {
        return this.e;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntWeekPlan
    public int getDayCount() {
        return this.d.size();
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntWeekPlan
    public IntDayPlan getDayByIdx(int i) {
        List<epl> list = this.d;
        if (list != null && i >= 0 && i < list.size()) {
            return this.d.get(i);
        }
        return null;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntWeekPlan
    public IntDayPlan getDayByOrder(int i) {
        List<epl> list = this.d;
        if (list == null) {
            return null;
        }
        for (epl eplVar : list) {
            if (eplVar.getDayOrder() == i) {
                return eplVar;
            }
        }
        return null;
    }

    public List<epl> b() {
        return this.d;
    }

    public void e(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.f12148a = str;
    }

    public void d(int i) {
        this.e = i;
    }

    public void d(List<epl> list) {
        this.d = list;
    }

    public epp c(IntWeekPlanBean intWeekPlanBean) {
        if (intWeekPlanBean != null) {
            d(intWeekPlanBean.getWeekOrder());
            e(intWeekPlanBean.getWeekDesc());
            c(intWeekPlanBean.getWeekPeriod());
            e(intWeekPlanBean);
        }
        return this;
    }

    private void e(IntWeekPlanBean intWeekPlanBean) {
        List<DayPlanBean> dayPlans = intWeekPlanBean.getDayPlans();
        if (koq.b(dayPlans)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<DayPlanBean> it = dayPlans.iterator();
        while (it.hasNext()) {
            arrayList.add(new epl().b(it.next()));
        }
        d(arrayList);
    }
}
