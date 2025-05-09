package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class OrderReportResultReq implements IRequest {

    @SerializedName("dataSignature")
    private String dataSignature;

    @SerializedName("orderCode")
    private String orderCode;

    @SerializedName("purchaseData")
    private String purchaseData;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/order/reportPurchaseResult";
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String str) {
        this.orderCode = str;
    }

    public String getPurchaseData() {
        return this.purchaseData;
    }

    public void setPurchaseData(String str) {
        this.purchaseData = str;
    }

    public String getDataSignature() {
        return this.dataSignature;
    }

    public void setDataSignature(String str) {
        this.dataSignature = str;
    }
}
