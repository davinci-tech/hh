package com.huawei.route;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import java.util.List;

/* loaded from: classes6.dex */
public class GetRouteDetailRsp extends CloudCommonReponse {

    @SerializedName("routeDatas")
    private List<RouteData> routeDatas;

    @SerializedName("version")
    private long version;

    public List<RouteData> getRouteDatas() {
        return this.routeDatas;
    }

    public void setRouteDatas(List<RouteData> list) {
        this.routeDatas = list;
    }

    public long getVersion() {
        return this.version;
    }

    public void setVersion(long j) {
        this.version = j;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("GetRouteDetailRsp{routeDatas=");
        sb.append(this.routeDatas);
        sb.append('}');
        return sb.toString();
    }
}
