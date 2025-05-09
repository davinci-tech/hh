package com.huawei.health.tradeservice.cloud;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class AssetMsgNotifyReq implements IRequest {

    @SerializedName("messageType")
    private int messageType;

    @SerializedName("territory")
    private String territory;

    public String getTerritory() {
        return this.territory;
    }

    public void setTerritory(String str) {
        this.territory = str;
    }

    public void setMessageType(int i) {
        this.messageType = i;
    }

    public int getMessageType() {
        return this.messageType;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/assetMessages";
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
