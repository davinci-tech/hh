package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes9.dex */
public class jak implements IRequest {

    @SerializedName("fileList")
    private List<jai> b;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/healthCare/upload/getInfo";
    }

    public void e(List<jai> list) {
        this.b = list;
    }
}
