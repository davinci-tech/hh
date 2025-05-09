package defpackage;

import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo;
import com.huawei.health.plan.model.intplan.PlanMetaInfoBean;

/* loaded from: classes3.dex */
public class epq implements PlanMetaInfo {

    /* renamed from: a, reason: collision with root package name */
    private String f12149a;
    private String b;
    private int c;
    private String d;
    private int e;
    private String f;
    private String g;
    private String h;
    private int i = 0;
    private int j = 0;
    private IntPlan.PlanType k;
    private String l;
    private String m;
    private int n;
    private String o;
    private int t;

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public IntPlan.PlanType getPlanType() {
        return this.k;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public String getPlanId() {
        return this.m;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public int getPlanStatus() {
        return this.n;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public String getPlanTempId() {
        return this.l;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public String getName() {
        return this.g;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public String getPicture() {
        return this.h;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public String getCardImage() {
        return this.f12149a;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public String getDescription() {
        return this.b;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public int getExeDayNum() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public String getExeDays() {
        return this.d;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public String getVersion() {
        return this.o;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public int getDayCount() {
        return this.e;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public int getWeekCount() {
        return this.t;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public int getPlanCategory() {
        return this.i;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public int getDisplayStyle() {
        return this.j;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo
    public String getOpenPageBigImage() {
        return this.f;
    }

    public void a(IntPlan.PlanType planType) {
        this.k = planType;
    }

    public void h(String str) {
        this.m = str;
    }

    public void c(int i) {
        this.n = i;
    }

    public void i(String str) {
        this.l = str;
    }

    public void e(String str) {
        this.g = str;
    }

    public void f(String str) {
        this.h = str;
    }

    public void a(String str) {
        this.f12149a = str;
    }

    public void d(String str) {
        this.b = str;
    }

    public void a(int i) {
        this.c = i;
    }

    public void c(String str) {
        this.d = str;
    }

    public void e(int i) {
        this.e = i;
    }

    public void i(int i) {
        this.t = i;
    }

    public void d(int i) {
        this.i = i;
    }

    public void b(int i) {
        this.j = i;
    }

    public void b(String str) {
        this.f = str;
    }

    public epq e(PlanMetaInfoBean planMetaInfoBean) {
        if (planMetaInfoBean != null) {
            d(planMetaInfoBean.getDescription());
            a(planMetaInfoBean.getExeDayNum());
            c(planMetaInfoBean.getExeDays());
            e(planMetaInfoBean.getName());
            h(planMetaInfoBean.getPlanId());
            i(planMetaInfoBean.getPlanTempId());
            a(IntPlan.PlanType.getPlanType(planMetaInfoBean.getPlanType()));
            c(planMetaInfoBean.getPlanStatus());
            f(planMetaInfoBean.getPicture());
            a(planMetaInfoBean.getCardImage());
            b(planMetaInfoBean.getOpenPageBigImage());
        }
        return this;
    }
}
