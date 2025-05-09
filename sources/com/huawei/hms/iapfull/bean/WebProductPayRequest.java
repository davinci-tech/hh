package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.y0;
import com.huawei.secure.android.common.intent.SafeBundle;

/* loaded from: classes4.dex */
public class WebProductPayRequest extends BaseWebRequest {
    private static final int PRICE_TYPE_SUBSCRIBE = 2;
    public static final String PRODUCTPAY_BUNDLE_KEY = "hw_api_productpay";
    private static final String TAG = "ProductPayRequest";
    public static final String TYPE_WEB_PRODUCT_PAY = "webpay/productPay";
    private String accessMode;
    private String developerPayload;
    private String expireTime;
    private String extChannel;
    private String extReserved;
    private boolean isWebPayPmsVer4;
    private String merchantId;
    private String merchantName;
    private String packageName;
    private int priceType;
    private String productId;
    private String productNo;
    private String requestId;
    private String returnUrl;
    private int sdkChannel;
    private String serviceCatalog;
    private String sign;
    private String signatureAlgorithm;
    private String systemExtInfo;
    private String terminalType;
    private String url;
    private String urlVer;
    private int validTime;

    public SafeBundle toBundle() {
        SafeBundle safeBundle = new SafeBundle();
        safeBundle.putString("hw_api_clientID", this.clientID);
        safeBundle.putString("hw_api_systemExtInfo", this.systemExtInfo);
        safeBundle.putString("hw_api_productId", this.productId);
        safeBundle.putString("hw_api_developerPayload", this.developerPayload);
        safeBundle.putString("hw_api_accessMode", this.accessMode);
        safeBundle.putString("hw_api_extChannel", this.extChannel);
        safeBundle.putBoolean("hw_api_isWebPayPmsVer4", this.isWebPayPmsVer4);
        safeBundle.putInt("hw_api_priceType", this.priceType);
        safeBundle.putString("hw_api_productNo", this.productNo);
        safeBundle.putInt("hw_api_validTime", this.validTime);
        safeBundle.putString("hw_api_applicationID", this.applicationID);
        safeBundle.putString("hw_api_requestId", this.requestId);
        safeBundle.putString("hw_api_merchantId", this.merchantId);
        safeBundle.putString("hw_api_serviceCatalog", this.serviceCatalog);
        safeBundle.putString("hw_api_merchantName", this.merchantName);
        safeBundle.putInt("hw_api_sdkChannel", this.sdkChannel);
        safeBundle.putString("hw_api_url", this.url);
        safeBundle.putString("hw_api_extReserved", this.extReserved);
        safeBundle.putString("hw_api_sign", this.sign);
        safeBundle.putString("hw_api_urlver", this.urlVer);
        safeBundle.putString("hw_api_packageName", this.packageName);
        safeBundle.putString("hw_api_reservedInfor", this.reservedInfor);
        safeBundle.putString("hw_api_expireTime", this.expireTime);
        safeBundle.putString("hw_api_signatureAlgorithm", this.signatureAlgorithm);
        safeBundle.putString(PRODUCTPAY_BUNDLE_KEY, TYPE_WEB_PRODUCT_PAY);
        return safeBundle;
    }

    public void setWebPayPmsVer4(boolean z) {
        this.isWebPayPmsVer4 = z;
    }

    public void setValidTime(int i) {
        this.validTime = i;
    }

    public void setUrlVer(String str) {
        this.urlVer = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setTerminalType(String str) {
        this.terminalType = str;
    }

    public void setSystemExtInfo(String str) {
        this.systemExtInfo = str;
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

    public void setReturnUrl(String str) {
        this.returnUrl = str;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setProductNo(String str) {
        this.productNo = str;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public void setPriceType(int i) {
        this.priceType = i;
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

    public void setExtChannel(String str) {
        this.extChannel = str;
    }

    public void setExpireTime(String str) {
        this.expireTime = str;
    }

    public void setDeveloperPayload(String str) {
        this.developerPayload = str;
    }

    public void setAccessMode(String str) {
        this.accessMode = str;
    }

    public boolean isWebPayPmsVer4() {
        return this.isWebPayPmsVer4;
    }

    public boolean isSubscribeReq() {
        return this.priceType == 2;
    }

    public int getValidTime() {
        return this.validTime;
    }

    public String getUrlVer() {
        return this.urlVer;
    }

    public String getUrl() {
        return this.url;
    }

    public String getTerminalType() {
        return this.terminalType;
    }

    public String getSystemExtInfo() {
        return this.systemExtInfo;
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

    public String getReturnUrl() {
        return this.returnUrl;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getProductNo() {
        return this.productNo;
    }

    public String getProductId() {
        return this.productId;
    }

    public int getPriceType() {
        return this.priceType;
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

    public String getExtChannel() {
        return this.extChannel;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public String getDeveloperPayload() {
        return this.developerPayload;
    }

    public String getAccessMode() {
        return this.accessMode;
    }

    public void fromBundle(SafeBundle safeBundle) {
        if (safeBundle == null) {
            y0.a(TAG, "bundle is null");
            return;
        }
        this.clientID = safeBundle.getString("hw_api_clientID");
        this.systemExtInfo = safeBundle.getString("hw_api_systemExtInfo");
        this.productId = safeBundle.getString("hw_api_productId");
        this.developerPayload = safeBundle.getString("hw_api_developerPayload");
        this.accessMode = safeBundle.getString("hw_api_accessMode");
        this.extChannel = safeBundle.getString("hw_api_extChannel");
        this.isWebPayPmsVer4 = safeBundle.getBoolean("hw_api_isWebPayPmsVer4", false);
        this.priceType = safeBundle.getInt("hw_api_priceType");
        this.productNo = safeBundle.getString("hw_api_productNo");
        this.validTime = safeBundle.getInt("hw_api_validTime");
        this.applicationID = safeBundle.getString("hw_api_applicationID");
        this.requestId = safeBundle.getString("hw_api_requestId");
        this.merchantId = safeBundle.getString("hw_api_merchantId");
        this.serviceCatalog = safeBundle.getString("hw_api_serviceCatalog");
        this.merchantName = safeBundle.getString("hw_api_merchantName");
        this.sdkChannel = safeBundle.getInt("hw_api_sdkChannel");
        this.url = safeBundle.getString("hw_api_url");
        this.extReserved = safeBundle.getString("hw_api_extReserved");
        this.sign = safeBundle.getString("hw_api_sign");
        this.urlVer = safeBundle.getString("hw_api_urlver");
        this.reservedInfor = safeBundle.getString("hw_api_reservedInfor");
        this.packageName = safeBundle.getString("hw_api_packageName");
        this.expireTime = safeBundle.getString("hw_api_expireTime");
        this.signatureAlgorithm = safeBundle.getString("hw_api_signatureAlgorithm");
    }
}
