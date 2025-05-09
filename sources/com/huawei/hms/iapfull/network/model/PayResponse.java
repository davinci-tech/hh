package com.huawei.hms.iapfull.network.model;

import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwidauth.datatype.DeviceInfo;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class PayResponse extends BaseResponse implements Serializable, Cloneable {
    public static final String STATUS_FAIL = "0";
    public static final String STATUS_HANDING = "3";
    public static final String STATUS_SUCCESS = "1";
    private static final long serialVersionUID = -3193282326057659181L;
    protected String amount;
    private String bankName;
    private String channel;
    private String clientCacheTime;
    private String countryCode;
    protected String currency;
    private String deviceId;
    private String deviceType;
    private String errCode;
    private String errMsg;
    private String grantID;
    private boolean isMoreQueryTime;
    private boolean isServerAnswer;
    private String merchantId;
    private String newSign;
    private String offsetHour;
    protected String orderNo;
    private String orderTime;
    private String payResultJson;
    protected String realPaidAmt;
    private String relatedStatus;
    protected String requestId;
    private String sign;
    private String status;
    private String tradeNo;
    protected String tradeTime;

    protected void udpate(PayResponse payResponse) {
        if (payResponse == null) {
            return;
        }
        this.returnCode = payResponse.returnCode;
        this.returnDesc = payResponse.returnDesc;
        this.status = payResponse.status;
        this.errCode = payResponse.errCode;
        this.errMsg = payResponse.errMsg;
        this.amount = payResponse.amount;
        this.realPaidAmt = payResponse.realPaidAmt;
        this.requestId = payResponse.requestId;
        this.orderNo = payResponse.orderNo;
        this.tradeNo = payResponse.tradeNo;
        this.deviceId = payResponse.deviceId;
        this.deviceType = payResponse.deviceType;
        this.orderTime = payResponse.orderTime;
        this.tradeTime = payResponse.tradeTime;
        this.bankName = payResponse.bankName;
        this.channel = payResponse.channel;
        this.grantID = payResponse.grantID;
        this.currency = payResponse.currency;
        this.isServerAnswer = payResponse.isServerAnswer;
        this.sign = payResponse.sign;
        this.relatedStatus = payResponse.relatedStatus;
        this.countryCode = payResponse.countryCode;
        this.clientCacheTime = payResponse.clientCacheTime;
        this.merchantId = payResponse.merchantId;
        this.offsetHour = payResponse.offsetHour;
    }

    public void setTradeTime(String str) {
        this.tradeTime = str;
    }

    public void setTradeNo(String str) {
        this.tradeNo = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setRelatedStatus(String str) {
        this.relatedStatus = str;
    }

    public void setRealPaidAmt(String str) {
        this.realPaidAmt = str;
    }

    public void setPayResultJson(String str) {
        this.payResultJson = str;
    }

    public void setPayRespRequestId(String str) {
        this.requestId = str;
    }

    public void setOrderTime(String str) {
        this.orderTime = str;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }

    public void setOffsetHour(String str) {
        this.offsetHour = str;
    }

    public void setNewSign(String str) {
        this.newSign = str;
    }

    public void setMoreQueryTime(boolean z) {
        this.isMoreQueryTime = z;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public void setGrantID(String str) {
        this.grantID = str;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public void setErrCode(String str) {
        this.errCode = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setClientCacheTime(String str) {
        this.clientCacheTime = str;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void parsePayResultQuery(String str) throws JSONException {
        setPayResultJson(str);
        JSONObject jSONObject = new JSONObject(str);
        this.returnCode = jSONObject.optString("returnCode");
        this.returnDesc = jSONObject.optString("returnDesc");
        this.errCode = jSONObject.optString("errCode");
        this.tradeTime = jSONObject.optString("tradeTime");
        this.offsetHour = jSONObject.optString("offsetHour");
        this.amount = jSONObject.optString(HwPayConstant.KEY_AMOUNT);
        this.realPaidAmt = jSONObject.optString("realPaidAmt");
        this.requestId = jSONObject.optString("requestId");
        this.orderNo = jSONObject.optString("orderID");
        this.grantID = jSONObject.optString("grantID");
        this.tradeNo = jSONObject.optString("tradeNo");
        this.deviceId = jSONObject.optString(DeviceInfo.TAG_DEVICE_ID);
        this.deviceType = jSONObject.optString("deviceType");
        this.orderTime = jSONObject.optString("orderTime");
        this.channel = jSONObject.optString("channel");
        this.status = jSONObject.optString("status");
        this.sign = jSONObject.optString(HwPayConstant.KEY_SIGN);
        this.newSign = jSONObject.optString("newSign");
        this.currency = jSONObject.optString(HwPayConstant.KEY_CURRENCY);
        this.relatedStatus = jSONObject.optString("relatedStatus");
    }

    public boolean isPaySuccess() {
        return "0".equals(this.returnCode) && "1".equals(this.status);
    }

    public boolean isMoreQueryTime() {
        return this.isMoreQueryTime;
    }

    public boolean isAmountEmpty() {
        return TextUtils.isEmpty(this.amount);
    }

    public String getTradeTime() {
        return this.tradeTime;
    }

    public String getTradeNo() {
        return this.tradeNo;
    }

    public String getStatus() {
        return this.status;
    }

    public String getSign() {
        return this.sign;
    }

    public String getRelatedStatus() {
        return this.relatedStatus;
    }

    public String getRealPaidAmt() {
        return this.realPaidAmt;
    }

    public String getPayResultJson() {
        return this.payResultJson;
    }

    public String getPayRespRequestId() {
        return this.requestId;
    }

    public String getOrderTime() {
        return this.orderTime;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public String getOffsetHour() {
        return this.offsetHour;
    }

    public String getNewSign() {
        return this.newSign;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getGrantID() {
        return this.grantID;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getClientCacheTime() {
        return this.clientCacheTime;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getAmountOfNoDot() {
        return isAmountEmpty() ? this.amount : this.amount.replace(".", "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public PayResponse mo607clone() throws CloneNotSupportedException {
        return (PayResponse) super.clone();
    }

    public PayResponse(String str, String str2) {
        super(str, str2);
        this.isServerAnswer = true;
        this.amount = null;
    }

    public PayResponse(String str) {
        super(str);
        this.isServerAnswer = true;
        this.amount = null;
    }

    public PayResponse() {
        this.isServerAnswer = true;
        this.amount = null;
    }
}
