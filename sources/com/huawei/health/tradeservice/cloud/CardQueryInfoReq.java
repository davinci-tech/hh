package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class CardQueryInfoReq implements IRequest {

    @SerializedName("cardPoolId")
    private String cardPoolId;

    @SerializedName("language")
    private String language;

    @SerializedName("orderCode")
    private String orderCode;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/thirdcard/information?";
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getCardPoolId() {
        return this.cardPoolId;
    }

    public void setCardPoolId(String str) {
        this.cardPoolId = str;
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String str) {
        this.orderCode = str;
    }

    public String toString() {
        return "CardQueryInfoReq{language='" + this.language + "', cardPoolId='" + this.cardPoolId + "', orderCode='" + this.orderCode + "'}";
    }
}
