package defpackage;

import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.health.plan.model.intplan.IntActionBean;

/* loaded from: classes3.dex */
public class epm implements IntAction {

    /* renamed from: a, reason: collision with root package name */
    private mnt f12145a;
    private int b;
    private String c;
    private int d;
    private IntAction.ActionType e;
    private long g;
    private String i;
    private long j;

    @Override // com.huawei.basefitnessadvice.model.intplan.IntAction
    public mnt getActionInfo() {
        return this.f12145a;
    }

    public void a(mnt mntVar) {
        this.f12145a = mntVar;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntAction
    public long getTimePeriod() {
        return this.g;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntAction
    public IntAction.ActionType getActionType() {
        return this.e;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntAction
    public String getActionId() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntAction
    public int getPunchFlag() {
        return this.b;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntAction
    public long getPunchTime() {
        return this.j;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntAction
    public String getRecordId() {
        return this.i;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.IntAction
    public int getFeedback() {
        return this.d;
    }

    public void b(long j) {
        this.g = j;
    }

    public void e(IntAction.ActionType actionType) {
        this.e = actionType;
    }

    public void e(int i) {
        this.b = i;
    }

    public void e(long j) {
        this.j = j;
    }

    public void e(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.i = str;
    }

    public void c(int i) {
        this.d = i;
    }

    public epm d(IntActionBean intActionBean) {
        if (intActionBean != null) {
            e(intActionBean.getActionId());
            e(IntAction.ActionType.getActionType(intActionBean.getActionType()));
            e(intActionBean.getPunchFlag());
            e(intActionBean.getPunchTime());
            c(intActionBean.getRecordId());
            c(intActionBean.getFeedback());
            a(intActionBean.getActionInfo());
        }
        return this;
    }
}
