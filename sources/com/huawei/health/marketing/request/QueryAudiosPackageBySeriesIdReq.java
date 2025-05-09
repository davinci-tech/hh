package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class QueryAudiosPackageBySeriesIdReq implements IRequest {

    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private String clientType;

    @SerializedName("ids")
    private String ids;

    public String getIds() {
        return this.ids;
    }

    public void setIds(String str) {
        this.ids = str;
    }

    public String getClientType() {
        return this.clientType;
    }

    public void setClientType(String str) {
        this.clientType = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("messageCenterUrl") + "/messageCenter/user/queryAudiosPackageBySeriesId";
    }
}
