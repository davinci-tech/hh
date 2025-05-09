package com.huawei.health.vip.vipinfo;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes4.dex */
public class VipMessageReadReq implements IRequest {
    private static final String VIP_PATH = "/tradeservice/v1/member/messages/visited";

    @SerializedName("messageIds")
    private List<String> messageIds;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + VIP_PATH;
    }

    public List<String> getMessageIds() {
        return this.messageIds;
    }

    public void setMessageIds(List<String> list) {
        this.messageIds = list;
    }
}
