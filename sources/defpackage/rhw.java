package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import health.compact.a.GRSManager;

/* loaded from: classes7.dex */
public class rhw implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("pageIndex")
    private int f16770a;

    @SerializedName("filterType")
    private int c;

    @SerializedName("serviceId")
    private int d;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int e;

    public rhw(int i, int i2, int i3, int i4) {
        this.f16770a = i;
        this.e = i2;
        this.c = i3;
        this.d = i4;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataRecommend/v1/labelConfig/queryUserLabelConfig";
    }
}
