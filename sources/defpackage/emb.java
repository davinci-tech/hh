package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class emb implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("pathId")
    private String f12090a;

    @SerializedName("motionPathStartTime")
    private long b;

    @SerializedName("minTime")
    private long d;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/app/addHotPathParticipateInfo";
    }

    public String toString() {
        return "AddHotPathParticipateInfoReq{pathId='" + this.f12090a + "', motionPathStartTime=" + this.b + ", minTime=" + this.d + '}';
    }
}
