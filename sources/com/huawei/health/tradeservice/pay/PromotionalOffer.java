package com.huawei.health.tradeservice.pay;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class PromotionalOffer {

    @SerializedName("purchaseDiscountInfo")
    private String purchaseDiscountInfo;

    @SerializedName("purchaseDiscountSignature")
    private String purchaseDiscountSignature;

    public String getPurchaseDiscountInfo() {
        return this.purchaseDiscountInfo;
    }

    public void setPurchaseDiscountInfo(String str) {
        this.purchaseDiscountInfo = str;
    }

    public String getPurchaseDiscountSignature() {
        return this.purchaseDiscountSignature;
    }

    public void setPurchaseDiscountSignature(String str) {
        this.purchaseDiscountSignature = str;
    }

    public String toString() {
        return new GsonBuilder().disableHtmlEscaping().create().toJson(this);
    }
}
