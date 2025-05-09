package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes7.dex */
public class ikb implements IRequest {

    @SerializedName("halfYearItems")
    private List<String> c;

    @SerializedName("monthItems")
    private List<String> d;

    public void a(List<String> list) {
        this.d = list;
    }

    public void c(List<String> list) {
        this.c = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataStat/healthTrends/getHealthTrend";
    }
}
