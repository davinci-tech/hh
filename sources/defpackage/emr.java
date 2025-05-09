package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class emr implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("pageIndex")
    private int f12099a;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int c;

    @SerializedName("pathId")
    private String e;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/inner/getFeedbackInfos";
    }

    public String toString() {
        return "GetFeedbackInfosReq{pageIndex=" + this.f12099a + ", pageSize=" + this.c + ", pathId='" + this.e + "'}";
    }
}
