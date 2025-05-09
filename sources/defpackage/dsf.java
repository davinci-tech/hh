package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes3.dex */
public class dsf implements IRequest {

    @SerializedName("idList")
    private List<drz> b;

    public dsf(List<drz> list) {
        this.b = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/getUserSampleConfig";
    }
}
