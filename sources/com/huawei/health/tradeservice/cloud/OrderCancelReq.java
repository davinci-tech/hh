package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class OrderCancelReq implements IRequest {

    @SerializedName("orderCode")
    private String orderCode;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/order/cancel";
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String str) {
        this.orderCode = str;
    }
}
