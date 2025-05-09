package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes4.dex */
public class ReceiveCouponReq implements IRequest {

    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private int clientType;

    @SerializedName("clientVersion")
    private String clientVersion;

    @SerializedName("couponIdList")
    private List<String> couponIdList;

    @SerializedName("territory")
    private String territory;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/coupons-batch-accept";
    }

    public List<String> getCouponIdList() {
        return this.couponIdList;
    }

    public String getTerritory() {
        return this.territory;
    }

    public int getClientType() {
        return this.clientType;
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public void setCouponIdList(List<String> list) {
        this.couponIdList = list;
    }

    public void setTerritory(String str) {
        this.territory = str;
    }

    public void setClientType(int i) {
        this.clientType = i;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }
}
