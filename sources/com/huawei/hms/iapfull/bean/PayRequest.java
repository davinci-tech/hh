package com.huawei.hms.iapfull.bean;

import android.text.TextUtils;
import com.huawei.hms.iapfull.y0;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.secure.android.common.intent.SafeBundle;

/* loaded from: classes4.dex */
public class PayRequest {
    private static final String TAG = "PayRequest";
    public String amount;
    public String applicationID;
    public String country;
    public String currency;
    public String expireTime;
    public String extReserved;
    public String merchantId;
    public String merchantName;
    public String packageName;
    public String productDesc;
    public String productName;
    public String requestId;
    public String reservedInfor;
    public int sdkChannel;
    public String serviceCatalog;
    public String sign;
    public String signatureAlgorithm;
    public String tradeType;
    public String url;
    public String urlver;
    private String usedSignType;

    public void fromBundle(SafeBundle safeBundle) {
        if (safeBundle == null) {
            y0.a(TAG, "bundle is null");
            return;
        }
        this.productName = safeBundle.getString("hw_api_productName");
        this.productDesc = safeBundle.getString("hw_api_productDesc");
        this.applicationID = safeBundle.getString("hw_api_applicationID");
        this.requestId = safeBundle.getString("hw_api_requestId");
        this.amount = safeBundle.getString("hw_api_amount");
        this.merchantId = safeBundle.getString("hw_api_merchantId");
        this.serviceCatalog = safeBundle.getString("hw_api_serviceCatalog");
        this.merchantName = safeBundle.getString("hw_api_merchantName");
        this.sdkChannel = safeBundle.getInt("hw_api_sdkChannel");
        this.url = safeBundle.getString("hw_api_url");
        this.currency = safeBundle.getString("hw_api_currency");
        this.country = safeBundle.getString("hw_api_country");
        this.extReserved = safeBundle.getString("hw_api_extReserved");
        this.sign = safeBundle.getString("hw_api_sign");
        String string = safeBundle.getString("hw_api_signatureAlgorithm");
        this.signatureAlgorithm = string;
        String str = "SHA256WithRSA".equals(string) ? "RSA256" : string;
        if ("SHA256WithRSA/PSS".equals(string)) {
            str = HwPayConstant.SIGNTYPE_RSA256PSS;
        }
        this.usedSignType = str;
        if (TextUtils.isEmpty(str)) {
            y0.b(TAG, "cp signatureAlgorithm is empty");
            this.usedSignType = "RSA256";
        }
        y0.b(TAG, "usedSignType : " + this.usedSignType);
        this.urlver = safeBundle.getString("hw_api_urlver");
        this.reservedInfor = safeBundle.getString("hw_api_reservedInfor");
        this.packageName = safeBundle.getString("hw_api_packageName");
        this.tradeType = safeBundle.getString("hw_api_trade_type");
        this.expireTime = safeBundle.getString("hw_api_expireTime");
    }

    public SafeBundle toBundle() {
        SafeBundle safeBundle = new SafeBundle();
        safeBundle.putString("hw_api_productName", this.productName);
        safeBundle.putString("hw_api_productDesc", this.productDesc);
        safeBundle.putString("hw_api_applicationID", this.applicationID);
        safeBundle.putString("hw_api_requestId", this.requestId);
        safeBundle.putString("hw_api_amount", this.amount);
        safeBundle.putString("hw_api_merchantId", this.merchantId);
        safeBundle.putString("hw_api_serviceCatalog", this.serviceCatalog);
        safeBundle.putString("hw_api_merchantName", this.merchantName);
        safeBundle.putInt("hw_api_sdkChannel", this.sdkChannel);
        safeBundle.putString("hw_api_url", this.url);
        safeBundle.putString("hw_api_currency", this.currency);
        safeBundle.putString("hw_api_country", this.country);
        safeBundle.putString("hw_api_extReserved", this.extReserved);
        safeBundle.putString("hw_api_sign", this.sign);
        safeBundle.putString("hw_api_signatureAlgorithm", this.signatureAlgorithm);
        safeBundle.putString("hw_api_urlver", this.urlver);
        safeBundle.putString("hw_api_packageName", this.packageName);
        safeBundle.putString("hw_api_reservedInfor", this.reservedInfor);
        safeBundle.putString("hw_api_trade_type", this.tradeType);
        safeBundle.putString("hw_api_expireTime", this.expireTime);
        return safeBundle;
    }

    public void setUrlver(String str) {
        this.urlver = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setTradeType(String str) {
        this.tradeType = str;
    }

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setServiceCatalog(String str) {
        this.serviceCatalog = str;
    }

    public void setSdkChannel(int i) {
        this.sdkChannel = i;
    }

    public void setReservedInfor(String str) {
        this.reservedInfor = str;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setMerchantName(String str) {
        this.merchantName = str;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public void setExtReserved(String str) {
        this.extReserved = str;
    }

    public void setExpireTime(String str) {
        this.expireTime = str;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setApplicationID(String str) {
        this.applicationID = str;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public boolean isSignatureAlgorithmEmpty() {
        return TextUtils.isEmpty(this.signatureAlgorithm);
    }

    public String getUsedSignType() {
        return this.usedSignType;
    }

    public String getUrlver() {
        return this.urlver;
    }

    public String getUrl() {
        return this.url;
    }

    public String getTradeType() {
        return this.tradeType;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public String getSign() {
        return this.sign;
    }

    public String getServiceCatalog() {
        return this.serviceCatalog;
    }

    public int getSdkChannel() {
        return this.sdkChannel;
    }

    public String getReservedInfor() {
        return this.reservedInfor;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getExtReserved() {
        return this.extReserved;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getCountry() {
        return this.country;
    }

    public String getApplicationID() {
        return this.applicationID;
    }

    public String getAmount() {
        return this.amount;
    }
}
