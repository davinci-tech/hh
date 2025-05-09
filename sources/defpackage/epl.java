package defpackage;

import com.huawei.basefitnessadvice.model.SuggestedReading;
import com.huawei.basefitnessadvice.model.intplan.Goal;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.basefitnessadvice.model.intplan.StatInfo;
import com.huawei.health.plan.model.intplan.DayPlanBean;
import com.huawei.health.plan.model.intplan.IntActionBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public class epl implements IntDayPlan {
    private int c;
    private SuggestedReading d;
    private int e;
    private int f;
    private long g;

    /* renamed from: a, reason: collision with root package name */
    private List<epm> f12144a = new CopyOnWriteArrayList();
    private List<epm> j = new CopyOnWriteArrayList();
    private List<epv> h = new CopyOnWriteArrayList();
    private Map<String, epw> i = new HashMap();
    private Map<String, epj> b = new HashMap();

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public int getInPlanActionCnt() {
        return this.f12144a.size();
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public IntAction getInPlanAction(int i) {
        List<epm> list = this.f12144a;
        if (list != null && i >= 0 && i < list.size()) {
            return this.f12144a.get(i);
        }
        return null;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public int getOutPlanActionCnt() {
        return this.j.size();
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public IntAction getOutPlanAction(int i) {
        List<epm> list = this.j;
        if (list != null && i >= 0 && i < list.size()) {
            return this.j.get(i);
        }
        return null;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public int getRecordDataCnt() {
        return this.h.size();
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public RecordData getRecordData(int i) {
        List<epv> list = this.h;
        if (list != null && i >= 0 && i < list.size()) {
            return this.h.get(i);
        }
        return null;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public StatInfo getStat(String str) {
        return this.i.get(str);
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public Goal getGoal(String str) {
        return this.b.get(str);
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public int getPunchFlag() {
        return this.f;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public int getDayOrder() {
        return this.e;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public int getDayStatus() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public long getPunchTime() {
        return this.g;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntDayPlan
    public SuggestedReading getSuggestReading() {
        return this.d;
    }

    public List<epm> d() {
        return this.f12144a;
    }

    public List<epm> a() {
        return this.j;
    }

    public void a(long j) {
        this.g = j;
    }

    public void a(int i) {
        this.f = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public void e(int i) {
        this.c = i;
    }

    public void c(List<epm> list) {
        this.f12144a.clear();
        this.f12144a.addAll(list);
    }

    public void a(List<epm> list) {
        this.j.clear();
        this.j.addAll(list);
    }

    public void d(List<epv> list) {
        this.h.clear();
        this.h.addAll(list);
    }

    public void a(epw epwVar) {
        this.i.put(epwVar.getStatType(), epwVar);
    }

    public void c(SuggestedReading suggestedReading) {
        this.d = suggestedReading;
    }

    public epl b(DayPlanBean dayPlanBean) {
        if (dayPlanBean != null) {
            b(dayPlanBean.getDayOrder());
            a(dayPlanBean.getPunchFlag());
            a(dayPlanBean.getPunchTime());
            e(dayPlanBean.getDayStatus());
            d(dayPlanBean);
            a(dayPlanBean);
            e(dayPlanBean);
        }
        return this;
    }

    private void d(DayPlanBean dayPlanBean) {
        List<IntActionBean> scheduledAction = dayPlanBean.getScheduledAction();
        if (koq.b(scheduledAction)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<IntActionBean> it = scheduledAction.iterator();
        while (it.hasNext()) {
            arrayList.add(new epm().d(it.next()));
        }
        c(arrayList);
    }

    private void a(DayPlanBean dayPlanBean) {
        List<IntActionBean> customAction = dayPlanBean.getCustomAction();
        if (koq.b(customAction)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<IntActionBean> it = customAction.iterator();
        while (it.hasNext()) {
            arrayList.add(new epm().d(it.next()));
        }
        a(arrayList);
    }

    private void e(DayPlanBean dayPlanBean) {
        List<mob> recordData = dayPlanBean.getRecordData();
        if (koq.b(recordData)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<mob> it = recordData.iterator();
        while (it.hasNext()) {
            arrayList.add(new epv().c(it.next()));
        }
        d(arrayList);
    }
}
