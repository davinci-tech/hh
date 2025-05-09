package com.huawei.watchface.mvp.model.info;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.api.entity.pay.WithholdRequest;
import com.huawei.hms.support.api.entity.pay.internal.BaseReq;
import com.huawei.watchface.ae;
import com.huawei.watchface.an;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import java.util.Locale;

/* loaded from: classes9.dex */
public class VipPayInfo extends ae {
    private static final String TAG = "VipPayInfo";
    private String amount;
    private String applicationID;
    private String country;
    private String currency;
    private String extReserved;
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
    private String tradeType;
    private String url;

    @SerializedName(HwPayConstant.KEY_URLVER)
    private String urlVersion;

    public String getReservedInfor() {
        return this.reservedInfor;
    }

    public void setReservedInfor(String str) {
        this.reservedInfor = str;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public String getSdkChannel() {
        return this.sdkChannel;
    }

    public void setSdkChannel(String str) {
        this.sdkChannel = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getApplicationID() {
        return this.applicationID;
    }

    public void setApplicationID(String str) {
        this.applicationID = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String getSignPss() {
        return this.signPss;
    }

    public void setSignPss(String str) {
        this.signPss = str;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public String getUrlVersion() {
        return this.urlVersion;
    }

    public void setUrlVersion(String str) {
        this.urlVersion = str;
    }

    public String getServiceCatalog() {
        return this.serviceCatalog;
    }

    public void setServiceCatalog(String str) {
        this.serviceCatalog = str;
    }

    public String getTradeType() {
        return this.tradeType;
    }

    public void setTradeType(String str) {
        this.tradeType = str;
    }

    public String getExtReserved() {
        return this.extReserved;
    }

    public void setExtReserved(String str) {
        this.extReserved = str;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public void setMerchantName(String str) {
        this.merchantName = str;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }

    public BaseReq getPayInfoReq(VipPayInfo vipPayInfo) {
        Double d;
        VipPayInfoReq vipPayInfoReq = new VipPayInfoReq();
        if (vipPayInfo == null) {
            return vipPayInfoReq;
        }
        if (!an.b(vipPayInfo.getSdkChannel())) {
            try {
                vipPayInfoReq.sdkChannel = Integer.parseInt(vipPayInfo.getSdkChannel());
            } catch (NumberFormatException e) {
                HwLog.i(TAG, "NumberFormatException: " + HwLog.printException((Exception) e));
            }
        }
        if (!an.b(vipPayInfo.getCountry())) {
            vipPayInfoReq.country = vipPayInfo.getCountry();
        }
        if (!an.b(vipPayInfo.getUrlVersion())) {
            vipPayInfoReq.urlVer = vipPayInfo.getUrlVersion();
        }
        if (!an.b(vipPayInfo.getTradeType())) {
            vipPayInfoReq.setTradeType(vipPayInfo.getTradeType());
        }
        if (!an.b(vipPayInfo.getUrl())) {
            vipPayInfoReq.url = vipPayInfo.getUrl();
        }
        if (!an.b(vipPayInfo.getExtReserved())) {
            vipPayInfoReq.extReserved = vipPayInfo.getExtReserved();
        }
        if (!an.b(vipPayInfo.getCurrency())) {
            vipPayInfoReq.currency = vipPayInfo.getCurrency();
        }
        vipPayInfoReq.productName = vipPayInfo.getProductName();
        vipPayInfoReq.productDesc = vipPayInfo.getProductDesc();
        vipPayInfoReq.merchantId = vipPayInfo.getMerchantId();
        vipPayInfoReq.applicationID = vipPayInfo.getApplicationID();
        if (!TextUtils.isEmpty(vipPayInfo.getAmount())) {
            try {
                d = Double.valueOf(vipPayInfo.getAmount());
            } catch (NumberFormatException e2) {
                HwLog.i(TAG, "NumberFormatException " + HwLog.printException((Exception) e2));
                d = null;
            }
            vipPayInfoReq.amount = String.format(Locale.ENGLISH, "%.2f", d);
        }
        vipPayInfoReq.requestId = vipPayInfo.getRequestId();
        vipPayInfoReq.merchantName = vipPayInfo.getMerchantName();
        vipPayInfoReq.serviceCatalog = vipPayInfo.getServiceCatalog();
        if (!TextUtils.isEmpty(vipPayInfo.getSignPss()) && MobileInfoHelper.getAppVersionCode("com.huawei.hwid") > 60000300) {
            vipPayInfoReq.signatureAlgorithm = "SHA256WithRSA/PSS";
            vipPayInfoReq.sign = vipPayInfo.getSignPss();
        } else {
            vipPayInfoReq.sign = vipPayInfo.getSign();
        }
        vipPayInfoReq.reservedInfor = vipPayInfo.reservedInfor;
        return vipPayInfoReq;
    }

    public BaseReq getWithholdRequest(VipPayInfo vipPayInfo) {
        Double d;
        WithholdRequest withholdRequest = new WithholdRequest();
        if (vipPayInfo == null) {
            return withholdRequest;
        }
        withholdRequest.merchantId = vipPayInfo.getMerchantId();
        withholdRequest.applicationID = vipPayInfo.getApplicationID();
        withholdRequest.requestId = vipPayInfo.getRequestId();
        if (!an.b(vipPayInfo.getUrlVersion())) {
            withholdRequest.urlVer = vipPayInfo.getUrlVersion();
        }
        if (!an.b(vipPayInfo.getSdkChannel())) {
            try {
                withholdRequest.sdkChannel = Integer.parseInt(vipPayInfo.getSdkChannel());
            } catch (NumberFormatException e) {
                HwLog.i(TAG, "NumberFormatException: " + HwLog.printException((Exception) e));
            }
        }
        if (!TextUtils.isEmpty(vipPayInfo.getSignPss()) && MobileInfoHelper.getAppVersionCode("com.huawei.hwid") > 60000300) {
            withholdRequest.signatureAlgorithm = "SHA256WithRSA/PSS";
            withholdRequest.sign = vipPayInfo.getSignPss();
        } else {
            withholdRequest.sign = vipPayInfo.getSign();
        }
        withholdRequest.merchantName = vipPayInfo.getMerchantName();
        withholdRequest.serviceCatalog = vipPayInfo.getServiceCatalog();
        if (!an.b(vipPayInfo.getExtReserved())) {
            withholdRequest.extReserved = vipPayInfo.getExtReserved();
        }
        withholdRequest.productName = vipPayInfo.getProductName();
        withholdRequest.productDesc = vipPayInfo.getProductDesc();
        if (!TextUtils.isEmpty(vipPayInfo.getAmount())) {
            try {
                d = Double.valueOf(vipPayInfo.getAmount());
            } catch (NumberFormatException e2) {
                HwLog.i(TAG, "NumberFormatException " + HwLog.printException((Exception) e2));
                d = null;
            }
            withholdRequest.amount = String.format(Locale.ENGLISH, "%.2f", d);
        }
        if (!an.b(vipPayInfo.getCurrency())) {
            withholdRequest.currency = vipPayInfo.getCurrency();
        }
        if (!an.b(vipPayInfo.getCountry())) {
            withholdRequest.country = vipPayInfo.getCountry();
        }
        if (!an.b(vipPayInfo.getTradeType())) {
            withholdRequest.tradeType = vipPayInfo.getTradeType();
        }
        withholdRequest.url = vipPayInfo.getUrl();
        withholdRequest.reservedInfor = vipPayInfo.reservedInfor;
        return withholdRequest;
    }
}
