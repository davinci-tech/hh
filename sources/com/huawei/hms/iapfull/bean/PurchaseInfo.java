package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.b1;

/* loaded from: classes9.dex */
public class PurchaseInfo implements b1 {
    private String appId;
    private String merchantId;
    private String productId;
    private String requestId;
    private String tradeTime;

    public void setTradeTime(String str) {
        this.tradeTime = str;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getTradeTime() {
        return this.tradeTime;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getAppId() {
        return this.appId;
    }
}
