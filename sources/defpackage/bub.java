package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes3.dex */
public class bub implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("followUsers")
    private List<Long> f507a;

    public void d(List<Long> list) {
        this.f507a = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/healthCare/familyCare/deleteFollowUsers";
    }
}
