package com.huawei.route;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RouteData;

/* loaded from: classes6.dex */
public class GetSingleRouteDetailRsp extends CloudCommonReponse {

    @SerializedName("routeData")
    private RouteData routeData;

    public RouteData getRouteData() {
        return this.routeData;
    }

    public void setRouteData(RouteData routeData) {
        this.routeData = routeData;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("GetSingleRouteDetailRsp{routeData=");
        sb.append(this.routeData);
        sb.append('}');
        return sb.toString();
    }
}
