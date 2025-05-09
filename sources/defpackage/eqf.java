package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.plan.model.intplan.IntPlanBean;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqf implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("intPlan")
    private IntPlanBean f12159a;

    private eqf(e eVar) {
        this.f12159a = eVar.d;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.i();
    }

    public static final class e {
        private IntPlanBean d;

        public e e(IntPlanBean intPlanBean) {
            this.d = intPlanBean;
            return this;
        }

        public eqf d() {
            return new eqf(this);
        }
    }
}
