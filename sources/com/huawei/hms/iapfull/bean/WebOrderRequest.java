package com.huawei.hms.iapfull.bean;

/* loaded from: classes9.dex */
public class WebOrderRequest {
    private String keyType;
    private String merchantId;
    private String requestId;
    private String reservedInfor;
    private String sign;
    private String signatureAlgorithm;
    private String time;

    public void setTime(String str) {
        this.time = str;
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

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public void setKeyType(String str) {
        this.keyType = str;
    }

    public String getTime() {
        return this.time;
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

    public String getRequestId() {
        return this.requestId;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getKeyType() {
        return this.keyType;
    }
}
