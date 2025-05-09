package com.huawei.hms.iapfull.bean;

/* loaded from: classes9.dex */
public class WebProductDetailRequest {
    private String applicationID;
    private String merchantId;
    private String productNos;
    private String requestId;
    private String reservedInfor;

    public void setReservedInfor(String str) {
        this.reservedInfor = str;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setProductNos(String str) {
        this.productNos = str;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public void setApplicationID(String str) {
        this.applicationID = str;
    }

    public String getReservedInfor() {
        return this.reservedInfor;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getProductNos() {
        return this.productNos;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getApplicationID() {
        return this.applicationID;
    }
}
