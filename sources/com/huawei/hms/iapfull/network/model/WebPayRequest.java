package com.huawei.hms.iapfull.network.model;

import com.huawei.hms.iapfull.bean.PayRequest;

/* loaded from: classes4.dex */
public class WebPayRequest extends PayRequest {
    private String returnUrl;
    private String terminalType;
    private String urlVer;
    private String validTime;

    public void setValidTime(String str) {
        this.validTime = str;
    }

    public void setUrlVer(String str) {
        this.urlVer = str;
    }

    public void setTerminalType(String str) {
        this.terminalType = str;
    }

    public void setReturnUrl(String str) {
        this.returnUrl = str;
    }

    public String getValidTime() {
        return this.validTime;
    }

    public String getUrlVer() {
        return this.urlVer;
    }

    public String getTerminalType() {
        return this.terminalType;
    }

    public String getReturnUrl() {
        return this.returnUrl;
    }

    public WebPayRequest(PayRequest payRequest) {
        this.productName = payRequest.getProductName();
        this.productDesc = payRequest.getProductDesc();
        this.applicationID = payRequest.getApplicationID();
        this.requestId = payRequest.getRequestId();
        this.amount = payRequest.getAmount();
        this.merchantId = payRequest.getMerchantId();
        this.sdkChannel = payRequest.getSdkChannel();
        this.url = payRequest.getUrl();
        this.currency = payRequest.getCurrency();
        this.country = payRequest.getCountry();
        this.extReserved = payRequest.getExtReserved();
        this.serviceCatalog = payRequest.getServiceCatalog();
        this.urlVer = payRequest.getUrlver();
        this.reservedInfor = payRequest.getReservedInfor();
        this.expireTime = payRequest.getExpireTime();
        this.sign = payRequest.getSign();
        this.packageName = payRequest.getPackageName();
        this.signatureAlgorithm = payRequest.getSignatureAlgorithm();
    }
}
