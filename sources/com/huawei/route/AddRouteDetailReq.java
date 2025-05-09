package com.huawei.route;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes6.dex */
public class AddRouteDetailReq implements IRequest {

    @SerializedName("routeData")
    private RouteData routeData;

    @SerializedName("routeDatas")
    private List<RouteData> routeDatas;

    @SerializedName("type")
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public RouteData getRouteData() {
        return this.routeData;
    }

    public void setRouteData(RouteData routeData) {
        this.routeData = routeData;
    }

    public List<RouteData> getRouteDatas() {
        return this.routeDatas;
    }

    public void setRouteDatas(List<RouteData> list) {
        this.routeDatas = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("AddRouteDetailReq{type=");
        sb.append(this.type);
        sb.append(", routeData=");
        sb.append(this.routeData);
        sb.append('}');
        return sb.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/trackRoute/addTrackRoute";
    }
}
