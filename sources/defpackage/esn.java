package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esn implements IRequest {

    @SerializedName("userFitnessPlanInfo")
    private UserFitnessPlanInfo b;

    private esn(a aVar) {
        this.b = aVar.f12228a;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bv();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private UserFitnessPlanInfo f12228a;

        public a b(UserFitnessPlanInfo userFitnessPlanInfo) {
            this.f12228a = userFitnessPlanInfo;
            return this;
        }

        public esn a() {
            return new esn(this);
        }
    }
}
