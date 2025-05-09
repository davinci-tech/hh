package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class ResourceAuthReq implements IRequest {

    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private int clientType;

    @SerializedName("country")
    private String country;

    @SerializedName("resId")
    private String resId;

    @SerializedName("resType")
    private String resType;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/resource/auth?";
    }

    public int getClientType() {
        return this.clientType;
    }

    public void setClientType(int i) {
        this.clientType = i;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getResType() {
        return this.resType;
    }

    public void setResType(String str) {
        this.resType = str;
    }

    public String getResId() {
        return this.resId;
    }

    public void setResId(String str) {
        this.resId = str;
    }
}
