package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes7.dex */
public class AssetMsgVisitedReq implements IRequest {

    @SerializedName("messageIds")
    private List<String> messageIds;

    public List<String> getMessageIds() {
        return this.messageIds;
    }

    public void setMessageIds(List<String> list) {
        this.messageIds = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/assetMessages/visited";
    }
}
