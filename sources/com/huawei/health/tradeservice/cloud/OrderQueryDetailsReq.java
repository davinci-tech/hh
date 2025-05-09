package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class OrderQueryDetailsReq implements IRequest {

    @SerializedName("language")
    private String language;

    @SerializedName("orderCode")
    private String orderCode;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/order/details?";
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String str) {
        this.orderCode = str;
    }
}
