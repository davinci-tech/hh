package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.Set;

/* loaded from: classes3.dex */
public class dxj implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("serviceId")
    private int f11883a = 0;

    @SerializedName("dataTypeNames")
    private Set<String> b;

    public void e(Set<String> set) {
        this.b = set;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthRecommendUrl") + "/dataRecommend/userlabel/queryLabelRuleByService";
    }
}
