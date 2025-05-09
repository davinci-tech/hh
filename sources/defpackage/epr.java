package defpackage;

import com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo;
import com.huawei.health.plan.model.intplan.PlanTimeInfoBean;

/* loaded from: classes3.dex */
public class epr implements PlanTimeInfo {

    /* renamed from: a, reason: collision with root package name */
    private long f12150a;
    private long b;
    private long c;
    private long d;
    private long e;
    private long g;
    private long j;

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo
    public long getCreateTime() {
        return this.f12150a;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo
    public long getFinishTime() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo
    public long getModifyTime() {
        return this.e;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo
    public long getRemindTime() {
        return this.j;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo
    public long getBeginDate() {
        return this.d;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo
    public long getEndDate() {
        return this.b;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo
    public long getReportTime() {
        return this.g;
    }

    public void d(long j) {
        this.f12150a = j;
    }

    public void a(long j) {
        this.e = j;
    }

    public void b(long j) {
        this.j = j;
    }

    public void c(long j) {
        this.d = j;
    }

    public void e(long j) {
        this.b = j;
    }

    public void g(long j) {
        this.g = j;
    }

    public epr e(PlanTimeInfoBean planTimeInfoBean) {
        if (planTimeInfoBean != null) {
            c(planTimeInfoBean.getBeginDate());
            d(planTimeInfoBean.getCreateTime());
            e(planTimeInfoBean.getEndDate());
            b(planTimeInfoBean.getRemindTime());
            g(planTimeInfoBean.getReportTime());
        }
        return this;
    }
}
