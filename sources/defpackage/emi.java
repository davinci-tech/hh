package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes8.dex */
public class emi implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("huids")
    private List<String> f12093a;

    @SerializedName("pathId")
    private String c;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/inner/deleteFeedbackInfo";
    }

    public String toString() {
        return "DeleteFeedbackInfoReq{pathId='" + this.c + "', huids=" + this.f12093a + '}';
    }
}
