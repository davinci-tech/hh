package com.huawei.hms.iapfull.bean;

/* loaded from: classes9.dex */
public class WebPurchaseInfoRequest {
    private String appId;
    private String merchantId;
    private long pageNo = 1;
    private String priceType;
    private String productId;
    private String reservedInfor;
    private String sign;
    private String signatureAlgorithm;
    private long ts;

    public void setTs(long j) {
        this.ts = j;
    }

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setReservedInfor(String str) {
        this.reservedInfor = str;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public void setPriceType(String str) {
        this.priceType = str;
    }

    public void setPageNo(long j) {
        this.pageNo = j;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public long getTs() {
        return this.ts;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public String getSign() {
        return this.sign;
    }

    public String getReservedInfor() {
        return this.reservedInfor;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getPriceType() {
        return this.priceType;
    }

    public long getPageNo() {
        return this.pageNo;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getAppId() {
        return this.appId;
    }
}
