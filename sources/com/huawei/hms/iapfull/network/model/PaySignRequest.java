package com.huawei.hms.iapfull.network.model;

import android.text.TextUtils;
import com.huawei.hms.iapfull.bean.PayRequest;

/* loaded from: classes4.dex */
public class PaySignRequest extends PayRequest {
    private String accessMode;
    private String at;
    private String channel;
    private String cpSignType;
    private String deviceBrand;
    private String manufacturer;
    private String merName;
    private String noiseTamp;
    private String scene;
    private String serialNo;
    private String signType;
    private String time;
    private String userID;
    private String userName;
    private String walletAppId;
    private String weburl = "";

    public void setWeburl(String str) {
        this.weburl = str;
    }

    public void setWalletAppId(String str) {
        this.walletAppId = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public void setSignType(String str) {
        this.signType = str;
    }

    public void setSerialNo(String str) {
        this.serialNo = str;
    }

    public void setScene(String str) {
        this.scene = str;
    }

    public void setNoiseTamp(String str) {
        this.noiseTamp = str;
    }

    public void setMerName(String str) {
        this.merName = str;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public void setDeviceBrand(String str) {
        this.deviceBrand = str;
    }

    public void setCpSignType(String str) {
        this.cpSignType = str;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public void setAt(String str) {
        this.at = str;
    }

    public void setAccessMode(String str) {
        this.accessMode = str;
    }

    public String getWeburl() {
        return this.weburl;
    }

    public String getWalletAppId() {
        return this.walletAppId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getTime() {
        return this.time;
    }

    public String getSignType() {
        return this.signType;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public String getScene() {
        return this.scene;
    }

    public String getNoiseTamp() {
        return this.noiseTamp;
    }

    public String getMerName() {
        return this.merName;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getDeviceBrand() {
        return this.deviceBrand;
    }

    public String getCpSignType() {
        return this.cpSignType;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getAt() {
        return this.at;
    }

    public String getAccessMode() {
        return this.accessMode;
    }

    public void checkCountryAndCurrency() {
        if (TextUtils.isEmpty(this.country)) {
            this.country = "CN";
        }
        if (TextUtils.isEmpty(this.currency)) {
            this.currency = "CNY";
        }
    }

    public PaySignRequest(PayRequest payRequest) {
        this.productName = payRequest.getProductName();
        this.productDesc = payRequest.getProductDesc();
        this.applicationID = payRequest.getApplicationID();
        this.requestId = payRequest.getRequestId();
        this.amount = payRequest.getAmount();
        this.merchantId = payRequest.getMerchantId();
        this.merchantName = payRequest.getMerchantName();
        this.sdkChannel = payRequest.getSdkChannel();
        this.url = payRequest.getUrl();
        this.currency = payRequest.getCurrency();
        this.country = payRequest.getCountry();
        this.extReserved = payRequest.getExtReserved();
        this.sign = payRequest.getSign();
        if (!payRequest.isSignatureAlgorithmEmpty()) {
            this.cpSignType = payRequest.getUsedSignType();
        }
        this.serviceCatalog = payRequest.getServiceCatalog();
        this.urlver = payRequest.getUrlver();
        this.tradeType = payRequest.getTradeType();
    }

    public PaySignRequest() {
    }
}
