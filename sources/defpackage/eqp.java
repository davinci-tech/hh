package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.plan.model.intplan.IntPlanBean;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqp implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("intPlan")
    private IntPlanBean f12174a;

    @SerializedName("reportType")
    private int d;

    private eqp(a aVar) {
        this.f12174a = aVar.d;
        this.d = aVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.r();
    }

    public static final class a {
        private int c;
        private IntPlanBean d;

        public a c(IntPlanBean intPlanBean) {
            this.d = intPlanBean;
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public eqp e() {
            return new eqp(this);
        }
    }
}
