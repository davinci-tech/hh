package com.huawei.watchface.mvp.model.datatype;

/* loaded from: classes9.dex */
public class PayInfo {
    private String amount;
    private String applicationID;
    private String country;
    private String currency;
    private String merchantId;
    private String merchantName;
    private String productDesc;
    private String productName;
    private String publicKey;
    private String requestId;
    private String reservedInfor;
    private String sdkChannel;
    private String serviceCatalog;
    private String sign;
    private String signPss;
    private String startTime;
    private String tradeType;
    private String url;
    private String urlver;
    private String validTime;

    public String getReservedInfor() {
        return this.reservedInfor;
    }

    public void setReservedInfor(String str) {
        this.reservedInfor = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSignPss(String str) {
        this.signPss = str;
    }

    public String getSignPss() {
        return this.signPss;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setMerchantName(String str) {
        this.merchantName = str;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public void setSdkChannel(String str) {
        this.sdkChannel = str;
    }

    public String getSdkChannel() {
        return this.sdkChannel;
    }

    public void setUrlver(String str) {
        this.urlver = str;
    }

    public String getUrlver() {
        return this.urlver;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setServiceCatalog(String str) {
        this.serviceCatalog = str;
    }

    public String getServiceCatalog() {
        return this.serviceCatalog;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setValidTime(String str) {
        this.validTime = str;
    }

    public String getValidTime() {
        return this.validTime;
    }

    public void setApplicationID(String str) {
        this.applicationID = str;
    }

    public String getApplicationID() {
        return this.applicationID;
    }

    public void setTradeType(String str) {
        this.tradeType = str;
    }

    public String getTradeType() {
        return this.tradeType;
    }
}
