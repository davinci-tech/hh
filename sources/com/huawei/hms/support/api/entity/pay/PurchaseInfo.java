package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class PurchaseInfo implements IMessageEntity {

    @Packed
    private String appId;

    @Packed
    private String merchantId;

    @Packed
    private String productId;

    @Packed
    private String requestId;

    @Packed
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
