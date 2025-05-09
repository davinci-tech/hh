package com.huawei.route;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes6.dex */
public class GetRouteDetailReq implements IRequest {

    @SerializedName("type")
    private int type;

    @SerializedName("version")
    private long version;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public long getVersion() {
        return this.version;
    }

    public void setVersion(long j) {
        this.version = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("GetRouteDetailReq{type=");
        sb.append(this.type);
        sb.append('}');
        return sb.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/trackRoute/getTrackRouteDetail";
    }

    public String getSingleRouteUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/trackRoute/getTrackRouteDetailByVersion";
    }
}
