package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes5.dex */
public class DeleteRouteDetailReq implements IRequest {

    @SerializedName("routeVersions")
    private List<Long> routeVersions;

    @SerializedName("type")
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public List<Long> getRouteVersions() {
        return this.routeVersions;
    }

    public void setRouteVersions(List<Long> list) {
        this.routeVersions = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("DeleteRouteDetailReq{type=");
        sb.append(this.type);
        sb.append(", routeVersions=");
        sb.append(this.routeVersions);
        sb.append('}');
        return sb.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/trackRoute/deleteTrackRoute";
    }
}
