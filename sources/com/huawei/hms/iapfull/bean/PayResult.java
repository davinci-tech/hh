package com.huawei.hms.iapfull.bean;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;

/* loaded from: classes4.dex */
public class PayResult {
    private String amount;
    private String country;
    private String currency;
    private String errMsg;
    private String inAppDataSignature;
    private String inAppPurchaseData;
    private long microsAmount;
    private String newSign;
    private String orderID;
    private int payType;
    private String productNo;
    private String requestId;
    private int returnCode;
    private String sign;
    private String signatureAlgorithm;
    private String time;
    private String userName;
    private String withHoldID;

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("returnCode", this.returnCode);
        bundle.putString("orderID", this.orderID);
        bundle.putString(HwPayConstant.KEY_USER_NAME, this.userName);
        bundle.putString(HwPayConstant.KEY_AMOUNT, this.amount);
        if (!TextUtils.isEmpty(this.withHoldID)) {
            bundle.putString("withholdID", this.withHoldID);
        }
        bundle.putString(HwPayConstant.KEY_CURRENCY, this.currency);
        bundle.putString("country", this.country);
        bundle.putString("errMsg", this.errMsg);
        bundle.putString("time", this.time);
        bundle.putString("requestId", this.requestId);
        bundle.putString(HwPayConstant.KEY_SIGN, this.sign);
        bundle.putString("newSign", this.newSign);
        String str = this.signatureAlgorithm;
        if ("RSA256".equals(str)) {
            str = "SHA256WithRSA";
        } else if (HwPayConstant.SIGNTYPE_RSA256PSS.equals(str)) {
            str = "SHA256WithRSA/PSS";
        }
        bundle.putString("signatureAlgorithm", str);
        bundle.putString("inAppPurchaseData", this.inAppPurchaseData);
        bundle.putString("inAppDataSignature", this.inAppDataSignature);
        bundle.putInt("payType", this.payType);
        bundle.putLong("microsAmount", this.microsAmount);
        bundle.putString(HwPayConstant.KEY_PRODUCT_NO, this.productNo);
        bundle.putBoolean("isFullSdk", true);
        return bundle;
    }

    public void setWithHoldID(String str) {
        this.withHoldID = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setReturnCode(String str) {
        try {
            this.returnCode = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            this.returnCode = -1;
        }
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setProductNo(String str) {
        this.productNo = str;
    }

    public void setPayType(int i) {
        this.payType = i;
    }

    public void setOrderID(String str) {
        this.orderID = str;
    }

    public void setNewSign(String str) {
        this.newSign = str;
    }

    public void setMicrosAmount(long j) {
        this.microsAmount = j;
    }

    public void setInAppPurchaseData(String str) {
        this.inAppPurchaseData = str;
    }

    public void setInAppDataSignature(String str) {
        this.inAppDataSignature = str;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public String getWithHoldID() {
        return this.withHoldID;
    }

    public String getUserName() {
        return this.userName;
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

    public int getReturnCode() {
        return this.returnCode;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getProductNo() {
        return this.productNo;
    }

    public int getPayType() {
        return this.payType;
    }

    public String getOrderID() {
        return this.orderID;
    }

    public String getNewSign() {
        return this.newSign;
    }

    public long getMicrosAmount() {
        return this.microsAmount;
    }

    public String getInAppPurchaseData() {
        return this.inAppPurchaseData;
    }

    public String getInAppDataSignature() {
        return this.inAppDataSignature;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getCountry() {
        return this.country;
    }

    public String getAmount() {
        return this.amount;
    }

    public void createPayResult(PayRequest payRequest) {
        if (payRequest == null) {
            this.returnCode = -1;
            return;
        }
        this.userName = payRequest.getMerchantName();
        this.requestId = payRequest.getRequestId();
        this.amount = payRequest.getAmount();
        this.currency = payRequest.getCurrency();
        this.country = payRequest.getCountry();
    }

    public PayResult(int i, String str) {
        this.userName = "";
        this.orderID = "";
        this.currency = "";
        this.country = "";
        this.requestId = "";
        this.amount = "";
        this.time = "";
        this.sign = "";
        this.newSign = "";
        this.withHoldID = "";
        this.returnCode = i;
        this.errMsg = str;
    }

    public PayResult() {
        this.returnCode = -1;
        this.userName = "";
        this.orderID = "";
        this.currency = "";
        this.country = "";
        this.requestId = "";
        this.amount = "";
        this.errMsg = "";
        this.time = "";
        this.sign = "";
        this.newSign = "";
        this.withHoldID = "";
    }
}
