package com.huawei.hms.support.api.entity.pay;

import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: classes9.dex */
public class PurchaseInfoInnerRequest extends PurchaseInfoRequest {

    @Packed
    private String hmsSdkVersionName;

    public void setHmsSdkVersionName(String str) {
        this.hmsSdkVersionName = str;
    }

    public String getHmsSdkVersionName() {
        return this.hmsSdkVersionName;
    }

    public PurchaseInfoInnerRequest(PurchaseInfoRequest purchaseInfoRequest) {
        setSign(purchaseInfoRequest.getSign());
        setTs(purchaseInfoRequest.getTs());
        setPageNo(purchaseInfoRequest.getPageNo());
        setPriceType(purchaseInfoRequest.getPriceType());
        setAppId(purchaseInfoRequest.getAppId());
        setMerchantId(purchaseInfoRequest.getMerchantId());
        setProductId(purchaseInfoRequest.getProductId());
        this.hmsSdkVersionName = "6.13.0.300";
    }

    public PurchaseInfoInnerRequest() {
        this.hmsSdkVersionName = "6.13.0.300";
    }
}
