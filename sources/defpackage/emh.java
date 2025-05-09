package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class emh implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("pathId")
    private String f12092a;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/inner/deleteFeedbackByPathId";
    }

    public String toString() {
        return "DeleteFeedbackByPathIdReq{pathId='" + this.f12092a + "'}";
    }
}
