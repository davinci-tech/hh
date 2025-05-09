package com.huawei.health.tradeservice.pay;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class OrderPurchaseExtensionInformation {

    @SerializedName("purchaseExtension")
    private String purchaseExtension;

    @SerializedName("purchaseExtensionSignature")
    private String purchaseExtensionSignature;

    public String getPurchaseExtension() {
        return this.purchaseExtension;
    }

    public void setPurchaseExtension(String str) {
        this.purchaseExtension = str;
    }

    public String getPurchaseExtensionSignature() {
        return this.purchaseExtensionSignature;
    }

    public void setPurchaseExtensionSignature(String str) {
        this.purchaseExtensionSignature = str;
    }

    public String toString() {
        return new GsonBuilder().disableHtmlEscaping().create().toJson(this);
    }
}
