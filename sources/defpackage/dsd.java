package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class dsd implements IRequest {
    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/healthExpansion/3rdService/getDoctorBasicInfo";
    }
}
