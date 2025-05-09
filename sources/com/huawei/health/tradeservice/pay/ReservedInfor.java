package com.huawei.health.tradeservice.pay;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class ReservedInfor {

    @SerializedName("accountInfo")
    private String accountInfo;

    @SerializedName("orderPurchaseExtensionInformation")
    private OrderPurchaseExtensionInformation orderPurchaseExtensionInformation;

    @SerializedName("promotionalOffer")
    private PromotionalOffer promotionalOffer;

    public PromotionalOffer getPromotionalOffer() {
        return this.promotionalOffer;
    }

    public void setPromotionalOffer(PromotionalOffer promotionalOffer) {
        this.promotionalOffer = promotionalOffer;
    }

    public OrderPurchaseExtensionInformation getOrderPurchaseExtensionInformation() {
        return this.orderPurchaseExtensionInformation;
    }

    public void setOrderPurchaseExtensionInformation(OrderPurchaseExtensionInformation orderPurchaseExtensionInformation) {
        this.orderPurchaseExtensionInformation = orderPurchaseExtensionInformation;
    }

    public String getAccountInfo() {
        return this.accountInfo;
    }

    public void setAccountInfo(String str) {
        this.accountInfo = str;
    }

    public String toString() {
        return new GsonBuilder().disableHtmlEscaping().create().toJson(this);
    }
}
