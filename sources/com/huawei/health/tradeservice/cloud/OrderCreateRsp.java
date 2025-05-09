package com.huawei.health.tradeservice.cloud;

/* loaded from: classes4.dex */
public class OrderCreateRsp extends BaseRsp {
    private String orderCode;
    private String productId;
    private String purchaseExtension;
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

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String str) {
        this.orderCode = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }
}
